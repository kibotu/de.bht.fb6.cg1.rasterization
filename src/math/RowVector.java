package math;

import de.bht.fb6.cg1.math.IRowVector;

public final class RowVector<T extends Number> extends Matrix<T> implements IRowVector<T> {

    /**
     * constructs a rowvector<T>
     * 
     * @param elements
     *            <T>[][]
     */
    public RowVector(final T[][] elements) {
	super(elements);
	if (elements.length != 1) {
	    throw new IllegalArgumentException("'rows' must be '1'");
	}
    }

    @SuppressWarnings("unchecked")
    @Override
    public T dotProduct(final IRowVector<T> vector) {
	if (vector == null) {
	    throw new NullPointerException("'vector' must not be 'null'");
	}
	if (getColumns() != vector.getColumns()) {
	    throw new IllegalArgumentException("not equal columns: " + getColumns() + "!=" + vector.getColumns());
	}
	Number result = 0.0;
	for (int i = 0; i < getColumns(); ++i) {
	    result = result.doubleValue() + get(0, i).doubleValue() * vector.get(0, i).doubleValue();
	}
	return (T) result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public IRowVector<T> crossProduct(final IRowVector<T>... vectors) {
	if (vectors == null) {
	    throw new NullPointerException("'vectors' must not be 'null'");
	}
	if (getColumns() != 3) {
	    throw new UnsupportedOperationException(getColumns() + " columns not supported yet.");
	}
	for (final IRowVector<T> vector : vectors) {
	    if (getColumns() != vector.getColumns()) {
		throw new IllegalArgumentException("not equal columns: " + getColumns() + "!=" + vector.getColumns());
	    }
	}
	final Number[][] result = new Number[vectors.length][3];
	for (int i = 0; i < vectors.length; ++i) {
	    result[i][0] = get(0, 1).doubleValue() * vectors[i].get(0, 2).doubleValue() - get(0, 2).doubleValue()
		    * vectors[i].get(0, 1).doubleValue();
	    result[i][1] = get(0, 2).doubleValue() * vectors[i].get(0, 0).doubleValue() - get(0, 0).doubleValue()
		    * vectors[i].get(0, 2).doubleValue();
	    result[i][2] = get(0, 0).doubleValue() * vectors[i].get(0, 1).doubleValue() - get(0, 1).doubleValue()
		    * vectors[i].get(0, 0).doubleValue();
	}
	return new RowVector<T>((T[][]) result);
    }
}
