package de.bht.fb6.cg1.exercise4;

import org.lwjgl.opengl.GL11;

/**
 * represents a cube
 */
public class Cube extends Drawable{

    /**
     * if texture shows to the inside
     */
    final protected boolean isInside;
    
    /**
     * constructs cube
     * 
     * @param name
     * @param width
     * @param height
     * @param deepth
     * @param subDivisions
     * @param hasDisplayList
     * @param hasTexturesInside
     */
    public Cube(String name,final float width, final float height, final float deepth, int subDivisions, boolean hasDisplayList, boolean hasTexturesInside) {
	super(name,width, height, deepth, subDivisions,hasDisplayList);
	this.isInside = hasTexturesInside;
    }

    /**
     * consctructs simpler cube
     * 
     * @param name
     * @param width
     * @param height
     * @param deepth
     */
    public Cube(String name,final float width, final float height, final float deepth) {
	this(name, width,height,deepth,0, false, true);
    }
    
    /**
     * simple construct
     */
    public Cube() {
	this("cube", 1,1,1,0,false, true);
    }

    @Override
    public void build() {
	TextureLoader.getInstance().drawTexture(name);
	GL11.glPushMatrix();
	GL11.glScalef(width, height, deepth);
	final float c = 0.5f;
	GL11.glColor3f(1f, 1f, 1f);
	/** far **/
	GL11.glNormal3f(0f, 0f, isInside ? 1f : -1f);
	Triangle efg = new Triangle(
		new float[] {-c, -c, -c },new float[] {0f,1f}, 
		new float[] {c, -c, -c },new float[] {1f, 1f},
		new float[] {c, c, -c },new float[] {1f, 0f});
	Triangle egh = new Triangle(
		new float[] {-c, -c, -c },new float[] {0f, 1f}, 
		new float[] {c, c, -c},new float[] {1f, 0f},
		new float[] {-c, c, -c },new float[] {0f, 0f});
	efg.draw(subDivisions);
	egh.draw(subDivisions);
	
	/** near **/
	GL11.glNormal3f(0f, 0f, isInside ? -1f : 1f);
	Triangle abc = new Triangle(
		new float[] {-c, -c, c },new float[] {1f, 1f}, 
		new float[] {c, -c, c },new float[] {0f, 1f},
		new float[] {c, c, c },new float[] {0f, 0f});
	
	Triangle acd = new Triangle(
		new float[] {-c, -c, c},new float[] {1f, 1f}, 
		new float[] {c, c, c},new float[] {0f, 0f},
		new float[] {-c, c, c },new float[] {1f, 0f});
	abc.draw(subDivisions);
	acd.draw(subDivisions);
	
	/** bottom **/
	GL11.glNormal3f(0f, isInside ? 1f : -1f, 0f);
	Triangle abf = new Triangle(
		new float[] {-c, -c, c },new float[] {1f, 0f}, 
		new float[] {c, -c, c},new float[] {0f, 0f},
		new float[] {c, -c, -c },new float[] {0f, 1f});
	
	Triangle afe = new Triangle(
		new float[] {-c, -c, c},new float[] {1f, 0f}, 
		new float[] {c, -c, -c},new float[] {0f, 1f},
		new float[] {-c, -c, -c },new float[] {1f, 1f});
	abf.draw(subDivisions);
	afe.draw(subDivisions);

	/** top **/
	GL11.glNormal3f(0f, isInside ? -1f : 1f, 0f);
	Triangle dcg = new Triangle(
		new float[] {-c, c, c },new float[] {1f, 1f}, 
		new float[] {c, c, c},new float[] {0f, 1f},
		new float[] {c, c, -c },new float[] {0f, 0f});
	
	Triangle dgh = new Triangle(
		new float[] {-c, c, c},new float[] {1f, 1f}, 
		new float[] {c, c, -c},new float[] {0f, 0f},
		new float[] {-c, c, -c},new float[] {1f, 0f});
	dcg.draw(subDivisions);
	dgh.draw(subDivisions);
	
	/** left **/
	GL11.glNormal3f(isInside ? -1f : 1f, 0f, 0f);
	Triangle bfg = new Triangle(
		new float[] {c, -c, c },new float[] {1f, 1f}, 
		new float[] {c, -c, -c},new float[] {0f, 1f},
		new float[] {c, c, -c},new float[] {0f, 0f});
	
	Triangle bgc = new Triangle(
		new float[] {c, -c, c},new float[] {1f, 1f}, 
		new float[] {c, c, -c},new float[] {0f, 0f},
		new float[] {c, c, c},new float[] {1f,0f});
	bfg.draw(subDivisions);
	bgc.draw(subDivisions);

	/** right **/
	GL11.glNormal3f(isInside ? 1f : -1f, 0f, 0f);
	Triangle aeh = new Triangle(
		new float[] {-c, -c, c },new float[] {0f, 1f}, 
		new float[] {-c, -c, -c},new float[] {1f, 1f},
		new float[] {-c, c, -c},new float[] {1f, 0f});
	
	Triangle ahd = new Triangle(
		new float[] {-c, -c, c},new float[] {0f, 1f}, 
		new float[] {-c, c, -c},new float[] {1f, 0f},
		new float[] {-c, c, c},new float[] {0f, 0f});
	aeh.draw(subDivisions);
	ahd.draw(subDivisions);
	GL11.glPopMatrix();
	TextureLoader.getInstance().defaultTexture();
    }
}
