package math;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCaseColumnVector {

    public Double[][] A = new Double[][] { { 1.0 }, { 2.0 }, { 3.0 } };
    public Double[][] B = new Double[][] { { 4.0 }, { 5.0 }, { 6.0 } };
    public ColumnVector<Double> matrixA = new ColumnVector<Double>(A);
    public ColumnVector<Double> matrixB = new ColumnVector<Double>(B);

    @Test
    public void testGetTransposed() {
	final Double[][] matrix = new Double[][] { { 1.0, 2.0, 3.0 } };
	assertTrue(matrixA.getTransposed().equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testDotProduct() {
	assertTrue(matrixA.dotProduct(matrixB).equals(32.0));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCrossProduct() {
	final Double[][] matrix = new Double[][] { { -3.0 }, { 6.0 }, { -3.0 } };
	assertTrue(matrixA.crossProduct(matrixB).equals(new ColumnVector<Double>(matrix)));
    }

}
