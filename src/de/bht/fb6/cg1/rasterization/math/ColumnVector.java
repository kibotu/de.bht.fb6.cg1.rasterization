package de.bht.fb6.cg1.rasterization.math;

import de.bht.fb6.cg1.math.IColumnVector;
import de.bht.fb6.cg1.math.IMatrix;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a dummy implementation to use it in the rasterization exercise.
 * Most methods throw an UnsupportedOperationException.
 *
 * Do not use this implementation outside of this excercise!
 *
 * @author Stephan Rehfeld
 */
public class ColumnVector implements IColumnVector< Float > {

    /**
     * The elements of the column vector.
     */
    private final List< Float > elements;

    /**
     * This constructor creates a new column vector with given elements.
     *
     * @param elements The elements of the column vector. Must not be null.
     */
    public ColumnVector( final List< Float > elements ) {
        if( elements == null ) {
            throw new IllegalArgumentException( "The parameter 'elements' must not be null!" );
        }
        this.elements = new ArrayList< Float >( elements );
    }
    
    /**
     * simple constructor for vector
     * 
     * @param x
     * @param y
     */
    public ColumnVector(final float x, final float y) {
	this(getList(x,y));
    }
    
    /**
     * creates list for simple constructor
     * 
     * @param x
     * @param y
     * @return list with x and y
     */
    protected static List<Float>  getList(final float x, final float y) {
	final List<Float> list = new LinkedList<Float>();
	list.add(x);
	list.add(y);
	return list;
    }

    @Override
    public Float dotProduct( final IColumnVector<Float> vector ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IColumnVector<Float> crossProduct( final IColumnVector<Float>... vectors ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getColumns() {
        return 1;
    }

    @Override
    public int getRows() {
        return this.elements.size();
    }

    @Override
    public Float get( final int row, int column ) {
        return( this.elements.get( row ) );
    }

    @Override
    public IMatrix< Float > add( final IMatrix<Float> matrix ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IMatrix< Float > sub( final IMatrix<Float> matrix ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IMatrix< Float > mult( final IMatrix<Float> matrix ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IMatrix< Float > mult( final Float value ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IMatrix< Float > getTransposed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals( final Object obj ) {
        if( obj == null ) {
            return false;
        }
        if( getClass() != obj.getClass() ) {
            return false;
        }
        final ColumnVector other = (ColumnVector) obj;
        if( this.elements != other.elements && (this.elements == null || !this.elements.equals(other.elements ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.elements != null ? this.elements.hashCode() : 0);
        return hash;
    }

	@Override
	public String toString() {
		return "ColumnVector [elements=" + elements + "]";
	}


}
