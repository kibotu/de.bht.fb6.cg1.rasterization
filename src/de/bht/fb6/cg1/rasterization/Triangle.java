package de.bht.fb6.cg1.rasterization;

import de.bht.fb6.cg1.math.IColumnVector;
import de.bht.fb6.cg1.rasterization.math.ColumnVector;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represets a triangle. It is used in the rasterization exercise.
 * The triangle it parametrized by three vertices. Attributes can be applied to
 * each vertex of the triangle.
 * 
 * @author Stephan Rehfeld
 */
public class Triangle implements IFigure {

    /**
     * The first vertex of the triangle.
     */
    final IColumnVector<Float> v1;

    /**
     * The second vertex of the triangle.
     */
    final IColumnVector<Float> v2;

    /**
     * The third vertex of the triangle.
     */
    final IColumnVector<Float> v3;

    /**
     * The attributes of the first vertex of the triangle.
     */
    final Map<String, Object> attributesV1;

    /**
     * The attributes of the second vertex of the triangle.
     */
    final Map<String, Object> attributesV2;

    /**
     * The attributes of the third vertex of the triangle.
     */
    final Map<String, Object> attributesV3;

    public Triangle(final IColumnVector<Float> v1, final IColumnVector<Float> v2, final IColumnVector<Float> v3) {
	this(v1, new HashMap<String, Object>(), v2, new HashMap<String, Object>(), v3, new HashMap<String, Object>());
    }

    /**
     * constructs the triangle object
     * 
     * @param v1
     * @param attributesV1
     * @param v2
     * @param attributesV2
     * @param v3
     * @param attributesV3
     */
    public Triangle(final IColumnVector<Float> v1, final Map<String, Object> attributesV1,
	    final IColumnVector<Float> v2, final Map<String, Object> attributesV2, final IColumnVector<Float> v3,
	    final Map<String, Object> attributesV3) {
	if (v1 == null) {
	    throw new IllegalArgumentException("The paraneter 'v1' mut not be 'null'!");
	}
	if (v2 == null) {
	    throw new IllegalArgumentException("The paraneter 'v2' mut not be 'null'!");
	}
	if (v3 == null) {
	    throw new IllegalArgumentException("The paraneter 'v3' mut not be 'null'!");
	}
	if (attributesV1 == null) {
	    throw new IllegalArgumentException("The paraneter 'attributesV1' mut not be 'null'!");
	}
	if (attributesV2 == null) {
	    throw new IllegalArgumentException("The paraneter 'attributesV2' mut not be 'null'!");
	}
	if (attributesV3 == null) {
	    throw new IllegalArgumentException("The paraneter 'attributesV3' mut not be 'null'!");
	}
	this.v1 = v1;
	this.v2 = v2;
	this.v3 = v3;
	this.attributesV1 = new HashMap<String, Object>(attributesV1);
	this.attributesV2 = new HashMap<String, Object>(attributesV2);
	this.attributesV3 = new HashMap<String, Object>(attributesV3);
    }

    /**
     * This method returns the attributes of the first vertex of the triangle.
     * 
     * @return The attributes of the first vertex of the triangle. Never returns
     *         'null' but is maybe empty.
     */
    public Map<String, Object> getAttributesV1() {
	return new HashMap<String, Object>(this.attributesV1);
    }

    /**
     * This method returns the attributes of the second vertex of the triangle.
     * 
     * @return The attributes of the second vertex of the triangle. Never
     *         returns 'null' but is maybe empty.
     */
    public Map<String, Object> getAttributesV2() {
	return new HashMap<String, Object>(this.attributesV2);
    }

    /**
     * This method returns the attributes of the third vertex of the triangle.
     * 
     * @return The attributes of the thirs vertex of the triangle. Never returns
     *         'null' but is maybe empty.
     */
    public Map<String, Object> getAttributesV3() {
	return new HashMap<String, Object>(this.attributesV3);
    }

    /**
     * This method returns the first vertex of the triangle.
     * 
     * @return The first vertex of the triangle. Never returns 'null'.
     */
    public IColumnVector<Float> getV1() {
	return this.v1;
    }

    /**
     * This method returns the second vertex of the triangle.
     * 
     * @return The second vertex of the triangle. Never returns 'null'.
     */
    public IColumnVector<Float> getV2() {
	return this.v2;
    }

