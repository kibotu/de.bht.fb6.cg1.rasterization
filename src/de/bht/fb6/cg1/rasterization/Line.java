package de.bht.fb6.cg1.rasterization;

import de.bht.fb6.cg1.math.IColumnVector;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represets a line. It is used in the rasterization exercise. The
 * line it parametrized by two points. Attributes can be applied for each point
 * of the line.
 * 
 * @author Stephan Rehfeld
 */
public class Line implements IFigure {

    /**
     * The first point.
     */
    final IColumnVector<Float> p1;

    /**
     * The second point.
     */
    final IColumnVector<Float> p2;

    /**
     * The attributes of the first point.
     */
    final Map<String, Object> attributesP1;

    /**
     * The attributes of the second point.
     */
    final Map<String, Object> attributesP2;

    /**
     * This constructor creates a new Line with given points an no attributes
     * for the points.
     * 
     * @param p1
     *            The first point of the line. Must not be 'null'.
     * @param p2
     *            The second point of the line. Must not be 'null'.
     */
    public Line(final IColumnVector<Float> p1, final IColumnVector<Float> p2) {
	this(p1, new HashMap<String, Object>(), p2, new HashMap<String, Object>());
    }

    /**
     * This constructor creates a new Line with given points an optinal
     * attributes for the points.
     * 
     * @param p1
     *            The first point of the line. Must not be 'null'.
     * @param attributesP1
     *            The attributes for the first point. Must not be 'null' but can
     *            be empty.
     * @param p2
     *            The second point of the line. Must not be 'null'.
     * @param attributesP2
     *            The attributes for the second point. Must not be 'null' but
     *            can be empty.
     */
    public Line(final IColumnVector<Float> p1, final Map<String, Object> attributesP1, final IColumnVector<Float> p2,
	    final Map<String, Object> attributesP2) {
	if (p1 == null) {
	    throw new IllegalArgumentException("The paraneter 'p1' must not be 'null'!");
	}
	if (p2 == null) {
	    throw new IllegalArgumentException("The paraneter 'p2' must not be 'null'!");
	}
	if (attributesP1 == null) {
	    throw new IllegalArgumentException("The paraneter 'attributesP1' must not be 'null'!");
	}
	if (attributesP2 == null) {
	    throw new IllegalArgumentException("The paraneter 'attributesP2' must not be 'null'!");
	}
	this.p1 = p1;
	this.p2 = p2;
	this.attributesP1 = new HashMap<String, Object>(attributesP1);
	this.attributesP2 = new HashMap<String, Object>(attributesP2);
    }

    /**
     * This method returns the first point of the Line.
     * 
     * @return The first point of the Line. Never returns 'null'.
     */
    public IColumnVector<Float> getP1() {
	return this.p1;
    }

    /**
     * This method returns the second point of the Line.
     * 
     * @return The second point of the Line. Never returns 'null'.
     */
    public IColumnVector<Float> getP2() {
	return this.p2;
    }

    /**
     * This method returns the attributes of the first point of the line.
     * 
     * @return The attributes of the first point of the line. Never returns
     *         'null' but maybe a empty map.
     */
    public Map<String, Object> getAttributesP1() {
	return new HashMap<String, Object>(this.attributesP1);
    }

    /**
     * This method returns the attributes of the second point of the line.
     * 
     * @return The attributes of the second point of the line. Never returns
     *         'null' but maybe a empty map.
     */
    public Map<String, Object> getAttributesP2() {
	return new HashMap<String, Object>(this.attributesP2);
    }

