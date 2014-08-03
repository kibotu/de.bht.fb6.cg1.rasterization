package math;

import de.bht.fb6.cg1.math.IColumnVector;

public final class ColumnVector<T extends Number> extends Matrix<T> implements IColumnVector<T> {

    /**
     * constructs a column vector
     * 
     * @param elements
     *            <T>[][]
     */
    public ColumnVector(final T[][] elements) {
	super(elements);
	if (elements[0].length != 1) {
	    throw new IllegalArgumentException("'rows' must be '1'");
	}
    }

    @SuppressWarnings("unchecked")
    public ColumnVector(final T[] elements) {
	super((T[][]) get2DArray(elements));
    }

    private static Number[][] get2DArray(final Number[] elements) {
	final Number[][] temp = new Number[0][elements.length];
	for (int i = 0; i < elements.length; ++i) {
	    temp[0][i] = elements[i];
	}

	System.out.println(temp);

	return temp;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T dotProduct(final IColumnVector<T> vector) {
	if (vector == null) {
	    throw new NullPointerException("'vector' must not be 'null'");
	}
	if (getRows() != vector.getRows()) {
	    throw new IllegalArgumentException("not equal rows: " + getRows() + "!=" + vector.getRows());
	}
	Double result = new Double(0.0);
	for (int i = 0; i < getRows(); ++i) {
	    result = result + get(i, 0).doubleValue() * vector.get(i, 0).doubleValue();
	}
	return (T) result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public IColumnVector<T> crossProduct(final IColumnVector<T>... vectors) {
	if (vectors == null) {
	    throw new NullPointerException("'vectors' must not be 'null'");
	}
	if (getRows() != 3) {
	    throw new UnsupportedOperationException(getRows() + " rows not supported yet.");
	}
	for (final IColumnVector<T> vector : vectors) {
	    if (getRows() != vector.getRows()) {
		throw new IllegalArgumentException("not equal rows: " + getRows() + "!=" + vector.getRows());
	    }
	}
	final Number[][] result = new Number[3][vectors.length];
	for (int i = 0; i < vectors.length; ++i) {
	    result[0][i] = get(1, 0).doubleValue() * vectors[i].get(2, 0).doubleValue() - get(2, 0).doubleValue()
		    * vectors[i].get(1, 0).doubleValue();
	    result[1][i] = get(2, 0).doubleValue() * vectors[i].get(0, 0).doubleValue() - get(0, 0).doubleValue()
		    * vectors[i].get(2, 0).doubleValue();
	    result[2][i] = get(0, 0).doubleValue() * vectors[i].get(1, 0).doubleValue() - get(1, 0).doubleValue()
		    * vectors[i].get(0, 0).doubleValue();
	}
	return new ColumnVector<T>((T[][]) result);
    }
}
