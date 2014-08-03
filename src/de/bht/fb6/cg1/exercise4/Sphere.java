package de.bht.fb6.cg1.exercise4;

import org.lwjgl.opengl.GL11;

import config.Config;

/**
 * represents a sphere
 *
 */
public class Sphere extends Drawable {

    /**
     * constructs the sphere
     * 
     * @param name
     * @param width
     * @param height
     * @param deepth
     * @param subDivisions
     * @param hasDisplayList
     */
    public Sphere(String name, float width, float height, float deepth, int subDivisions, boolean hasDisplayList) {
	super(name, width, height, deepth, subDivisions, hasDisplayList);
    }

    /**
     * simple sphere
     * 
     * @param name
     */
    public Sphere(String name) {
	this(name,1,1,1,50,true);
    }
    
    @Override
    protected void build() {
	TextureLoader.getInstance().drawTexture(name);
	GL11.glPushMatrix();
	GL11.glRotatef(90f, 2f, 0f, 0f);
	final org.lwjgl.util.glu.Sphere s = new org.lwjgl.util.glu.Sphere();
	s.setDrawStyle(Config.SPHERE_DRAWING_STYLE);
	s.setTextureFlag(true);
	s.draw(0.5f, subDivisions, subDivisions);
	GL11.glPopMatrix();
	TextureLoader.getInstance().defaultTexture();
    }
}