    /**
     * This method returns the third vertex of the triangle.
     * 
     * @return The second third of the triangle. Never returns 'null'.
     */
    public IColumnVector<Float> getV3() {
	return this.v3;
    }

    @Override
    public boolean equals(final Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Triangle other = (Triangle) obj;
	if (this.v1 != other.v1 && (this.v1 == null || !this.v1.equals(other.v1))) {
	    return false;
	}
	if (this.v2 != other.v2 && (this.v2 == null || !this.v2.equals(other.v2))) {
	    return false;
	}
	if (this.v3 != other.v3 && (this.v3 == null || !this.v3.equals(other.v3))) {
	    return false;
	}
	if (this.attributesV1 != other.attributesV1
		&& (this.attributesV1 == null || !this.attributesV1.equals(other.attributesV1))) {
	    return false;
	}
	if (this.attributesV2 != other.attributesV2
		&& (this.attributesV2 == null || !this.attributesV2.equals(other.attributesV2))) {
	    return false;
	}
	if (this.attributesV3 != other.attributesV3
		&& (this.attributesV3 == null || !this.attributesV3.equals(other.attributesV3))) {
	    return false;
	}
	return true;
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 79 * hash + (this.v1 != null ? this.v1.hashCode() : 0);
	hash = 79 * hash + (this.v2 != null ? this.v2.hashCode() : 0);
	hash = 79 * hash + (this.v3 != null ? this.v3.hashCode() : 0);
	hash = 79 * hash + (this.attributesV1 != null ? this.attributesV1.hashCode() : 0);
	hash = 79 * hash + (this.attributesV2 != null ? this.attributesV2.hashCode() : 0);
	hash = 79 * hash + (this.attributesV3 != null ? this.attributesV3.hashCode() : 0);
	return hash;
    }
    
    /**
     * 'draws' this triangle
     * 
     * @param frameBuffer
     */
    public void fill(final Color[][] frameBuffer) {
	if (frameBuffer == null) {
	    throw new NullPointerException("'frameBuffer' must not be 'null'");
	}
	Color c1 = (getAttributesV1().containsKey("color")) ? (Color) getAttributesV1().get("color") : Color.white;
	Color c2 = (getAttributesV2().containsKey("color")) ? (Color) getAttributesV2().get("color") : Color.white;
	Color c3 = (getAttributesV3().containsKey("color")) ? (Color) getAttributesV3().get("color") : Color.white;

	int ax = getV1().get(0, 0).intValue();
	int ay = getV1().get(1, 0).intValue();
	int bx = getV2().get(0, 0).intValue();
	int by = getV2().get(1, 0).intValue();
	int cx = getV3().get(0, 0).intValue();
	int cy = getV3().get(1, 0).intValue();

	int minX = Math.min(Math.min(ax, bx), cx);
	int minY = Math.min(Math.min(ay, by), cy);
	int maxX = Math.max(Math.max(ax, bx), cx);
	int maxY = Math.max(Math.max(ay, by), cy);

	for (int x = minX; x <= maxX; ++x) {
	    for (int y = minY; y <= maxY; ++y) {

		Float alpha = Line.getLineEquationForTriangle((float) x, (float) y, ax, ay, bx, by, cx, cy);
		Float beta = Line.getLineEquationForTriangle((float) x, (float) y, bx, by, ax, ay, cx, cy);
		// Float gamma = 1.001f - alpha - beta;
		Float gamma = Line.getLineEquationForTriangle((float) x, (float) y, cx, cy, ax, ay, bx, by);

		if ((alpha.compareTo(0f) >= 0) && (alpha.compareTo(1f) <= 1) && (beta.compareTo(0f) >= 0)
			&& (beta.compareTo(1f) <= 1) && (gamma.compareTo(0f) >= 0) && (gamma.compareTo(1f) <= 1)) {
		    int red = (int) (alpha * c1.getRed() + beta * c2.getRed() + gamma * c3.getRed());
		    int green = (int) (alpha * c1.getGreen() + beta * c2.getGreen() + gamma * c3.getGreen());
		    int blue = (int) (alpha * c1.getBlue() + beta * c2.getBlue() + gamma * c3.getBlue());
		    frameBuffer[x][y] = new Color(red, green, blue);
		}
	    }
	}
    }

