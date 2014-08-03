package de.bht.fb6.cg1.rasterization;

import java.awt.Color;

/**
 * This base class provides come common methods for all rasterizer.
 *
 * @author Stephan Rehfeld
 */
public interface IRasterizer {

    /**
     * This method is called before a new frame is created. It should reset
     * internal states like a Z-Buffer.
     */
    public void reset();

    /**
     * An implementation of this method should rasterize the given figure
     * and set the pixel belongs to the figure in the frameBuffer.
     *
     * @param figure The figure to rasterize.
     * @param frameBuffer The frame buffer where the pixel should be set.
     */
    public void rasterize( final IFigure figure, final Color[][] frameBuffer );


}
