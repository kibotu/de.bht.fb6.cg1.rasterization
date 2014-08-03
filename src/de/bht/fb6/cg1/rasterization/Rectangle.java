package de.bht.fb6.cg1.rasterization;

import de.bht.fb6.cg1.math.IColumnVector;
import de.bht.fb6.cg1.rasterization.math.ColumnVector;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represets a rectangle. It is used in the rasterization exercise.
 * The triangle it parametrized by the upper left corener and a dimension.
 * Attributes can be applied to the rectangle.
 * 
 * @author Stephan Rehfeld
 */
public class Rectangle implements IFigure {

    /**
     * The upper left corner.
     */
    private final IColumnVector<Float> p;

    /**
     * The dimension.
     */
    private final IColumnVector<Float> d;

    /**
     * The attributes of the rectangle.
     */
    private final Map<String, Object> attributes;

    /**
     * This constructor creates a new rectangle.
     * 
     * @param p
     *            The upper left corner. Must not be 'null'.
     * @param d
     *            The dimnesion. Must not be 'null'. Must be positive.
     * @param attributes
     *            The attributes of the rectangle. Must not be 'null' but can be
     *            empty.
     */
    public Rectangle(final IColumnVector<Float> p, final IColumnVector<Float> d, final Map<String, Object> attributes) {
	if (p == null) {
	    throw new IllegalArgumentException("The parameter 'p' must not be null!");
	}
	if (d == null) {
	    throw new IllegalArgumentException("The parameter 'd' must not be null!");
	}
	if (d.get(0, 0) < 0f || d.get(1, 0) < 0) {
	    throw new IllegalArgumentException("The parameter 'd' must be positive!");
	}
	if (attributes == null) {
	    throw new IllegalArgumentException("The parameter 'attributes' must not be null!");
	}
	this.p = p;
	this.d = d;
	this.attributes = new HashMap<String, Object>(attributes);
    }

    /**
     * This method returns the upper left corner of the rectangle.
     * 
     * @return The upper left corner of the rectangle. Never returns 'null'.
     */
    public IColumnVector<Float> getD() {
	return this.d;
    }

    /**
     * This method returns the dimension of the rectangle.
     * 
     * @return The dimneiosn of the rectangle. Never returns 'null'.
     */
    public IColumnVector<Float> getP() {
	return this.p;
    }

    /**
     * The attributes of the rectangle.
     * 
     * @return The attributes of the rectangle. Never returns 'null' but is
     *         maybe empty.
     */
    public Map<String, Object> getAttributes() {
	return new HashMap<String, Object>(this.attributes);
    }

    @Override
    public boolean equals(final Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Rectangle other = (Rectangle) obj;
	if (this.p != other.p && (this.p == null || !this.p.equals(other.p))) {
	    return false;
	}
	if (this.d != other.d && (this.d == null || !this.d.equals(other.d))) {
	    return false;
	}
	if (this.attributes != other.attributes
		&& (this.attributes == null || !this.attributes.equals(other.attributes))) {
	    return false;
	}
	return true;
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 53 * hash + (this.p != null ? this.p.hashCode() : 0);
	hash = 53 * hash + (this.d != null ? this.d.hashCode() : 0);
	hash = 53 * hash + (this.attributes != null ? this.attributes.hashCode() : 0);
	return hash;
    }

    /**
     * 'draws' rectangle
     * 
     * @param frameBuffer
     */
    public void fill(final Color[][] frameBuffer) {
	if (frameBuffer == null) {
	    throw new NullPointerException("'frameBuffer' must not be 'null'");
	}
	Color c = (getAttributes().containsKey("color")) ? (Color) getAttributes().get("color") : Color.white;
	for (int i = getP().get(0, 0).intValue(); i < getP().get(0, 0).intValue() + getD().get(0, 0).intValue(); ++i) {
	    for (int j = getP().get(1, 0).intValue(); j < getP().get(1, 0) + getD().get(1, 0); ++j) {
		frameBuffer[i][j] = c;
	    }
	}
    }

    /**
     * outlines rectangle 
     * 
     * @param frameBuffer
     */
    public void outline(final Color[][] frameBuffer) {
	if (frameBuffer == null) {
	    throw new NullPointerException("'frameBuffer' must not be 'null'");
	}
	IColumnVector<Float> pA = new ColumnVector(p.get(0, 0), p.get(1, 0));
	IColumnVector<Float> pB = new ColumnVector(p.get(0, 0) + d.get(0, 0), p.get(1, 0));
	IColumnVector<Float> pC = new ColumnVector(p.get(0, 0) + d.get(0, 0), p.get(1, 0) + d.get(1, 0));
	IColumnVector<Float> pD = new ColumnVector(p.get(0, 0), p.get(1, 0) + d.get(1, 0));
	(new Line(pA, attributes, pB, attributes)).fill(frameBuffer);
	(new Line(pB, attributes, pC, attributes)).fill(frameBuffer);
	(new Line(pC, attributes, pD, attributes)).fill(frameBuffer);
	(new Line(pD, attributes, pA, attributes)).fill(frameBuffer);
    }
}
