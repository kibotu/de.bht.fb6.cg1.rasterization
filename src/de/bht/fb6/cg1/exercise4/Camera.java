package de.bht.fb6.cg1.exercise4;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import config.Config;

/**
 * represents a camera
 */
public class Camera {

    protected float angleH;
    protected float angle;
    protected float angleV;
    protected float[] eye;
    protected float[] focus;
    protected float[] up;
    protected float camSpeed;
    protected float[] strafe;

    /**
     * constructs the camera
     */
    public Camera() {
		angle = 45;
		angleH = 0;
		angleV = 0;
		eye = new float[] { 0f, 0f, 0f };
		focus = new float[] { 0f, 0f, 0f };
		up = new float[] { 0f, 1f, 0f };
		camSpeed = 1f;
    }
    
    /**
     * sets position for the camera
     * 
     * @param eye
     * @param position
     * @param up
     */
    public void setPosition(float [] eye, float [] position, float []up) {
	this.eye = eye.clone();
	this.focus = position.clone();
	this.up = up.clone();
    }

    /**
     * moves camera
     * 
     * positiv speed = forward 
     * negative speed = backwards
     * 
     * @param speed
     */
    public void move(float speed) {
	float[] vVector = normalize(sub(focus, eye));
	eye[0] += vVector[0] * speed;
	eye[2] += vVector[2] * speed;
	focus[0] += vVector[0] * speed;
	focus[2] += vVector[2] * speed;
	update();
    }

    /**
     * strafes camera
     * 
     * positiv speed = right 
     * negative speed = left
     * 
     * @param speed
     */
    public void strafe(float speed) {
	eye[0] += strafe[0] * speed;
	eye[2] += strafe[2] * speed;
	focus[0] += strafe[0] * speed;
	focus[2] += strafe[2] * speed;
	update();
    }

    /**
     * looks sidewards
     * 
     * positiv speed = right 
     * negative speed = left
     * 
     * @param rotatexz
     */
    public void lookHorizontally(float speed) {
	angleH += speed;
	focus[0] = eye[0] + (float) Math.sin(angleH);
	focus[2] = eye[2] + (float) -Math.cos(angleH);
	update();
    }
    
    /**
     * looks up and down
     * 
     * positive speed = up
     * negative speed = down
     * 
     * @param speed
     */
    public void lookVertically(float speed) {
//	float[] vAxis = crossProduct(sub(focus,eye), up);
//	rotate(speed, normalize(vAxis));
	focus[1] += speed;
	update();
    }

    /**
     * zooms in or out
     * 
     * @param zoomfactor
     */
    public void zoom(float zoomfactor) {
//	update();
    }
    
    /**
     * rotates
     * 
     * @param angle
     * @param vector
     */
    public void rotate(float angle, float [] vector) {
	rotate(angle, vector[0], vector[1], vector[2]);
    }
    
    /**
     * rotates 
     * 
     * @param angle
     * @param x
     * @param y
     * @param z
     */
    public void rotate(float angle, float x, float y, float z)  {
    	float [] newView = new float[3];
    	float [] vView = sub(focus,eye);		

    	float cosTheta = (float)Math.cos(angle);
    	float sinTheta = (float)Math.sin(angle);

    	newView[0]  = (cosTheta + (1 - cosTheta) * x * x)	* vView[0];
    	newView[0] += ((1 - cosTheta) * x * y - z * sinTheta)	* vView[1];
    	newView[0] += ((1 - cosTheta) * x * z + y * sinTheta)	* vView[2];

    	newView[1]  = ((1 - cosTheta) * x * y + z * sinTheta)	* vView[0];
    	newView[1] += (cosTheta + (1 - cosTheta) * y * y)	* vView[1];
    	newView[1] += ((1 - cosTheta) * y * z - x * sinTheta)	* vView[2];

    	newView[2]  = ((1 - cosTheta) * x * z - y * sinTheta)	* vView[0];
    	newView[2] += ((1 - cosTheta) * y * z + x * sinTheta)	* vView[1];
    	newView[2] += (cosTheta + (1 - cosTheta) * z * z)	* vView[2];

    	focus = add(eye,newView);
    }

    /**
     * updates glulookat
     */
    private void update() {
		GL11.glLoadIdentity();
		GLU.gluLookAt(eye[0], eye[1], eye[2], focus[0], focus[1], focus[2],
			up[0], up[1], up[2]);
		strafe = normalize(crossProduct(sub(focus, eye), up));
    }

    /**
     * substracts vectors (v1-v2)
     * 
     * @param v1
     * @param v2
     * @return substracted Vector
     */
    private float[] sub(float[] v1, float[] v2) {
	return new float[] { v1[0] - v2[0], v1[1] - v2[1], v1[2] - v2[2] };
    }
    
    /**
     * add vectors (v1+v2)
     * 
     * @param v1
     * @param v2
     * @return
     */
    private float[] add(float[] v1, float[] v2) {
	return new float[] { v1[0] + v2[0], v1[1] + v2[1], v1[2] + v2[2] };
    }

    /**
     * crossproduct of 2 vectors
     * 
     * @param vVector1
     * @param vVector2
     * @return crossproduct
     */
    private float[] crossProduct(float[] vVector1, float[] vVector2) {
	return new float[] {
		vVector1[1] * vVector2[2] - vVector1[2] * vVector2[1],
		vVector1[2] * vVector2[0] - vVector1[0] * vVector2[2],
		vVector1[0] * vVector2[1] - vVector1[1] * vVector2[0] };
    }

    /**
     * normalizes vector
     * 
     * @param vVector
     */
    private float[] normalize(float[] vVector) {
	float[] normalizedVector = new float[vVector.length];
	System.arraycopy(vVector, 0, normalizedVector, 0, vVector.length);
	float magnitude = magnitude(vVector);
	for (int i = 0; i < vVector.length; ++i)
	    vVector[i] /= magnitude;
	return normalizedVector;
    }

    /**
     * calcs magnitude of a vector;
     * 
     * @param vNormal
     * @return magnitude
     */
    private float magnitude(float[] vNormal) {
	return (float) Math.sqrt((vNormal[0] * vNormal[0])
		+ (vNormal[1] * vNormal[1]) + (vNormal[2] * vNormal[2]));
    }

    /**
     * inits to perspective view
     */
    public void switchToPerspView() {
	GL11.glMatrixMode(GL11.GL_PROJECTION);
	GL11.glLoadIdentity();
	GLU.gluPerspective(angle, (float) Display.getDisplayMode().getWidth() / (float) Display.getDisplayMode().getHeight(), 1,Config.WORLD_SIZE);
	GL11.glMatrixMode(GL11.GL_MODELVIEW);
	update();
    }

    /**
     * inits to orthographic view
     */
    public void switchToOrthView() {
	GL11.glMatrixMode(GL11.GL_PROJECTION);
	GL11.glLoadIdentity();
	GL11.glViewport(0, 0, Display.getDisplayMode().getWidth(), Display
		.getDisplayMode().getHeight());
	GL11.glOrtho(-(float) Display.getDisplayMode().getWidth()
		/ Display.getDisplayMode().getHeight(), (float) Display
		.getDisplayMode().getWidth()
		/ Display.getDisplayMode().getHeight(), -1, 1, -1, 1);
	GL11.glMatrixMode(GL11.GL_MODELVIEW);
	GL11.glLoadIdentity();
    }
}
