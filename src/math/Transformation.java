package math;

import de.bht.fb6.cg1.math.IMatrix;

final public class Transformation {

    /**
     * static class
     */
    private Transformation() {
    }

    /**
     * translation 2D
     * 
     * @param x
     * @param y
     * @return translation Matrix
     */
    public static IMatrix<Number> translation2D(final double x, final double y) {
	return new Matrix<Number>(new Double[][] { { 1.0, 0.0, x }, { 0.0, 1.0, y }, { 0.0, 0.0, 1.0 } });
    }

    /**
     * translation 3D
     * 
     * @param x
     * @param y
     * @param z
     * @return translation Matrix
     */
    public static IMatrix<Number> translation3D(final double x, final double y, final double z) {
	return new Matrix<Number>(new Double[][] { { 1.0, 0.0, 0.0, x }, { 0.0, 1.0, 0.0, y }, { 0.0, 0.0, 1.0, z },
		{ 0.0, 0.0, 0.0, 1.0 } });
    }

    /**
     * rotation
     * 
     * @param alpha
     * @return rotations Matrix
     */
    public static IMatrix<Number> rotation2D(final double alpha) {
	return new Matrix<Number>(new Double[][] { { Math.cos(alpha), -Math.sin(alpha), 0.0 },
		{ Math.sin(alpha), Math.cos(alpha), 0.0 }, { 0.0, 0.0, 1.0 } });
    }

    /**
     * rotation 3D an x-Achse
     * 
     * @param alpha
     * @return rotations Matrix
     */
    public static IMatrix<Number> rotation3DonX(final double alpha) {
	return new Matrix<Number>(new Double[][] { { 1.0, 0.0, 0.0, 0.0 },
		{ 0.0, Math.cos(alpha), -Math.sin(alpha), 0.0 }, { 0.0, Math.sin(alpha), Math.cos(alpha), 0.0 },
		{ 0.0, 0.0, 0.0, 1.0 } });
    }

    /**
     * rotation 3D an y-Achse
     * 
     * @param alpha
     * @return rotations Matrix
     */
    public static IMatrix<Number> rotation3DonY(final double alpha) {
	return new Matrix<Number>(new Double[][] { { Math.cos(alpha), 0.0, Math.sin(alpha), 0.0 },
		{ 0.0, 1.0, 0.0, 0.0 }, { -Math.sin(alpha), 0.0, Math.cos(alpha), 0.0 }, { 0.0, 0.0, 0.0, 1.0 } });
    }

    /**
     * rotation 3D an z-Achse
     * 
     * @param alpha
     * @return rotations Matrix
     */
    public static IMatrix<Number> rotation3DonZ(final double alpha) {
	return new Matrix<Number>(new Double[][] { { Math.cos(alpha), -Math.sin(alpha), 0.0, 0.0 },
		{ Math.sin(alpha), Math.cos(alpha), 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } });
    }

    /**
     * scale 2D
     * 
     * @param sx
     * @param sy
     * @return scaled Matrix
     */
    public static IMatrix<Number> scale2D(final double sx, final double sy) {
	return new Matrix<Number>(new Double[][] { { sx, 0.0, 0.0 }, { 0.0, sy, 0.0 }, { 0.0, 0.0, 1.0 } });
    }

    /**
     * scale 3D
     * 
     * @param sx
     * @param sy
     * @param sz
     * @return scaled Matrix
     */
    public static IMatrix<Number> scale3D(final double sx, final double sy, final double sz) {
	return new Matrix<Number>(new Double[][] { { sx, 0.0, 0.0, 0.0 }, { 0.0, sy, 0.0, 0.0 }, { 0.0, 0.0, sz, 0.0 },
		{ 0.0, 0.0, 0.0, 1.0 } });
    }

    /**
     * shears on x-axe
     * 
     * @param beta
     * @return shear matrix
     */
    public static IMatrix<Number> shearOnX2D(final double beta) {
	return new Matrix<Number>(new Double[][] { { 1.0, Math.tan(beta), 0.0 }, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 } });
    }

    /**
     * shears on y-axe
     * 
     * @param beta
     * @return shear matrix
     */
    public static IMatrix<Number> shearOnY2D(final double beta) {
	return new Matrix<Number>(new Double[][] { { 1.0, 0.0, 0.0 }, { Math.tan(beta), 1.0, 0.0 }, { 0.0, 0.0, 1.0 } });
    }

    /**
     * combined 3D share Matrix
     * 
     * @param xy
     * @param xz
     * @param yx
     * @param yz
     * @param zx
     * @param zy
     * @return shear matrix
     */
    public static IMatrix<Number> shearCombined3D(final double xy, final double xz, final double yx, final double yz,
	    final double zx, final double zy) {
	return new Matrix<Number>(new Double[][] { { 1.0, xy, xz, 0.0 }, { yx, 1.0, yz, 0.0 }, { zx, zy, 1.0, 0.0 },
		{ 0.0, 0.0, 0.0, 1.0 } });
    }

    /**
     * deep clone for arrays
     * 
     * @param array
     * @return cloned number array
     */
    protected static Number[][] deepClone(final Number[][] array) {
	final int length = array.length;
	final Number[][] clone = new Number[length][];
	for (int i = 0; i < clone.length; i++) {
	    clone[i] = array[i] == null ? null : array[i].clone();
	}
	return clone;
    }
}