    @Override
    public String toString() {
	StringBuilder output = new StringBuilder();
	output.append("Triangle [vertices=");

	output.append("(" + v1.get(0, 0) + "|" + v1.get(1, 0) + ")");
	output.append("(" + v2.get(0, 0) + "|" + v2.get(1, 0) + ")");
	output.append("(" + v3.get(0, 0) + "|" + v3.get(1, 0) + ")");

	output.append(", attributes=");

	output.append("(" + attributesV1.get("color") + ")");
	output.append("(" + attributesV2.get("color") + ")");
	output.append("(" + attributesV3.get("color") + ")");

	output.append("]");

	return output.toString();
    }

    /**
     * outlines a triangle
     * 
     * @param frameBuffer
     */
    public void outline(Color[][] frameBuffer) {
	if (frameBuffer == null) {
	    throw new NullPointerException("'frameBuffer' must not be 'null'");
	}
	(new Line(v1, attributesV1, v2, attributesV2)).fill(frameBuffer);
	(new Line(v2, attributesV2, v3, attributesV3)).fill(frameBuffer);
	(new Line(v3, attributesV3, v1, attributesV1)).fill(frameBuffer);
    }

    /**
     * get mid angle of 3 consecutive points
     * 
     * @param ax
     * @param ay
     * @param bx
     * @param by
     * @param cx
     * @param cy
     * @return degree of the mid angle
     */
    public static double getMidAngle(final float ax, final float ay, final float bx, final float by, final float cx, final float cy) {
	// vector(BA)
	final float xa = ax - bx;
	final float ya = ay - by;
	// vector(BC)
	final float xb = cx - bx;
	final float yb = cy - by;
	// skalarprodukt
	final double skalarproduct = (-ya * xb + xa * yb);
	// betrag
	final double betrag = Math.sqrt(xa * xa + ya * ya) * Math.sqrt(xb * xb + yb * yb);
	return Math.toDegrees(Math.asin(skalarproduct / betrag));
    }

    /**
     * checks if a point p lies within 3 consecutive points x,y,z
     * 
     * @param x
     * @param y
     * @param z
     * @param p
     * @return
     */
    public static boolean isInside(final IColumnVector<Float> x, final IColumnVector<Float> y, final IColumnVector<Float> z,
	    final IColumnVector<Float> p) {
	if (x == null) {
	    throw new NullPointerException("'x' must not be 'null'");
	}
	if (y == null) {
	    throw new NullPointerException("'y' must not be 'null'");
	}
	if (z == null) {
	    throw new NullPointerException("'z' must not be 'null'");
	}
	if (p == null) {
	    throw new NullPointerException("'p' must not be 'null'");
	}
	// Point v1 = new Point(y.x - x.x, y.y - x.y);
	IColumnVector<Float> v1 = new ColumnVector(y.get(0, 0) - x.get(0, 0), y.get(1, 0) - x.get(1, 0));
	// Point v2 = new Point(z.x - x.x, z.y - x.y);
	IColumnVector<Float> v2 = new ColumnVector(z.get(0, 0) - x.get(0, 0), z.get(1, 0) - x.get(1, 0));
	// double det = v1.x * v2.y - v2.x * v1.y;
	double determinate = v1.get(0, 0) * v2.get(1, 0) - v2.get(0, 0) * v1.get(1, 0);
	// Point tmp = new Point(p.x - x.x, p.y - x.y);
	IColumnVector<Float> tmp = new ColumnVector(p.get(0, 0) - x.get(0, 0),p.get(1, 0) - x.get(1, 0));
	// double lambda = (tmp.x * v2.y - v2.x * tmp.y) / det;
	double lambda = (tmp.get(0, 0) * v2.get(1, 0) - v2.get(0, 0) * tmp.get(1, 0)) / determinate;
	// double mue = (v1.x * tmp.y - tmp.x*v1.y) / det;
	double mue = (v1.get(0, 0) * tmp.get(1, 0) - tmp.get(0, 0) * v1.get(1, 0)) / determinate;
	return (lambda > 0 && mue > 0 && (lambda + mue) < 1);
    }
}
