package math;

import de.bht.fb6.cg1.math.ISquareMatrix;

public final  class SquareMatrix<T extends Number> extends Matrix<T> implements ISquareMatrix<T> {

    /**
     * constructs a squarematrix<T>
     * 
     * @param elements
     *            <T>[][]
     */
    public SquareMatrix(final T[][] elements) {
	super(elements);
	if (elements == null) {
	    throw new NullPointerException("'elements' must not be 'null'");
	}
	if (elements.length != elements[0].length) {
	    throw new IllegalArgumentException("not square matrix");
	}
    }

    @Override
    public boolean isIdentityMatrix() {
	final int[] pos = new int[getColumns()];
	for (int i = 0; i < getColumns(); ++i) {
	    for (int j = 0; j < getColumns(); ++j) {
		if (!get(i, j).equals(0.0)) {
		    if (get(i, j).equals(1.0)) {
			if (pos[j] == 0.0) {
			    pos[j] = 1;
			    continue;
			} else {
			    return false;
			}
		    } else {
			return false;
		    }
		}
	    }
	}
	return true;
    }

    /**
     * gets Determinante of a squarematrix with lower or equal dimension of 3
     * 
     * @return determinante
     */
    public Double getDeterminante() {
	if (getRows() != getColumns()) {
	    throw new IllegalArgumentException("matrix not square");
	}
	switch (getRows()) {
	case 1:
	    return elements[0][0].doubleValue();
	case 2:
	    return elements[0][0].doubleValue() * elements[1][1].doubleValue() - elements[0][1].doubleValue()
		    * elements[1][0].doubleValue();
	case 3:
	    return elements[0][0].doubleValue() * elements[1][1].doubleValue() * elements[2][2].doubleValue()
		    + elements[0][1].doubleValue() * elements[1][2].doubleValue() * elements[2][0].doubleValue()
		    + elements[0][2].doubleValue() * elements[1][0].doubleValue() * elements[2][1].doubleValue()
		    - elements[0][2].doubleValue() * elements[1][1].doubleValue() * elements[2][0].doubleValue()
		    - elements[0][1].doubleValue() * elements[1][0].doubleValue() * elements[2][2].doubleValue()
		    - elements[0][0].doubleValue() * elements[1][2].doubleValue() * elements[2][1].doubleValue();
	default:
	    throw new UnsupportedOperationException("Matrix with Dimension: " + getRows() + " is not supported.");
	}
    }

}
