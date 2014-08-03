package de.bht.fb6.cg1.rasterization;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.List;

/**
 * This uses a rasterizer to draw the figures.
 *
 * @author Stephan Rehfeld
 */
public class RasterCanvas extends Canvas {

    private static final long serialVersionUID = 7071414597273591203L;

    /**
     * The list of figures to draw.
     */
    private final List< IFigure > figures;

    /**
     * The rasterizer that should be used to draw the figures.
     */
    private final IRasterizer rasterizer;

    /**
     * This constructor creates a new RasterCanvas.
     *
     * @param figures A list of figures to rasterize. Must not be 'null'
     * @param rasterizer The rasterizer. Must not be 'null'.
     */
    public RasterCanvas( final List< IFigure > figures, final IRasterizer rasterizer ) {
        if( figures == null ) {
            throw new IllegalArgumentException( "The paraneter 'figures' must not be 'null'!" );
        }
        if( rasterizer == null ) {
            throw new IllegalArgumentException( "The paraneter 'rasterizer' must not be 'null'!" );
        }
        this.figures = figures;
        this.rasterizer = rasterizer;
    }
    
    @Override
    public void paint( final Graphics g ) {
        super.paint( g );
        
        final Color[][] frameBuffer = new Color[this.getWidth()][this.getHeight()];
        for( int i = 0; i < this.getWidth(); ++i ) {
            for( int j = 0; j < this.getHeight(); ++j ) {
                frameBuffer[i][j] = Color.black;
            }
        }
        for( final IFigure figure : this.figures ) {
            this.rasterizer.rasterize( figure, frameBuffer );
        }
        final BufferedImage image = new BufferedImage( this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB );
        final WritableRaster raster = image.getRaster();
        final ColorModel model = image.getColorModel();

        for( int i = 0; i < this.getWidth(); ++i ) {
            for( int j = 0; j < this.getHeight(); ++j ) {
                raster.setDataElements( i, j, model.getDataElements( frameBuffer[i][j].getRGB(), null ) );
            }
        }

        g.drawImage(image, 0, 0, this);

    }

}
