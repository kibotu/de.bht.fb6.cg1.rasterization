package de.bht.fb6.cg1.exercise4;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import config.Config;

/**
 * Represents a opengl Triangle 
 */
public class Triangle {
    
    protected float[] v1;
    protected float[] v2;
    protected float[] v3;
    protected float[] t1;
    protected float[] t2;
    protected float[] t3;

    /**
     * constructs triangle
     * 
     * @param vertice1
     * @param texture1
     * @param vertice2
     * @param texture2
     * @param vertice3
     * @param texture3
     */
    public Triangle(float v1[], float t1[], float v2[], float t2[], float v3[],
	    float t3[]) {
	this.v1 = v1;
	this.v2 = v2;
	this.v3 = v3;
	this.t1 = t1;
	this.t2 = t2;
	this.t3 = t3;
    }
    
    /**
     * constructs triangle without texture coordinates
     * 
     * @param v1
     * @param v2
     * @param v3
     */
    public Triangle(float v1[], float v2[], float v3[]) {
	this(v1, new float[] { 0.5f, 1f }, v2, new float[] { 0f, 0f }, v3,
		new float[] { 1f, 1f });
    }

    /**
     *  draws the triangle
     */
    public void draw() {
	GL11.glBegin(Config.TRIANGLE_DRAWING_STYLE);
	GL11.glTexCoord2f(t1[0], t1[1]);
	GL11.glVertex3f(v1[0], v1[1], v1[2]);
	GL11.glTexCoord2f(t2[0], t2[1]);
	GL11.glVertex3f(v2[0], v2[1], v2[2]);
	GL11.glTexCoord2f(t3[0], t3[1]);
	GL11.glVertex3f(v3[0], v3[1], v3[2]);
	GL11.glEnd();
    }

    /**
     * draws subdivided triangle
     * 
     * @param divisions
     */
    public void draw(int divisions) {
	divideTriangle(v1,t1, v2,t2, v3,t3, divisions);
    }

    /**
     * divides a triangle
     * 
     * @param vertice1
     * @param texture1
     * @param vertice2
     * @param texture2
     * @param vertice3
     * @param texture3
     * @param subdivisions
     */
    void divideTriangle(float[] a, float [] ta, float[] b, float [] tb, float[] c, float [] tc, int m) {
	float [] va = new float[3];
	float [] vb = new float[3];
	float [] vc = new float[3];
	float [] vta = new float [2];
	float [] vtb = new float [2];
	float [] vtc = new float [2];
	int j;
	if (m > 0) {
	    for (j = 0; j < 3; j++) va[j] = (a[j] + b[j]) / 2;
	    for (j = 0; j < 3; j++) vb[j] = (a[j] + c[j]) / 2;
	    for (j = 0; j < 3; j++) vc[j] = (b[j] + c[j]) / 2;
	    for (j = 0; j < 2; j++) vta[j] = (ta[j] + tb[j]) / 2;
	    for (j = 0; j < 2; j++) vtb[j] = (ta[j] + tc[j]) / 2;
	    for (j = 0; j < 2; j++) vtc[j] = (tb[j] + tc[j]) / 2;
	    divideTriangle(a,ta, va,vta, vb,vtb, m - 1);
	    divideTriangle(c, tc, vb,vtb, vc,vtc, m - 1);
	    divideTriangle(b,tb, vc,vtc, va,vta, m - 1);
	    divideTriangle(va,vta, vb,vtb, vc,vtc, m - 1);
	} else {
	    (new Triangle(a, ta, b, tb, c, tc)).draw();
	}
    }

    /**
     * returns name
     * 
     * @return name
     */
    public String getName() {
	return "Triangle";
    }

    @Override
    public String toString() {
	return getName() + " [v1=" + Arrays.toString(v1) + ", v2="
		+ Arrays.toString(v2) + ", v3=" + Arrays.toString(v3) + ", t1="
		+ Arrays.toString(t1) + ", t2=" + Arrays.toString(t2) + ", t3="
		+ Arrays.toString(t3) + "]";
    }
}
