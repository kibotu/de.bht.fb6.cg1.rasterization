package math;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCaseTransformation {

    @Test
    public void testTranslation2D() {
	final double x = 3.0;
	final double y = 4.0;
	final Double[][] matrix = new Double[][] { { 1.0, 0.0, x }, { 0.0, 1.0, y }, { 0.0, 0.0, 1.0 } };
	assertTrue(Transformation.translation2D(x, y).equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testRotation2D() {
	final double alpha = 60;
	final Double[][] matrix = new Double[][] { { Math.cos(alpha), -Math.sin(alpha), 0.0 },
		{ Math.sin(alpha), Math.cos(alpha), 0.0 }, { 0.0, 0.0, 1.0 } };
	assertTrue(Transformation.rotation2D(alpha).equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testScale2D() {
	final double sx = 3.0;
	final double sy = 4.0;
	final Double[][] matrix = new Double[][] { { sx, 0.0, 0.0 }, { 0.0, sy, 0.0 }, { 0.0, 0.0, 1.0 } };
	assertTrue(Transformation.scale2D(sx, sy).equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testShearOnX2D() {
	final double beta = 33;
	final Double[][] matrix = new Double[][] { { 1.0, Math.tan(beta), 0.0 }, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 } };
	assertTrue(Transformation.shearOnX2D(beta).equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testShearOnY2D() {
	final double beta = 27;
	final Double[][] matrix = new Double[][] { { 1.0, 0.0, 0.0 }, { Math.tan(beta), 1.0, 0.0 }, { 0.0, 0.0, 1.0 } };
	assertTrue(Transformation.shearOnY2D(beta).equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testTranslation3D() {
	final double x = 1.0;
	final double y = 2.0;
	final double z = 3.0;
	final Double[][] matrix = new Double[][] { { 1.0, 0.0, 0.0, x }, { 0.0, 1.0, 0.0, y }, { 0.0, 0.0, 1.0, z },
		{ 0.0, 0.0, 0.0, 1.0 } };
	assertTrue(Transformation.translation3D(x, y, z).equals(new Matrix<Double>(matrix)));

    }

    @Test
    public void testRotation3DonX() {
	final double alpha = 63;
	final Double[][] matrix = new Double[][] { { 1.0, 0.0, 0.0, 0.0 },
		{ 0.0, Math.cos(alpha), -Math.sin(alpha), 0.0 }, { 0.0, Math.sin(alpha), Math.cos(alpha), 0.0 },
		{ 0.0, 0.0, 0.0, 1.0 } };
	assertTrue(Transformation.rotation3DonX(alpha).equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testRotation3DonY() {
	final double alpha = 63;
	final Double[][] matrix = new Double[][] { { Math.cos(alpha), 0.0, Math.sin(alpha), 0.0 },
		{ 0.0, 1.0, 0.0, 0.0 }, { -Math.sin(alpha), 0.0, Math.cos(alpha), 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
	assertTrue(Transformation.rotation3DonY(alpha).equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testRotation3DonZ() {
	final double alpha = 63;
	final Double[][] matrix = new Double[][] { { Math.cos(alpha), -Math.sin(alpha), 0.0, 0.0 },
		{ Math.sin(alpha), Math.cos(alpha), 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
	assertTrue(Transformation.rotation3DonZ(alpha).equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testScale3D() {
	final double sx = 1.0;
	final double sy = 2.0;
	final double sz = 3.0;
	final Double[][] matrix = new Double[][] { { sx, 0.0, 0.0, 0.0 }, { 0.0, sy, 0.0, 0.0 }, { 0.0, 0.0, sz, 0.0 },
		{ 0.0, 0.0, 0.0, 1.0 } };
	assertTrue(Transformation.scale3D(sx, sy, sz).equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testShearCombined3D() {
	final double xy = 1.0;
	final double xz = 2.0;
	final double yx = 3.0;
	final double yz = 4.0;
	final double zx = 5.0;
	final double zy = 6.0;
	final Double[][] matrix = new Double[][] { { 1.0, xy, xz, 0.0 }, { yx, 1.0, yz, 0.0 }, { zx, zy, 1.0, 0.0 },
		{ 0.0, 0.0, 0.0, 1.0 } };
	assertTrue(Transformation.shearCombined3D(xy, xz, yx, yz, zx, zy).equals(new Matrix<Double>(matrix)));
    }

}
