package de.bht.fb6.cg1.rasterization;

import de.bht.fb6.cg1.math.IColumnVector;
import de.bht.fb6.cg1.rasterization.math.ColumnVector;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class represets a polygon. It is used in the rasterization exercise. The
 * polygon it parametrized by its vertices. Attributes can be applied to each
 * vertex of the polygon.
 * 
 * @author Stephan Rehfeld
 */
public class Polygon implements IFigure {

    /**
     * The vertices of the polygon.
     */
    final List<IColumnVector<Float>> vertices;

    /**
     * The attributes of the vertices of the polygon.
     */
    final List<Map<String, Object>> attributes;

    /**
     * represents all triangles the polygon consists of
     */
    final List<Triangle> triangles;

    /**
     * represents all vertices that are pointed to the inside of the polygon
     */
    final List<IColumnVector<Float>> nonConvexVertices;

    /**
     * Orientation of polygon: true = clockwise, false = counterclockwise
     */
    final boolean isCw;

    /**
     * This constructor creates a new polygon with given vertices and without
     * attributes.
     * 
     * @param vertices
     *            The vertices of the of the polygon. Must not be 'null'. Must
     *            not contain 'null'.
     */
    Polygon(final List<IColumnVector<Float>> vertices) {
	this(vertices, new ArrayList<Map<String, Object>>());
    }

    /**
     * This constructor creates a new polygon with given vertices an optional
     * attributes for each vertex.
     * 
     * @param vertices
     *            The vertices of the of the polygon. Must not be 'null'. Must
     *            not contain 'null'.
     * @param attributes
     *            The attributes for each vertex. Must not be 'null' Must not
     *            contain 'null' but maybe empty maps.
     */
    Polygon(final List<IColumnVector<Float>> vertices, final List<Map<String, Object>> attributes) {
	if (vertices == null) {
	    throw new IllegalArgumentException("The parameter 'vertices' must not be null!");
	}
	for (final IColumnVector<Float> v : vertices) {
	    if (v == null) {
		throw new IllegalArgumentException("No vertex must be 'null'!");
	    }
	}
	if (attributes == null) {
	    throw new IllegalArgumentException("The parameter 'attributes' must not be null!");
	}
	this.vertices = new ArrayList<IColumnVector<Float>>(vertices);
	this.attributes = new ArrayList<Map<String, Object>>();
	for (final Map<String, Object> m : attributes) {
	    if (m == null) {
		throw new IllegalArgumentException("No attribues map must be null!");
	    }
	    this.attributes.add(new HashMap<String, Object>(m));
	}
	this.triangles = new LinkedList<Triangle>();
	this.nonConvexVertices = new LinkedList<IColumnVector<Float>>();
	this.isCw = getPolyOrientation();
	calcNonConvexPoints();
	kong();
    }

    /**
     * This method returns the attributes of the vertices.
     * 
     * @return The attributes of the vertices. Never returns 'null'.
     * 
     */
    public List<Map<String, Object>> getAttributes() {
	final List<Map<String, Object>> r = new ArrayList<Map<String, Object>>();
	for (final Map<String, Object> m : this.attributes) {
	    r.add(new HashMap<String, Object>(m));
	}
	return r;
    }

