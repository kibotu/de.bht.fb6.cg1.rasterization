package de.bht.fb6.cg1.rasterization;

import java.io.File;
import java.util.List;

/**
 * The starter class for the rasterization exercise of computer graphics 1
 * at the Beuth University of Applied Sciences
 *
 * @author Stephan Rehfeld
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final List< IFigure > figures = FigureLoader.loadFromFiles( new File("figures/circles") );
        System.out.println( "For Breakpoint" );
        new RasterizerWindow( figures, new OutlineRasterizer() );
        new RasterizerWindow( figures, new Rasterizer() );
    }
}
