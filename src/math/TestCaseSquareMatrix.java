package math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCaseSquareMatrix {

    public Double[][] A = new Double[][] { { 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0 }, { 3.0, 3.0, 3.0 } };
    public Double[][] B = new Double[][] { { 4.0, 4.0, 4.0 }, { 5.0, 5.0, 5.0 }, { 6.0, 6.0, 6.0 } };
    public SquareMatrix<Double> matrixA = new SquareMatrix<Double>(A);
    public SquareMatrix<Double> matrixB = new SquareMatrix<Double>(B);

    @Test
    public void testEqualsObject() {
	final Double[][] a = new Double[][] { { 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0 }, { 3.0, 3.0, 3.0 } };
	assertTrue(matrixA.equals(new SquareMatrix<Double>(a)));
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
	assertTrue(matrixA.add(matrixB).equals(new SquareMatrix<Double>(matrix)));
    }

    @Test
    public void testSub() {
	final Double[][] matrix = new Double[][] { { -3.0, -3.0, -3.0 }, { -3.0, -3.0, -3.0 }, { -3.0, -3.0, -3.0 } };
	assertTrue(matrixA.sub(matrixB).equals(new SquareMatrix<Double>(matrix)));
    }

    @Test
    public void testMultIMatrixOfNumber() {
	final Double[][] matrix = new Double[][] { { 15.0, 15.0, 15.0 }, { 30.0, 30.0, 30.0 }, { 45.0, 45.0, 45.0 } };
	assertTrue(matrixA.mult(matrixB).equals(new SquareMatrix<Double>(matrix)));
    }

    @Test
    public void testMultNumber() {
	final Double[][] matrix = new Double[][] { { 2.0, 2.0, 2.0 }, { 4.0, 4.0, 4.0 }, { 6.0, 6.0, 6.0 } };
	assertTrue(matrixA.mult(2.0).equals(new SquareMatrix<Double>(matrix)));
    }

    @Test
    public void testGetTransposed() {
	final Double[][] matrix = new Double[][] { { 1.0, 2.0, 3.0 }, { 1.0, 2.0, 3.0 }, { 1.0, 2.0, 3.0 } };
	assertTrue(matrixA.getTransposed().equals(new SquareMatrix<Double>(matrix)));
    }

    @Test
    public void testIsIdentityMatrix() {
	final Double[][] matrix = new Double[][] { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 } };
	final Double[][] matrix2 = new Double[][] { { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0 } };
	final Double[][] matrix3 = new Double[][] { { 1.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 }, { 1.0, 0.0, 0.0 } };
	assertTrue(new SquareMatrix<Double>(matrix).isIdentityMatrix());
	assertTrue(new SquareMatrix<Double>(matrix2).isIdentityMatrix());
	assertFalse(new SquareMatrix<Double>(matrix3).isIdentityMatrix());
    }

    @Test
    public void testGetDeterminante() {
	final Double[][] matrix = new Double[][] { { 1.0, 2.0, 3.0 }, { -1.0, 2.0, 3.0 }, { 1.0, 2.0, 3.0 } };
	final Double[][] matrix2 = new Double[][] { { 1.0, 1.0, 1.0 }, { 1.0, -2.0, 3.0 }, { -2.0, -1.0, -1.0 } };
	assertTrue(new SquareMatrix<Double>(matrix).getDeterminante().equals(0.0));
	assertFalse(new SquareMatrix<Double>(matrix2).getDeterminante().equals(0.0));
    }
}
