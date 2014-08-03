package de.bht.fb6.cg1.math;

/**
 * This interface represents a row vector.
 *
 * @author Stephan Rehfeld <rehfeld (-at-) beuth-hochschule.de>
 */
public interface IRowVector< T extends Number > extends IMatrix< T > {

	/**
	 * An implementation of this method should return to dot product of both
	 * vectors. It must not change any value in the current vector. Instead it
	 * is returning the result.
	 *
	 * @param vector The second vector. Must not be null!
	 * @return The dot product.
	 */
	public T dotProduct( final IRowVector< T > vector );

	/**
	 * An implementation of this method should return the cross product of
	 * the given vectors. It must not change any value in the current vector. Instead it
	 * is returning the result.
	 *
	 * It must at least implement the cross product of vectors with 3 elements.
	 *
	 * @param vectors The vectors for the cross product. The amount of vectors must be dimension - 2. Must not be null.
	 * @return The cross product of the vectors.
	 */
	public IRowVector< T > crossProduct( final IRowVector< T >... vectors );

}
