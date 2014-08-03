package de.bht.fb6.cg1.rasterization;

import de.bht.fb6.cg1.math.IColumnVector;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represets a circle. It is used in the rasterization exercise. The
 * circle it parametrized by the middle point and the radius. Attributes can be
 * applied for the center and the edge of the circle.
 * 
 * @author Stephan Rehfeld
 */
public class Circle implements IFigure {

    @Override
    public String toString() {
	return "Circle [p=" + p + ", attributesP=" + attributesP + ", r=" + r + ", attributesR=" + attributesR + "]";
    }

    /**
     * The center point.
     */
    final IColumnVector<Float> p;

    /**
     * Attributes for the center point.
     */
    final Map<String, Object> attributesP;

    /**
     * The radius.
     */
    final float r;

    /**
     * Attributes for the edge of the circle.
     */
    final Map<String, Object> attributesR;

    /**
     * This constructor creates a new Circle with given center point an radius.
     * A circle without any attributes is created.
     * 
     * @param p
     *            The center point. Must not be 'null'.
     * @param r
     *            The radius. Must be larger than 0.
     */
    public Circle(final IColumnVector<Float> p, final float r) {
	this(p, new HashMap<String, Object>(), r, new HashMap<String, Object>());
    }

    /**
     * This constructor creates a new Circle with given center point. radius,
     * and attributes for the center and the edge. Attributes may be interpreted
     * by the rasterization unit.
     * 
     * @param p
     *            The center point. Must not be 'null'.
     * @param attributesP
     *            Attributes for the center of the circe. Must not be 'null'.
     * @param r
     *            The radius. Must be larger than 0.
     * @param attributesR
     *            Attributes for the edge of the circe. Must not be 'null'.
     */
    public Circle(final IColumnVector<Float> p, final Map<String, Object> attributesP, final float r,
	    final Map<String, Object> attributesR) {
	if (p == null) {
	    throw new IllegalArgumentException("The paraneter 'p' must not be 'null'!");
	}
	if (attributesP == null) {
	    throw new IllegalArgumentException("The paraneter 'attributesP' mut not be 'null'!");
	}
	if (r <= 0f) {
	    throw new IllegalArgumentException("The paraneter 'r' must be larger than 0!");
	}
	if (attributesR == null) {
	    throw new IllegalArgumentException("The paraneter 'attributesR' mut not be 'null'!");
	}
	this.p = p;
	this.attributesP = attributesP;
	this.r = r;
	this.attributesR = attributesR;

    }

    /**
     * This method returns the center point of the circle.
     * 
     * @return The center point of the circle. Never returns 'nulll'.
     */
    public IColumnVector<Float> getP() {
	return this.p;
    }

    /**
     * This method return the attributes of the center point of the circle.
     * 
     * @return The attributes of the center. Never returns 'null' but maybe a
     *         empty map.
     */
    public Map<String, Object> getAttributesP() {
	return new HashMap<String, Object>(this.attributesP);
    }

    /**
     * This method returns the radius of the circle.
     * 
     * @return The radius of the circle. Is larger than 0.
     */
    public float getR() {
	return this.r;
    }

    /**
     * This method return the attributes of the edge of the circle.
     * 
     * @return The attributes of edge. Never returns 'null' but maybe a empty
     *         map.
     */
    public Map<String, Object> getAttributesR() {
	return new HashMap<String, Object>(this.attributesR);
    }

    @Override
    public boolean equals(final Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Circle other = (Circle) obj;
	if (this.p != other.p && (this.p == null || !this.p.equals(other.p))) {
	    return false;
	}
	if (this.attributesP != other.attributesP
		&& (this.attributesP == null || !this.attributesP.equals(other.attributesP))) {
	    return false;
	}
	if (this.r != other.r) {
	    return false;
	}
	if (this.attributesR != other.attributesR
		&& (this.attributesR == null || !this.attributesR.equals(other.attributesR))) {
	    return false;
	}
	return true;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 29 * hash + (this.p != null ? this.p.hashCode() : 0);
	hash = 29 * hash + (this.attributesP != null ? this.attributesP.hashCode() : 0);
	hash = 29 * hash + Float.floatToIntBits(this.r);
	hash = 29 * hash + (this.attributesR != null ? this.attributesR.hashCode() : 0);
	return hash;
    }

    /**
     * 'draws' circle; if at that point is already a color: newColor = (oldColor + currentColor) 
     * 
     * @param frameBuffer
     */
    public void fill(final Color[][] frameBuffer) {
	if (frameBuffer == null) {
	    throw new NullPointerException("'frameBuffer' must not be 'null'");
	}
	final Color cP = (getAttributesP().containsKey("color")) ? (Color) getAttributesP().get("color") : Color.white;
	final Color cR = (getAttributesR().containsKey("color")) ? (Color) getAttributesR().get("color") : Color.white;
	
	final int px = getP().get(0, 0).intValue();
	final int py = getP().get(1, 0).intValue();

	for (int y = (int) (py - r); y <= py + r; ++y) {
	    for (int x = (int) (px - r); x <= px + r; ++x) {
		int gleichung = (int)((x - px) * (x - px) + (y - py) * (y - py) - r * r);
		if (gleichung <= 0) {
		    frameBuffer[x][y] = new Color((frameBuffer[x][y].getRGB() + RasterizerColor.gradedValue(cP, cR,
			    (float) Math.hypot(px - x, py - y) / r).getRGB()));;
		}
	    }
	}
    }

    /**
     * 'draws' circle; if at that point is already a color: newColor = (oldColor + currentColor) / 2 
     * 
     * @param frameBuffer
     */
    public void outline(final Color[][] frameBuffer) {
	if (frameBuffer == null) {
	    throw new NullPointerException("'frameBuffer' must not be 'null'");
	}
	final Color cP = (getAttributesP().containsKey("color")) ? (Color) getAttributesP().get("color") : Color.white;
	final Color cR = (getAttributesR().containsKey("color")) ? (Color) getAttributesR().get("color") : Color.white;
	
	final int px = getP().get(0, 0).intValue();
	final int py = getP().get(1, 0).intValue();

	for (int y = (int) (py - r); y <= py + r; ++y) {
	    for (int x = (int) (px - r); x <= px + r; ++x) {
		double gleichung = ((x - px) * (x - px) + (y - py) * (y - py) - r * r);
		if (gleichung <= 0 && gleichung >= -getR() * Math.PI) {
		    frameBuffer[x][y] = new Color((frameBuffer[x][y].getRGB() + RasterizerColor.gradedValue(cR, cP,
			    (float) Math.hypot(px - x, py - y) / r).getRGB()) / 2);
		}
	    }
	}
    }

}