    @Override
    public boolean equals(final Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Line other = (Line) obj;
	if (this.p1 != other.p1 && (this.p1 == null || !this.p1.equals(other.p1))) {
	    return false;
	}
	if (this.p2 != other.p2 && (this.p2 == null || !this.p2.equals(other.p2))) {
	    return false;
	}
	if (this.attributesP1 != other.attributesP1
		&& (this.attributesP1 == null || !this.attributesP1.equals(other.attributesP1))) {
	    return false;
	}
	if (this.attributesP2 != other.attributesP2
		&& (this.attributesP2 == null || !this.attributesP2.equals(other.attributesP2))) {
	    return false;
	}
	return true;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 29 * hash + (this.p1 != null ? this.p1.hashCode() : 0);
	hash = 29 * hash + (this.p2 != null ? this.p2.hashCode() : 0);
	hash = 29 * hash + (this.attributesP1 != null ? this.attributesP1.hashCode() : 0);
	hash = 29 * hash + (this.attributesP2 != null ? this.attributesP2.hashCode() : 0);
	return hash;
    }

    /**
     * 
     * @param x
     * @param y
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     * @return
     */
    public static float getLineValue(Float x, Float y, Float x0, Float y0, Float x1, Float y1) {
	return (y0 - y1) * x + (x1 - x0) * y + x0 * y1 - x1 * y0;
    }

    /**
     * 
     * @param x
     * @param y
     * @param zx
     * @param zy
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     * @return
     */
    public static float getLineEquationForTriangle(float x, float y, float zx, float zy, float x0, float y0, float x1,
	    float y1) {
	return getLineValue(x, y, x0, y0, x1, y1) / getLineValue(zx, zy, x0, y0, x1, y1);
    }

    /**
     * Jack Bresenham Line Algorythmus
     * 
     * @param frameBuffer
     */
    public void fill(final Color[][] frameBuffer) {
	if (frameBuffer == null) {
	    throw new NullPointerException("'frameBuffer' must not be 'null'");
	}
	final Color c0 = (getAttributesP1().containsKey("color")) ? (Color) getAttributesP1().get("color") : Color.white;
	final Color c1 = (getAttributesP1().containsKey("color")) ? (Color) getAttributesP2().get("color") : Color.white;

	int x0 = new Float(getP1().get(0, 0)).intValue();
	int y0 = new Float(getP1().get(1, 0)).intValue();
	int x1 = new Float(getP2().get(0, 0)).intValue();
	int y1 = new Float(getP2().get(1, 0)).intValue();

	int dy = y1 - y0;
	int dx = x1 - x0;
	int stepx = 1;
	int stepy = 1;

	final double length = Math.hypot(x1 - x0, y1 - y0);

	if (dy < 0) {
	    dy = -dy;
	    stepy = -1;
	}
	if (dx < 0) {
	    dx = -dx;
	    stepx = -1;
	}

	dy <<= 1; // dy is now 2*dy
	dx <<= 1; // dx is now 2*dx

	frameBuffer[x0][y0] = c0;
	if (dx > dy) {
	    int fraction = dy - (dx >> 1); // same as 2*dy - dx
	    while (x0 != x1) {
		if (fraction >= 0) {
		    y0 += stepy;
		    fraction -= dx; // same as fraction -= 2*dx
		}
		x0 += stepx;
		fraction += dy; // same as fraction -= 2*dy
		Color newColor = RasterizerColor.gradedValue(c1, c0, (Math.abs((x1 - x0))) / length);

//		frameBuffer[x0][y0 - 1] = newColor.darker().darker().darker().darker();
		frameBuffer[x0][y0] = newColor;
//		frameBuffer[x0][y0 + 1] = newColor.darker().darker().darker().darker();
	    }
	} else {
	    int fraction = dx - (dy >> 1);
	    while (y0 != y1) {
		if (fraction >= 0) {
		    x0 += stepx;
		    fraction -= dy;
		}
		y0 += stepy;
		fraction += dx;
		Color newColor = RasterizerColor.gradedValue(c1, c0, (Math.abs(y1 - y0)) / length);

//		frameBuffer[x0 - 1][y0] = newColor.darker().darker().darker().darker();
		frameBuffer[x0][y0] = newColor;
//		frameBuffer[x0 + 1][y0] = newColor.darker().darker().darker().darker();
	    }
	}
    }

    @Override
    public String toString() {
	return "Line [p1=" + p1 + ", p2=" + p2 + ", attributesP1=" + attributesP1 + ", attributesP2=" + attributesP2
		+ "]";
    }

    
}