    /**
     * This method returns the vertices.
     * 
     * @return The vertices. Never returns 'null'.
     * 
     */
    public List<IColumnVector<Float>> getVertices() {
	return new ArrayList<IColumnVector<Float>>(vertices);
    }
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((vertices == null) ? 0 : vertices.hashCode());
	result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
	result = prime * result + (isCw ? 1231 : 1237);
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof Polygon)) {
	    return false;
	}
	Polygon other = (Polygon) obj;
	if (attributes == null) {
	    if (other.attributes != null) {
		return false;
	    }
	} else if (!attributes.equals(other.attributes)) {
	    return false;
	}
	if (isCw != other.isCw) {
	    return false;
	}
	if (vertices == null) {
	    if (other.vertices != null) {
		return false;
	    }
	} else if (!vertices.equals(other.vertices)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Polygon [vertices=" + vertices + ", attributes=" + attributes + ", isClockWise=" + isCw + "]";
    }

    /**
     * checks if vertices are connected clockwise or counter clockwise and
     * updates 'isCw'
     * 
     * @return true if clockwise
     */
    protected boolean getPolyOrientation() {
	if (vertices.size() < 3)
	    return true;

	// first find point with minimum x-coord - if there are several ones
	// take the one with maximal y-coord
	int index = 0; // index of point in vector to find
	IColumnVector<Float> currentPoint = vertices.get(0);
	for (int i = 1; i < vertices.size(); i++) {
	    if (vertices.get(i).get(0, 0) < currentPoint.get(0, 0)) {
		currentPoint = vertices.get(i);
		index = i;
	    } else if (vertices.get(i).get(0, 0) == currentPoint.get(0, 0)
		    && vertices.get(i).get(1, 0) > currentPoint.get(1, 0)) {
		currentPoint = vertices.get(i);
		index = i;
	    }
	}
	// get vector from index-1 to index
	final IColumnVector<Float> previousPoint = (index == 0) ? vertices.get(vertices.size() - 1) : vertices
		.get(index - 1);
	// v1 = p(minX, maxY) - prevPoint(x,y)
	final IColumnVector<Float> v1 = new ColumnVector(currentPoint.get(0, 0) - previousPoint.get(0, 0), currentPoint.get(1, 0) - previousPoint.get(1, 0));
	// get next point
	final IColumnVector<Float> nextPoint = (index == vertices.size() - 1) ? vertices.get(0) : vertices
		.get(index + 1);
	// get orientation
	// next(x) * v1(y) - next(y)*v1(x) + v1(x) * prev(y) - v1(y) * prev(x)
	// result = (x+1) * (y) - (y+1)*(x) + (x) * (y-1) - (y)*(x-1)
	final float result = nextPoint.get(0, 0) * v1.get(1, 0) - nextPoint.get(1, 0) * v1.get(0, 0) + v1.get(0, 0)
		* previousPoint.get(1, 0) - v1.get(1, 0) * previousPoint.get(0, 0);
	return (result <= 0 ? true : false);
    }

    /**
     * updates list of non convex vertices
     */
    protected void calcNonConvexPoints() {
	// safety check, with less than 4 points we have to do nothing
	if (vertices.size() <= 3) {
	    return;
	}
	// actual three points
	IColumnVector<Float> p;
	IColumnVector<Float> v;
	IColumnVector<Float> u;
	// result value of test function
	float result = 0;
	for (int i = 0; i < vertices.size() - 1; i++) {
	    /** p **/
	    p = vertices.get(i);
	    /** temp **/
	    IColumnVector<Float> tmp = vertices.get(i + 1);
	    /** v 
	     * v.x = tmp.x - p.x;
	     * v.y = tmp.y - p.y;
	     */
	    v = new ColumnVector(tmp.get(0, 0) - p.get(0, 0),tmp.get(1, 0) - p.get(1, 0));
	    /** u **/
	    u = (i == vertices.size() - 2) ? vertices.get(0) : vertices.get(i + 2);
	    // res = u.x * v.y - u.y * v.x + v.x * p.y - v.y * p.x;
	    result = u.get(0, 0) * v.get(1, 0) - u.get(1, 0) * v.get(0, 0) + v.get(0, 0) * p.get(1, 0) - v.get(1, 0)
		    * p.get(0, 0);
	    // note: cw means res/newres is <= 0
	    if ((result > 0 && isCw) || (result <= 0 && !isCw)) {
		nonConvexVertices.add(tmp);
	    }
	}
    }

    /**
     * gets index of list (backwards possible)
     * 
     * @param <T>
     * @param index
     * @param offset
     * @param list
     * @return real index
     */
    protected <T> int getIndex(final int index, final int offset, final List<T> list) {
	if (list == null) {
	    throw new NullPointerException("'list' must not be 'null'");
	}
	return (index + offset >= list.size()) ? list.size() - (index + offset) : (index + offset < 0) ? list.size()
		+ (index + offset) : index + offset;
    }

    /**
     * checks if a vertice lies within a polygon
     * 
     * @param vertice
     * @return true if vertice lies within polygon
     */
    public boolean pointInPolygon(final IColumnVector<Float> point) {
	if (point == null) {
	    throw new NullPointerException("'point' must not be 'null'");
	}
	return pointInPolygon(point.get(0, 0).intValue(), point.get(1, 0).intValue());
    }

    /**
     * checks if a point(px, py) lies within a polygon
     * 
     * @param pX
     * @param pY
     * @return true if point lies within polygon
     */
    protected boolean pointInPolygon(final int pX, final int pY) {

	int i, j = vertices.size() - 1;
	boolean oddNodes = false;

	for (i = 0; i < vertices.size(); i++) {
	    // y0 < pY && yLast >= pY || yLast < pY && y0 >= pY
	    if (vertices.get(i).get(1, 0) < pY && vertices.get(j).get(1, 0) >= pY || vertices.get(j).get(1, 0) < pY
		    && vertices.get(i).get(1, 0) >= pY) {
		// x0 + y0 / yLast - y0 * xLast - x0 < pX
		if (vertices.get(i).get(0, 0) + (pY - vertices.get(i).get(1, 0))
			/ (vertices.get(j).get(1, 0) - vertices.get(i).get(1, 0))
			* (vertices.get(j).get(0, 0) - vertices.get(i).get(0, 0)) < pX) {
		    oddNodes = !oddNodes;
		}
	    }
	    j = i;
	}

	return oddNodes;
    }

    /**
     * check if 3 consecutive vectors are an ear
     * 
     * @param p1
     * @param p2
     * @param p3
     * @return true if is ear
     */
    public boolean isEar(final IColumnVector<Float> p1, final IColumnVector<Float> p2, final IColumnVector<Float> p3) {
	if (p1 == null) {
	    throw new NullPointerException("'p1' must not be 'null'");
	}
	if (p2 == null) {
	    throw new NullPointerException("'p2' must not be 'null'");
	}
	if (p3 == null) {
	    throw new NullPointerException("'p3' must not be 'null'");
	}
	// not convex, bye
	if (!(isConvex(p1, p2, p3))) {
	    return false;
	}
	// iterate over all konkav points and check if one of them lies inside
	// the given triangle
	for (int i = 0; i < nonConvexVertices.size(); i++) {
	    if (Triangle.isInside(p1, p2, p3, nonConvexVertices.get(i))) {
		return false;
	    }
	}
	return true;
    }

    /**
     * checks if 3 vecites are convex
     * 
     * @param p1
     * @param p2
     * @param p3
     * @return true if convex
     */
    private boolean isConvex(final IColumnVector<Float> p1, final IColumnVector<Float> p2, final IColumnVector<Float> p3) {
	if (p1 == null) {
	    throw new NullPointerException("'p1' must not be 'null'");
	}
	if (p2 == null) {
	    throw new NullPointerException("'p2' must not be 'null'");
	}
	if (p3 == null) {
	    throw new NullPointerException("'p3' must not be 'null'");
	}

	// Point v = new Point(p2.x - p1.x, p2.y - p1.y);
	final List<Float> vList = new LinkedList<Float>();
	vList.add(p2.get(0, 0) - p1.get(0, 0));
	vList.add(p2.get(1, 0) - p1.get(1, 0));
	final IColumnVector<Float> v = new ColumnVector(vList);

	// int res = p3.x * v.y - p3.y * v.x + v.x * p1.y - v.y * p1.x;
	final float result = p3.get(0, 0) * v.get(1, 0) - p3.get(1, 0) * v.get(0, 0) + v.get(0, 0) * p1.get(1, 0)
		- v.get(1, 0) * p1.get(0, 0);

	return !((result > 0 && isCw) || (result <= 0 && !isCw));
    }

    /**
     * "drawing" convex polygon as triangles
     * 
     * @param frameBuffer
     */
    public void fillConvex(final Color[][] frameBuffer) {
	if (frameBuffer == null) {
	    throw new NullPointerException("'frameBuffer' must not be 'null'");
	}
	for (Triangle triangle : triangles) {
	    triangle.fill(frameBuffer);
	}
    }

    /**
     * scan fill - algorythm
     * 
     * @param frameBuffer
     */
    public void scanFill(final Color[][] frameBuffer) {
	if (frameBuffer == null) {
	    throw new NullPointerException("'frameBuffer' must not be 'null'");
	}
	// gets bounds of the polygon
	int minX = 0;
	int minY = 0;
	int maxX = 0;
	int maxY = 0;
	for (int i = 0; i < vertices.size(); ++i) {
	    minX = (int) Math.min(minX, vertices.get(i).get(0, 0));
	    minY = (int) Math.min(minY, vertices.get(i).get(1, 0));
	    maxX = (int) Math.max(maxX, vertices.get(i).get(0, 0));
	    maxY = (int) Math.max(maxY, vertices.get(i).get(1, 0));
	}
	// fills frameBuffer if a point(x,y) is within the polygon
	for (int x = minX; x < maxX; ++x) {
	    for (int y = minY; y < maxY; ++y) {
		if (pointInPolygon(x, y)) {
		    frameBuffer[x][y] = Color.white;
		}
	    }
	}
    }

    /**
     * The actual Kong's Triangulation Algorithm
     * 
     * cuts off all ears of the polygon until only 3 points are left every ear
     * is to be added to the triangle list
     */
    public void kong() {
	if (vertices.size() <= 3) {
	    return;
	}
	// keep original polygon
	final List<IColumnVector<Float>> vert = new LinkedList<IColumnVector<Float>>();
	vert.addAll(vertices);
	final List<Map<String, Object>> attr = new LinkedList<Map<String, Object>>();
	attr.addAll(attributes);

	int index = 1;
	while (vert.size() > 3) {
	    if (isEar(vert.get(getIndex(index, -1, vert)), vert.get(index), vert.get(getIndex(index, 1, vert)))) {
		// cut ear
		triangles.add(new Triangle(vert.get(getIndex(index, -1, vert)), attr.get(getIndex(index, -1, vert)),
			vert.get(index), attr.get(index), vert.get(getIndex(index, 1, vert)), attr.get(getIndex(index,
				1, vert))));
		vert.remove(vert.get(index));
		attr.remove(attr.get(index));
		index = getIndex(index, -1, vert);
	    } else {
		index = getIndex(index, 1, vert);
	    }
	}
	// add last triangle
	triangles.add(new Triangle(vert.get(0), attr.get(0), vert.get(1), attr.get(1), vert.get(2), attr.get(2)));
    }

    /**
     * outlines the triangulated polygon
     * 
     * @param frameBuffer
     */
    public void outlineTriangles(final Color[][] frameBuffer) {
	if (frameBuffer == null) {
	    throw new NullPointerException("'frameBuffer' must not be 'null'");
	}
	for (Triangle triangle : triangles) {
	    triangle.outline(frameBuffer);
	}
    }

    /**
     * outlines the polygon
     * 
     * @param frameBuffer
     */
    public void outline(final Color[][] frameBuffer) {
	if (frameBuffer == null) {
	    throw new NullPointerException("'frameBuffer' must not be 'null'");
	}
	// "draw" lines
	for (int i = 0; i < vertices.size() - 1; ++i) {
	    (new Line(vertices.get(i), attributes.get(i), vertices.get(i + 1), attributes.get(i + 1)))
		    .fill(frameBuffer);
	}
	// line: from last to first
	(new Line(vertices.get(0), attributes.get(0), vertices.get(vertices.size() - 1),
		attributes.get(vertices.size() - 1))).fill(frameBuffer);
    }

    /**
     * adds a vertices and its attribute to the polygon at the index position
     * 
     * @param vertice
     * @param attribute
     * @param index
     */
    public void addVertice(final IColumnVector<Float> vertice, final Map<String, Object> attribute, final int index) {
	if (vertice == null) {
	    throw new NullPointerException("'vertice' must not be 'null'");
	}
	vertices.add(index, vertice);
	attributes.add(index, attribute);
	calcNonConvexPoints();
	kong();
    }

    /**
     * adds a list of vertices and its attributes to the polygon at the index
     * position
     * 
     * @param vertices
     * @param attributes
     * @param index
     */
    public void addVertices(final List<IColumnVector<Float>> vertices, final List<Map<String, Object>> attributes,
	    final int index) {
	if (vertices == null) {
	    throw new NullPointerException("'vertices' must not be 'null'");
	}
	if (attributes == null) {
	    throw new NullPointerException("'attributes' must not be 'null'");
	}
	if (index >= vertices.size()) {
	    throw new IndexOutOfBoundsException("'index' must not be greater than amount of polygon vertices");
	}
	this.vertices.addAll(index, vertices);
	this.attributes.addAll(index, attributes);
	calcNonConvexPoints();
	kong();
    }
}