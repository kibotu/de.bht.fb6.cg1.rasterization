package de.bht.fb6.cg1.exercise4;

import org.lwjgl.opengl.GL11;

/**
 * superclass for all drawables
 * 
 */
public abstract class Drawable {

    protected final String name;
    protected final float width;
    protected final float height;
    protected final float deepth;
    protected final int subDivisions;
    protected final int displayId;
    protected boolean hasDisplayList;
    
    /**
     * constructs drawable
     * 
     * @param name
     * @param width
     * @param height
     * @param deepth
     * @param subDivisions
     * @param hasDisplayList
     */
    public Drawable(String name, float width, float height, float deepth, int subDivisions, boolean hasDisplayList) {
	this.name = name;
	this.width = width;
	this.height = height;
	this.deepth = deepth;
	this.subDivisions = subDivisions;
	this.hasDisplayList = hasDisplayList;
	if(hasDisplayList) {
	    this.displayId = GL11.glGenLists(1);
	    createDisplayList();
	} else {
	    this.displayId = 0;
	}
    }
    
    /**
     * draws drawable, if displayList true = wraps displaylist around the build
     */
    public void draw() {
	if(hasDisplayList) {
	    GL11.glCallList(displayId);
	} else {
	    build();
	}
    }
    
    /**
     * wrapping displaylist
     */
    public void createDisplayList() {
	GL11.glNewList(displayId, GL11.GL_COMPILE);
	build();
	GL11.glEndList();
    }
    
    /**
     * builds the drawable
     */
    protected abstract void build();
    
    /**
     * returns name
     * 
     * @return name
     */
    public String getName() {
	return name;
    }
}