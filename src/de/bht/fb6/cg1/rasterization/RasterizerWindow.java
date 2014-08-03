package de.bht.fb6.cg1.rasterization;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

/**
 * This is a frame that contains a RasterCanvas.
 * It's closing when the (x) is hit.
 *
 * @author Stephan Rehfeld
 */
public class RasterizerWindow extends Frame {

    private static final long serialVersionUID = -484037154792795425L;

    /**
     * This constructor creates a new RasterWindow.
     *
     * @param figures A list of figures to rasterize. Must not be 'null'
     * @param rasterizer The rasterizer. Must not be 'null'.
     */
    public RasterizerWindow( final List< IFigure > figures, final IRasterizer rasterizer ) {
        super("Rasterizer");
        if( figures == null ) {
            throw new IllegalArgumentException( "The paraneter 'figures' must not be 'null'!" );
        }
        if( rasterizer == null ) {
            throw new IllegalArgumentException( "The paraneter 'rasterizer' must not be 'null'!" );
        }
        this.setSize( 800, 600 );
        this.addWindowListener( new WindowListener() {

            @Override
            public void windowOpened( final WindowEvent e) {

            }

            @Override
            public void windowClosing( final WindowEvent e) {
                System.exit( 0 );
            }

            @Override
            public void windowClosed( final WindowEvent e) {

            }

            @Override
            public void windowIconified( final WindowEvent e) {

            }

            @Override
            public void windowDeiconified( final WindowEvent e) {

            }

            @Override
            public void windowActivated( final WindowEvent e) {

            }

            @Override
            public void windowDeactivated( final WindowEvent e) {

            }

        });

        this.setVisible( true );
        final Canvas c = new RasterCanvas( figures, rasterizer );
        c.setSize( this.getWidth(), this.getHeight() );
        c.setLocation( 0, 0 );
        this.setLayout( new GridLayout( 1, 1 ));
        this.add( c );
    }

}
