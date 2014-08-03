package de.bht.fb6.cg1.math;

/**
 * This interfaces contains some methods for a square matrix.
 *
 * @author Stephan Rehfeld <rehfeld (-at-) beuth-hochschule.de>
 */
public interface ISquareMatrix< T extends Number > extends IMatrix< T > {

	/**
	 * This method returns if the current matrix is the identity matrix
	 *
	 * @return Return 'true' if the current matrix is the identity matrix and 'false' if not.
	 */
	public boolean isIdentityMatrix();

}
