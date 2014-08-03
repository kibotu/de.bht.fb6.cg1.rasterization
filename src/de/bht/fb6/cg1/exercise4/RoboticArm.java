package de.bht.fb6.cg1.exercise4;

import org.lwjgl.opengl.GL11;

/**
 * represents a robotic arm 
 *
 */
public class RoboticArm extends Drawable{
    
    protected float sphereRotateY = 0;
    /** upends the arm  */
    protected float arm1 = 0f;
    protected float arm2 = 0f;
    protected float arm3 = 0f;
    /** rotates the arms  */
    protected float armR1 = 0f;
    protected float armR2 = 0f;
    protected float armR3 = 0f;
    
    protected float x = 0f;
    protected float y = -50f;
    protected float z = 0f;

    /**
     * constrcuts robotic arm
     * 
     * @param name
     * @param width
     * @param height
     * @param deepth
     * @param subDivisions
     * @param hasDisplayList
     */
    public RoboticArm(String name, float width, float height, float deepth, int subDivisions, boolean hasDisplayList) {
	super(name, width, height, deepth, subDivisions, hasDisplayList);
    }
    
    /** 
     * simple constructor
     */
    public RoboticArm() {
	this("robotic-arm",0.33f,1f,0.33f,0, true);
    }

    @Override
    protected void build() {
	
	GL11.glPushMatrix();
	GL11.glTranslatef(x, y, z);
	
	GL11.glPushMatrix();
	GL11.glScalef(this.width, this.height, this.deepth);
	
	final float width = 0.33f;
	final float deepth = 0.33f;
	final float height = 1f;
	final float radius = 0.33f;
	/* anfang 1. arm */
	GL11.glPushMatrix();
//	GL11.glTranslatef(-width / 2, -2f, -deepth / 2);
//	GL11.glTranslatef(0f, -10f, 0f);

	GL11.glPushMatrix();
	GL11.glTranslatef(width / 2, 0f, deepth / 2);
	GL11.glScalef(radius, radius, radius);
	GL11.glRotatef(sphereRotateY % 360, 0f, 1f, 0f);
	Sphere moon = new Sphere("moon", 1f,1f,1f,50+subDivisions, false);
	moon.draw();
	GL11.glPopMatrix();

	GL11.glTranslatef(width / 2, 0f, deepth / 2);

	GL11.glPushMatrix();
	// kippen
	GL11.glRotatef(arm1, 0f, 0f, 1f);
	// um eigene achse drehen
	GL11.glRotatef(armR1, 0.0f, 1f, 0.0f);
	GL11.glTranslatef(0f, 0.65f, 0f);
	(new Cube("cube", width, height, deepth, subDivisions, false, false)).draw();
	/* anfang 2. arm * */
	GL11.glTranslatef(0f, 0.8f, 0f);

	GL11.glPushMatrix();
	GL11.glTranslatef(0f, -0.15f, 0f);
	GL11.glScalef(radius, radius, radius);
	GL11.glRotatef(sphereRotateY % 360, 0f, 1f, 0f);
	Sphere earth = new Sphere("earth", 1f,1f,1f,50+subDivisions, false);
	earth.draw();
	GL11.glPopMatrix();

	GL11.glPushMatrix();
	// um eigene achse drehen
	GL11.glRotatef(armR2, 0.0f, 1.0f, 0.0f);
	// kippen
	GL11.glRotatef(arm2, 0f, 0f, 1f);
	GL11.glTranslatef(0f, 0.5f, 0f);
	(new Cube("cube", width, height, deepth, subDivisions, false, false)).draw();
	/* anfang 3. arm */
	GL11.glTranslatef(0f, 0.8f, 0f);

	GL11.glPushMatrix();
	GL11.glTranslatef(0f, -0.15f, 0f);
	GL11.glScalef(radius, radius, radius);
	GL11.glRotatef(sphereRotateY % 360, 0f, 1f, 0f);
	Sphere mars = new Sphere("mars", 1f,1f,1f,50+subDivisions, false);
	mars.draw();
	GL11.glPopMatrix();

	GL11.glPushMatrix();
	// um eigene achse drehen
	GL11.glRotatef(armR3, 0.0f, 1.0f, 0.0f);
	// kippen
	GL11.glRotatef(arm3, 0f, 0f, 1f);
	GL11.glTranslatef(0f, 0.5f, 0f);
	(new Cube("cube", width, height, deepth, subDivisions, false, false)).draw();

	/* directed light bulb */
	GL11.glTranslatef(0f, 0.8f, 0f);

	GL11.glPushMatrix();
	GL11.glTranslatef(0f, -0.15f, 0f);
	GL11.glScalef(radius, radius, radius);
	GL11.glRotatef(sphereRotateY % 360, 0f, 1f, 0f);
	Sphere sun = new Sphere("sun", 1f,1f,1f,50+subDivisions, false);
	sun.draw();
	
	Light light = Light.getInstance();
	light.addLight(GL11.GL_LIGHT0);
	light.addDiffuse(GL11.GL_LIGHT0, new float[] { 1f, 1f, 0.5f,1.0f });
	light.addSpecular(GL11.GL_LIGHT0, new float[] { 0.5f, 0.5f, 0.5f, 1f });
	light.setPosition(GL11.GL_LIGHT0, new float[] { 0f, 0f, 0f, 1f });
	light.addSpotDirection(GL11.GL_LIGHT0,new float[] { 0f, 1f, 0f, 0f });

	GL11.glPopMatrix();
	/* ende 3. arm */
	GL11.glPopMatrix();
	/* ende 2. arm */
	GL11.glPopMatrix();
	/* ende 1. arm */
	GL11.glPopMatrix();
	
	GL11.glPopMatrix();
	GL11.glPopMatrix();
	GL11.glPopMatrix();
    }
    
    /**
     * rotates first arm on y-axe
     * 
     * @param steps
     */
    public void rotateArm1Y(float steps) {
	arm1 += steps;
    }
    
    /**
     * rotates second arm on y-axe
     * 
     * @param steps
     */
    public void rotateArm2Y(float steps) {
	arm2 += steps;
    }
    
    /**
     * rotates third arm on y-axe
     * 
     * @param steps
     */
    public void rotateArm3Y(float steps) {
	arm3 += steps;
    }
    
    /**
     * rotates first arm on xz-axe
     * 
     * @param steps
     */
    public void rotateArm1XZ(float steps) {
	armR1 += steps;
    }
    
    /**
     * rotates second arm on xz-axe
     * 
     * @param steps
     */
    public void rotateArm2XZ(float steps) {
	armR2 += steps;
    }
    
    /**
     * rotates third arm on xz-axe
     * 
     * @param steps
     */
    public void rotateArm3XZ(float steps) {
	armR3 += steps;
    }
    
    /**
     * moves on x-axe
     * 
     * @param steps
     */
    public void moveX(float steps) {
	x += steps;
    }
    
    /**
     * moves on z-axe
     * 
     * @param steps
     */
    public void moveZ(float steps) {
	z += steps;
    }
}
