package de.bht.fb6.cg1.rasterization;

import java.awt.Color;

/**
 * 
 * A rasterizer.
 * 
 * @author Stephan Rehfeld
 */
public class Rasterizer implements IRasterizer {
    @Override
    public void reset() {

    }

    @Override
    public void rasterize(final IFigure figure, final Color[][] frameBuffer) {
	if (figure instanceof Rectangle) {
	    System.out.println("rectangle");
	    final Rectangle rectangle = (Rectangle) figure;
	    rectangle.fill(frameBuffer);
	}
	if (figure instanceof Line) {
	    System.out.println("line");
	    final Line line = (Line) figure;
	    line.fill(frameBuffer);
	}
	if (figure instanceof Circle) {
	    System.out.println("circle");
	    final Circle circle = (Circle) figure;
	    circle.fill(frameBuffer);
	}
	if (figure instanceof Triangle) {
	    System.out.println("triangle");
	    final Triangle triangle = (Triangle) figure;
	    triangle.fill(frameBuffer);
	}
	if (figure instanceof Polygon) {
	    System.out.println("Polygon");
	    final Polygon polygon = (Polygon) figure;
	    polygon.fillConvex(frameBuffer);
//	    polygon.scanFill(frameBuffer);
	}
    }
}
