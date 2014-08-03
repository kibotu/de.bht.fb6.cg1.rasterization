package de.bht.fb6.cg1.exercise4;

import org.lwjgl.opengl.GL11;

/**
 * represents a plane 
 */
public class Plane extends Drawable {

    /**
     * constructs plane
     * 
     * @param name
     * @param width
     * @param height
     * @param deepth
     * @param subDivisions
     * @param hasDisplayList
     */
    public Plane(String name, float width, float height, float deepth,int subDivisions, boolean hasDisplayList) {
	super(name, width, height, deepth, subDivisions,hasDisplayList);
    }    
    
    /**
     * simple constrcut
     * 
     * @param name
     */
    public Plane(String name) {
	this(name, 1f, 1f, 1f, 0, false);
    }

    @Override
    protected void build() {
	float repeatTexture = 5f;
	TextureLoader.getInstance().drawTexture(name);
	GL11.glPushMatrix();
	GL11.glScalef(width, height, deepth);
	float c = 0.5f;
	GL11.glColor3f(1f, 1f, 1f);
	GL11.glNormal3f(0f, 1f, 0f);
	Triangle t1 = new Triangle(
		new float[] {-c,0f,c },new float[] {0f-repeatTexture, 0f-repeatTexture}, 
		new float[] {c, 0f, c },new float[] {1f+repeatTexture, 0f-repeatTexture},
		new float[] {c, 0f, -c },new float[] {1f+repeatTexture, 1f+repeatTexture});
	Triangle t2 = new Triangle(
		new float[] {-c, 0f, c},new float[] {0f-repeatTexture,0f-repeatTexture}, 
		new float[] {c, 0f, -c},new float[] {1f+repeatTexture, 1f+repeatTexture},
		new float[] {-c, 0f, -c },new float[] {0f-repeatTexture, 1f+repeatTexture});
	t1.draw(subDivisions);
	t2.draw(subDivisions);
	GL11.glPopMatrix();
	TextureLoader.getInstance().defaultTexture();
    }
}
