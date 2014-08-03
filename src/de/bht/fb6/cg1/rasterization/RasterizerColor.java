package de.bht.fb6.cg1.rasterization;

import java.awt.Color;

public class RasterizerColor extends Color {

    private static final long serialVersionUID = 8495971380447226233L;

    public RasterizerColor(float r, float g, float b, float a) {
	super(r, g, b, a);
    }

    /**
     * returns a color between start and end colors based upon percent of the
     * range
     * 
     * @param beginColor
     * @param endColor
     * @param percent
     * @return graded color
     */
    public static Color gradedValue(final Color beginColor, final Color endColor, final double percent) {
	if (beginColor == null) {
	    throw new NullPointerException("'beginColor' must not be 'null'");
	}
	if (endColor == null) {
	    throw new NullPointerException("'endColor' must not be 'null'");
	}
	final int red = beginColor.getRed() + (int) (percent * (endColor.getRed() - beginColor.getRed()));
	final int blue = beginColor.getBlue() + (int) (percent * (endColor.getBlue() - beginColor.getBlue()));
	final int green = beginColor.getGreen() + (int) (percent * (endColor.getGreen() - beginColor.getGreen()));
	return new Color(red, green, blue);
    }
}
