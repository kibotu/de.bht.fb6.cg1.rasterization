package math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCaseMatrix2 {

    public Double[][] A = new Double[][] { { 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0 }, { 3.0, 3.0, 3.0 } };
    public Double[][] B = new Double[][] { { 4.0, 4.0, 4.0 }, { 5.0, 5.0, 5.0 }, { 6.0, 6.0, 6.0 } };
    public Matrix<Double> matrixA = new Matrix<Double>(A);
    public Matrix<Double> matrixB = new Matrix<Double>(B);

    @Test
    public void testEqualsObject() {
	final Double[][] a = new Double[][] { { 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0 }, { 3.0, 3.0, 3.0 } };
	assertTrue(matrixA.equals(new Matrix<Double>(a)));
	assertFalse(matrixA.equals(matrixB));
    }

    @Test
    public void testGetColumns() {
	assertTrue(matrixA.getColumns() == A[0].length);
    }

    @Test
    public void testGetRows() {
	assertTrue(matrixA.getRows() == A.length);
    }

    @Test
    public void testGet() {
	for (int i = 0; i < A.length; ++i) {
	    for (int j = 0; j < A[0].length; ++j) {
		assertTrue(matrixA.get(i, j).equals(A[i][j]));
	    }
	}
    }

    @Test
    public void testAdd() {
	final Double[][] matrix = new Double[][] { { 5.0, 5.0, 5.0 }, { 7.0, 7.0, 7.0 }, { 9.0, 9.0, 9.0 } };
	assertTrue(matrixA.add(matrixB).equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testSub() {
	final Double[][] matrix = new Double[][] { { -3.0, -3.0, -3.0 }, { -3.0, -3.0, -3.0 }, { -3.0, -3.0, -3.0 } };
	assertTrue(matrixA.sub(matrixB).equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testMultIMatrixOfNumber() {
	final Double[][] matrix = new Double[][] { { 15.0, 15.0, 15.0 }, { 30.0, 30.0, 30.0 }, { 45.0, 45.0, 45.0 } };
	assertTrue(matrixA.mult(matrixB).equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testMultNumber() {
	final Double[][] matrix = new Double[][] { { 2.0, 2.0, 2.0 }, { 4.0, 4.0, 4.0 }, { 6.0, 6.0, 6.0 } };
	assertTrue(matrixA.mult(2.0).equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testGetTransposed() {
	final Double[][] matrix = new Double[][] { { 1.0, 2.0, 3.0 }, { 1.0, 2.0, 3.0 }, { 1.0, 2.0, 3.0 } };
	assertTrue(matrixA.getTransposed().equals(new Matrix<Double>(matrix)));
    }

    @Test
    public void testToString() {
	final StringBuilder result = new StringBuilder();
	for (int i = 0; i < A.length; i++) {
	    for (int j = 0; j < A[0].length; j++) {
		result.append(String.format("%7.2f", A[i][j]));
	    }
	    result.append("\n");
	}
	assertTrue(result.toString().equals(matrixA.toString()));
    }
}
