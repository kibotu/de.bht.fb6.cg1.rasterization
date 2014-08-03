package de.bht.fb6.cg1.rasterization;

import de.bht.fb6.cg1.math.IColumnVector;
import de.bht.fb6.cg1.rasterization.math.ColumnVector;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains some static methods to load figures from a file.
 *
 * @author Stephan Rehfeld
 */
public class FigureLoader {

    /**
     * This method loads the figures from the given file and returns them as
     * a list of figures.
     *
     *
     * @param file The file where the figures should be loaded from. Must not be 'null'.
     * @return The loaded figures. Never returns 'null'
     */
    public static List< IFigure > loadFromFiles( final File file ) {
        if( file == null ) {
            throw new IllegalArgumentException( "The parameter 'file' must not be 'null'." );
        }
        final List< IFigure > figures = new ArrayList< IFigure >();

        try {
            final BufferedReader reader = new BufferedReader( new FileReader( file ) );
            String currentLine = null;
            while( (currentLine = reader.readLine()) != null ) {
                final String[] elements = currentLine.split( " " );
                if( elements[0].equals( "line" ) ) {
                    figures.add( FigureLoader.reconstructLine( elements ) );
                } else if( elements[0].equals( "circle" ) ) {
                    figures.add( FigureLoader.reconstructCircle( elements ) );
                } else if( elements[0].equals( "triangle" ) ) {
                    figures.add( FigureLoader.reconstructTriangle( elements ) );
                } else if( elements[0].equals( "polygon" ) ) {
                    figures.add( FigureLoader.reconstructPolygon( elements ) );
                } else if( elements[0].equals( "rectangle" ) ) {
                    figures.add( FigureLoader.reconstructRectangle( elements ) );
                }
            }
            reader.close();
        } catch( final FileNotFoundException ex ) {
            Logger.getLogger( FigureLoader.class.getName()).log( Level.SEVERE, null, ex );
        } catch( final IOException ex ) {
            Logger.getLogger( FigureLoader.class.getName()).log( Level.SEVERE, null, ex );
        }

        return figures;
    }

    /**
     * This method reconstructs a Line from a splitted line of the file.
     *
     * @param elements The elements of the splited line of the file. Must not be 'null'.
     * @return The reconstructed Line.
     */
    public static Line reconstructLine( final String[] elements ) {
        if( elements == null ) {
            throw new IllegalArgumentException( "The parameter 'elements' must not be 'null'." );
        }
        final Map< String, Object > attributesP1 = new HashMap< String, Object >();
        final Map< String, Object > attributesP2 = new HashMap< String, Object >();
        IColumnVector< Float > p1 = null;
        IColumnVector< Float > p2 = null;
        final List< Float > digitBuffer = new ArrayList< Float >();
        int i = 1;
        do {
            if( elements[i].equals( "p1" ) ) {
                ++i;
                digitBuffer.clear();
                while( (Character.isDigit( elements[i].charAt( 0 ) ) || (elements[i].charAt( 0 ) == '-') ) ) {
                    digitBuffer.add( Float.parseFloat( elements[i++] ) );
                }
                p1 = new ColumnVector( digitBuffer );
                if( elements[i].equals( "color" ) ) {
                    ++i;
                    final int r = Integer.parseInt( elements[i++] );
                    final int g = Integer.parseInt( elements[i++] );
                    final int b = Integer.parseInt( elements[i++] );
                    attributesP1.put( "color", new Color( r, g, b ) );
                }
            } else if( elements[i].equals( "p2" ) ) {
                ++i;
                digitBuffer.clear();
                while( i < elements.length && (Character.isDigit( elements[i].charAt( 0 ) ) || (elements[i].charAt( 0 ) == '-') ) ) {
                    digitBuffer.add( Float.parseFloat( elements[i++] ) );
                }
                p2 = new ColumnVector( digitBuffer );
                if( i < elements.length && elements[i].equals( "color" ) ) {
                    ++i;
                    final int r = Integer.parseInt( elements[i++] );
                    final int g = Integer.parseInt( elements[i++] );
                    final int b = Integer.parseInt( elements[i++] );
                    attributesP2.put( "color", new Color( r, g, b ) );
                }
            }
        } while( i < elements.length );
        return new Line( p1, attributesP1, p2, attributesP2 );
    }

