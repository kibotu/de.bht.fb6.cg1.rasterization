package de.bht.fb6.cg1.exercise4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

/*
 * represents light - singleton
 */
public class Light {

    /**
     * holds all lights that are currently added
     */
    protected final Map<Integer, Boolean> lights;
    /**
     * singleton instance
     */
    protected static final Light INSTANCE = new Light();
    /**
     * sets size of light cone
     */
    protected int spotLightAngle = 30;

    /**
     * singleton constructor
     */
    protected Light() {
	lights = new HashMap<Integer, Boolean>();
    }
    
    /**
     * gets an instance of light
     * 
     * @return instance
     */
    public static Light getInstance() {
	return INSTANCE;
    }

    /**
     * switches 
     * 
     * @param id
     */
    public void switchLight(int id) {
	if (!lights.containsKey(id)) {
	    return;
	}
	if (lights.get(id)) {
	    GL11.glDisable(id);
	    lights.put(id, false);
	} else {
	    GL11.glEnable(id);
	    lights.put(id, true);
	}
    }

    /** 
     * adds a light and enables it
     * 
     * @param id
     */
    public void addLight(int id) {
	if(!lights.containsKey(id)) {
	    lights.put(id, true);
	    GL11.glEnable(id);
	}
    }
    
    /**
     * enables all light
     */
    public void enableAll() {
	GL11.glEnable(GL11.GL_LIGHTING);
    }

    /**
     * disables all light
     */
    public void disableAll() {
	GL11.glDisable(GL11.GL_LIGHTING);
    }

    /**
     * adds ambient light
     * 
     * @param intensity
     */
    public void addAmbient(float[] intensity) {
	FloatBuffer ambientColor = Light.allocFloats(intensity);
	GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, ambientColor);
    }

    /**
     * adds diffuse light
     * 
     * @param id
     * @param intensity
     */
    public void addDiffuse(int id, float[] intensity) {
	addLight(id);
	FloatBuffer diffuseLight = allocFloats(intensity);
	GL11.glLight(id, GL11.GL_DIFFUSE, diffuseLight);
    }

    /**
     * adds specular light
     * 
     * @param id
     * @param intensity
     */
    public void addSpecular(int id, float[] intensity) {
	addLight(id);
	FloatBuffer specularLight = allocFloats(intensity);
	GL11.glLight(id, GL11.GL_SPECULAR, specularLight);
    }

    /**
     * sets a position of the light
     * 
     * @param id
     * @param position
     */
    public void setPosition(int id, float[] position) {
	FloatBuffer lightPosition = allocFloats(position);
	GL11.glLight(id, GL11.GL_POSITION, lightPosition);
    }

    /**
     * adds spotdirection
     * 
     * @param id
     * @param direction
     */
    public void addSpotDirection(int id, float[] direction) {
	addLight(id);
	FloatBuffer spotDirection = allocFloats(direction);
	GL11.glLight(id, GL11.GL_SPOT_DIRECTION, spotDirection);
	GL11.glLighti(id, GL11.GL_SPOT_CUTOFF, spotLightAngle);
    }
    
    /**
     * changes the spotlightangle
     * 
     * @param id
     * @param dx
     */
    public void changeCut(int id, int dx) {	
	this.spotLightAngle = this.spotLightAngle + dx;
    }
    
    /**
     * float array to floatbuffer
     * 
     * @param floatarray
     * 
     * @return floatbuffer
     */
    public static FloatBuffer allocFloats(float[] floatarray) {
	FloatBuffer fb = ByteBuffer.allocateDirect(floatarray.length * 4)
		.order(ByteOrder.nativeOrder()).asFloatBuffer();
	fb.put(floatarray).flip();
	return fb;
    }
}
