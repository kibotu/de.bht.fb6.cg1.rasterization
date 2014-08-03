package de.bht.fb6.cg1.math;

/**
 * This interfrace defines some basic methods for a matrix implementation. A matrix
 * is immutable! Once created the values can't be changed.
 *
 * @author Stephan Rehfeld <rehfeld (-at-) beuth-hochschule.de>
 */
public interface IMatrix< T extends Number > {

	/**
	 * An implementation of this method should return the amount of columns of the matrix.
	 *
	 * @return The amount of columns of the matrix.
	 */
	public int getColumns();

	/**
	 * An implementation of this method should return the amount of rows of the matrix.
	 *
	 * @return The amount of rows of the matrix.
	 */
	public int getRows();

	/**
	 * An implementation of this method should return the value at the given row and column.
	 * Furthermore, it should throw an exceptions derived by RuntimeException if column or row
	 * is smaller than 0 or larger than the dimension of the matrix.
	 *
	 * @param row The row. Must be at least 0 and fit to the maximum of rows of the matrix.
	 * @param column The column. Must be at least 0 and fit to the maximum of columns of the matrix
	 * @return The value at the given column and row.
	 */
	public T get( final int row, final int column );

	/**
	 * An implementation of this method should add the current matrix and the given matrix
	 * and the returns the result. It must not affect the current matrix! The result is returned,
	 * not written in the object!
	 *
	 * Furthermore it should throw exceptions if the boundaries mismatch or 'null' is passed as argument.
	 *
	 * @param matrix The matrix that should be added to the current matrix. Must not be 'null'.
	 * @return The result of the addition.
	 */
	public IMatrix< T > add( final IMatrix< T > matrix );

	/**
	 * An implementation of this method subtract the given matrix from the current matrix
	 * and returns the result. It must not affect the current matrix! The result is returned,
	 * not written in the object!
	 *
	 * Furthermore it should throw exceptions if the boundaries mismatch or 'null' is passed as argument.
	 *
	 * @param matrix The matrix that should be subtracted to the current matrix. Must not be 'null'.
	 * @return The result of the subtraction.
	 */
	public IMatrix< T > sub( final IMatrix< T > matrix );


	/**
	 * An implementation of this method multiplies the current matrix and the given matrix
	 * using the matrix mulpilication. The result is returned. It must not affect the current matrix! The result is returned,
	 * not written in the object!
	 *
	 * Furthermore it should throw exceptions if the boundaries mismatch or 'null' is passed as argument.
	 *
	 * @param matrix The matrix with that the current matrix should be multiplied. Must not be 'null'
	 * @return The result of the multiplication.
	 */
	public IMatrix< T > mult( final IMatrix< T > matrix );

	/**
	 * An implementation of this method multiplies the current matrix with the given value
	 * and returns the result. The result is returned. It must not affect the current matrix! The result is returned,
	 * not written in the object!
	 *
	 * Furthermore it should throw exceptions if 'null' is passed as argument.
	 *
	 * @param value The value with that the matrix should be multiplied.
	 * @return The result.
	 */
	public IMatrix< T > mult( final T value );

	/**
	 * An implementation of this method should return the current matrix transposed.
	 * Calling this method must not effect the current matrix itself. It must create
	 * a new Matrix and return it.
	 *
	 * @return The current matrix transposed.
	 */
	public IMatrix< T > getTransposed();
	
}