    /**
     * This method reconstructs a Circle from a splitted line of the file.
     *
     * @param elements The elements of the splited line of the file. Must not be 'null'.
     * @return The reconstructed Circle.
     */
    public static Circle reconstructCircle( final String[] elements ) {
        if( elements == null ) {
            throw new IllegalArgumentException( "The parameter 'elements' must not be 'null'." );
        }
        final Map< String, Object > attributesP = new HashMap< String, Object >();
        final Map< String, Object > attributesR = new HashMap< String, Object >();
        IColumnVector< Float > p = null;
        float radius =  0f;
        final List< Float > digitBuffer = new ArrayList< Float >();
        int i = 1;
        do {
            if( elements[i].equals( "p" ) ) {
                ++i;
                digitBuffer.clear();
                while( (Character.isDigit( elements[i].charAt( 0 ) ) || (elements[i].charAt( 0 ) == '-') ) ) {
                    digitBuffer.add( Float.parseFloat( elements[i++] ) );
                }
                p = new ColumnVector( digitBuffer );
                if( elements[i].equals( "color" ) ) {
                    ++i;
                    final int r = Integer.parseInt( elements[i++] );
                    final int g = Integer.parseInt( elements[i++] );
                    final int b = Integer.parseInt( elements[i++] );
                    attributesP.put( "color", new Color( r, g, b ) );
                }
            } else if( elements[i].equals( "r" ) ) {
                ++i;
                radius = Float.parseFloat( elements[ i++ ] );
                if( i < elements.length && elements[i].equals( "color" ) ) {
                    ++i;
                    final int r = Integer.parseInt( elements[i++] );
                    final int g = Integer.parseInt( elements[i++] );
                    final int b = Integer.parseInt( elements[i++] );
                    attributesR.put( "color", new Color( r, g, b ) );
                }
            }
        } while( i < elements.length );
        return new Circle( p, attributesP, radius, attributesR );
    }

    /**
     * This method reconstructs a Triangle from a splitted line of the file.
     *
     * @param elements The elements of the splited line of the file. Must not be 'null'.
     * @return The reconstructed Triangle.
     */
    public static Triangle reconstructTriangle( final String[] elements ) {
        if( elements == null ) {
            throw new IllegalArgumentException( "The parameter 'elements' must not be 'null'." );
        }
        final Map< String, Object > attributesV1 = new HashMap< String, Object >();
        final Map< String, Object > attributesV2 = new HashMap< String, Object >();
        final Map< String, Object > attributesV3 = new HashMap< String, Object >();
        IColumnVector< Float > v1 = null;
        IColumnVector< Float > v2 = null;
        IColumnVector< Float > v3 = null;
        final List< Float > digitBuffer = new ArrayList< Float >();
        int i = 1;
        do {
            if( elements[i].equals( "v1" ) ) {
                ++i;
                digitBuffer.clear();
                while( (Character.isDigit( elements[i].charAt( 0 ) ) || (elements[i].charAt( 0 ) == '-') ) ) {
                    digitBuffer.add( Float.parseFloat( elements[i++] ) );
                }
                v1 = new ColumnVector( digitBuffer );
                if( elements[i].equals( "color" ) ) {
                    ++i;
                    final int r = Integer.parseInt( elements[i++] );
                    final int g = Integer.parseInt( elements[i++] );
                    final int b = Integer.parseInt( elements[i++] );
                    attributesV1.put( "color", new Color( r, g, b ) );
                }
            } else if( elements[i].equals( "v2" ) ) {
                ++i;
                digitBuffer.clear();
                while( i < elements.length && (Character.isDigit( elements[i].charAt( 0 ) ) || (elements[i].charAt( 0 ) == '-') ) ) {
                    digitBuffer.add( Float.parseFloat( elements[i++] ) );
                }
                v2 = new ColumnVector( digitBuffer );
                if( i < elements.length && elements[i].equals( "color" ) ) {
                    ++i;
                    final int r = Integer.parseInt( elements[i++] );
                    final int g = Integer.parseInt( elements[i++] );
                    final int b = Integer.parseInt( elements[i++] );
                    attributesV2.put( "color", new Color( r, g, b ) );
                }
            } else if( elements[i].equals( "v3" ) ) {
                ++i;
                digitBuffer.clear();
                while( i < elements.length && (Character.isDigit( elements[i].charAt( 0 ) ) || (elements[i].charAt( 0 ) == '-') ) ) {
                    digitBuffer.add( Float.parseFloat( elements[i++] ) );
                }
                v3 = new ColumnVector( digitBuffer );
                if( i < elements.length && elements[i].equals( "color" ) ) {
                    ++i;
                    final int r = Integer.parseInt( elements[i++] );
                    final int g = Integer.parseInt( elements[i++] );
                    final int b = Integer.parseInt( elements[i++] );
                    attributesV3.put( "color", new Color( r, g, b ) );
                }
            }
        } while( i < elements.length );
        return new Triangle( v1, attributesV1, v2, attributesV2, v3, attributesV3 );
    }

