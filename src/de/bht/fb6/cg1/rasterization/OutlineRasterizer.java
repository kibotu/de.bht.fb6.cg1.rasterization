package de.bht.fb6.cg1.rasterization;

import java.awt.Color;

public class OutlineRasterizer extends Rasterizer {

    @Override
    public void reset() {

    }

    @Override
    public void rasterize(IFigure figure, Color[][] frameBuffer) {
	if (figure instanceof Rectangle) {
	    Rectangle rectangle = (Rectangle) figure;
	    rectangle.outline(frameBuffer);
	}
	if (figure instanceof Line) {
	    System.out.println("line");
	    final Line line = (Line) figure;
	    line.fill(frameBuffer);
	}
	if (figure instanceof Circle) {
	    System.out.println("circle");
	    final Circle circle = (Circle) figure;
	    circle.outline(frameBuffer);
	}
	if (figure instanceof Triangle) {
	    System.out.println("triangle");
	    final Triangle triangle = (Triangle) figure;
	    triangle.outline(frameBuffer);
	}
	if (figure instanceof Polygon) {
	    System.out.println("polygon");
	    final Polygon polygon = (Polygon) figure;
	    polygon.outline(frameBuffer);
//	    polygon.outlineTriangles(frameBuffer);
	}
    }
}
