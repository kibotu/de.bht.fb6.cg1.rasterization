package de.bht.fb6.cg1.exercise4;

import org.lwjgl.opengl.GL11;

import config.Config;

/**
 * represents a polygon made out of a obj-file
 *
 */
public class ObjPolygon extends Drawable{

    /**
     * constructs polygon 
     * 
     * @param name
     * @param width
     * @param height
     * @param deepth
     * @param subDivisions
     * @param hasDisplayList
     */
    public ObjPolygon(String name, float width, float height, float deepth,
	    int subDivisions, boolean hasDisplayList) {
	super(name, width, height, deepth, subDivisions, hasDisplayList);
    }

    @Override
    protected void build() {
	ObjectLoader objLoader = new ObjectLoader(name, Config.OBJECTS.get(name));
	if(objLoader.hasTextures) {
//	    TextureLoader.getInstance().drawTexture(name);
	}
	GL11.glPushMatrix();
	GL11.glScalef(width, height, deepth);
	objLoader.draw();
	GL11.glPopMatrix();
//	TextureLoader.getInstance().reset();
    }
}