    /**
     * This method reconstructs a Polygon from a splitted line of the file.
     *
     * @param elements The elements of the splited line of the file. Must not be 'null'.
     * @return The reconstructed Polygon.
     */
    public static Polygon reconstructPolygon( final String[] elements ) {
        if( elements == null ) {
            throw new IllegalArgumentException( "The parameter 'elements' must not be 'null'." );
        }
        final List< Map< String, Object > > attributes = new ArrayList< Map< String, Object > >();
        final List< IColumnVector< Float > > vertices = new ArrayList< IColumnVector< Float > >();
        final List< Float > digitBuffer = new ArrayList< Float >();
        int i = 1;
        do {
            if( elements[i].equals( "v" ) ) {
                ++i;
                digitBuffer.clear();
                while( i < elements.length && (Character.isDigit( elements[i].charAt( 0 ) ) || (elements[i].charAt( 0 ) == '-') ) ) {
                    digitBuffer.add( Float.parseFloat( elements[i++] ) );
                }
                vertices.add( new ColumnVector( digitBuffer ) );
                final Map< String, Object > a = new HashMap< String, Object >();
                if( i < elements.length && elements[i].equals( "color" ) ) {
                    ++i;
                    final int r = Integer.parseInt( elements[i++] );
                    final int g = Integer.parseInt( elements[i++] );
                    final int b = Integer.parseInt( elements[i++] );
                    a.put( "color", new Color( r, g, b ) );
                }
                attributes.add( a );
            }
        } while( i < elements.length );
        return new Polygon( vertices, attributes );
    }

    /**
     * This method reconstructs a Rectangle from a splitted line of the file.
     *
     * @param elements The elements of the splited line of the file. Must not be 'null'.
     * @return The reconstructed Rectangle.
     */
    public static Rectangle reconstructRectangle( final String[] elements ) {
        if( elements == null ) {
            throw new IllegalArgumentException( "The parameter 'elements' must not be 'null'." );
        }
        IColumnVector< Float > p = null;
        IColumnVector< Float > d = null;
        final Map< String, Object > a = new HashMap< String, Object >();
        final List< Float > digitBuffer = new ArrayList< Float >();
        int i = 1;
        do {
            if( elements[i].equals( "p" ) ) {
                ++i;
                digitBuffer.clear();
                while( i < elements.length && (Character.isDigit( elements[i].charAt( 0 ) ) || (elements[i].charAt( 0 ) == '-') ) ) {
                    digitBuffer.add( Float.parseFloat( elements[i++] ) );
                }
                p = new ColumnVector( digitBuffer );
            } else if( elements[i].equals( "d" ) ) {
                ++i;
                digitBuffer.clear();
                while( i < elements.length && (Character.isDigit( elements[i].charAt( 0 ) ) || (elements[i].charAt( 0 ) == '-') ) ) {
                    digitBuffer.add( Float.parseFloat( elements[i++] ) );
                }
                d = new ColumnVector( digitBuffer );
            } else if( elements[i].equals( "color" ) ) {
                ++i;
                final int r = Integer.parseInt( elements[i++] );
                final int g = Integer.parseInt( elements[i++] );
                final int b = Integer.parseInt( elements[i++] );
                a.put( "color", new Color( r, g, b ) );
            }
        } while( i < elements.length );
        return new Rectangle( p, d, a );
    }

}
