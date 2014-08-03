package math;

import java.util.Arrays;

import de.bht.fb6.cg1.math.IMatrix;

public class Matrix<T extends Number> implements IMatrix<T> {

	/**
	 * represents all elements of a matrix<T>
	 */
	protected final T[][] elements;

	/**
	 * constructs a matrix<T>
	 * 
	 * @param elements
	 *            <T>[][]
	 */
	public Matrix(final T[][] elements) {
		if (elements == null)
			throw new NullPointerException("'elements' must not be 'null'");
		this.elements = elements.clone();
	}

	@Override
	public int getColumns() {
		return elements[0].length;
	}

	@Override
	public int getRows() {
		return elements.length;
	}

	@Override
	public T get(final int row, final int column) {
		return elements[row][column];
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMatrix<T> add(final IMatrix<T> matrix) {
		if (getRows() != matrix.getRows())
			throw new IllegalArgumentException(getRows() + " != "
					+ matrix.getRows());
		if (getColumns() != matrix.getColumns())
			throw new IllegalArgumentException(getColumns() + " != "
					+ matrix.getColumns());
		final Number[][] result = new Number[getRows()][getColumns()];
		for (int i = 0; i < getRows(); ++i) {
			for (int j = 0; j < getColumns(); ++j) {
				result[i][j] = elements[i][j].doubleValue()
				+ matrix.get(i, j).doubleValue();
			}
		}
		return new Matrix<T>((T[][]) result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMatrix<T> sub(final IMatrix<T> matrix) {
		if (getRows() != matrix.getRows())
			throw new IllegalArgumentException(getRows() + " != "
					+ matrix.getRows());
		if (getColumns() != matrix.getColumns())
			throw new IllegalArgumentException(getColumns() + " != "
					+ matrix.getColumns());
		final Number[][] result = new Number[getRows()][getColumns()];
		for (int i = 0; i < getRows(); ++i) {
			for (int j = 0; j < getColumns(); ++j) {
				result[i][j] = elements[i][j].doubleValue()
				- matrix.get(i, j).doubleValue();
			}
		}
		return new Matrix<T>((T[][]) result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMatrix<T> mult(final IMatrix<T> matrix) {
		if (getColumns() != matrix.getRows())
			throw new IllegalArgumentException("Columns(" + getColumns()
					+ ") != Rows(" + matrix.getRows() + ")");
		final Number[][] result = new Number[getRows()][matrix.getColumns()];
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < matrix.getColumns(); j++) {
				double sum = 0.0;
				for (int k = 0; k < matrix.getRows(); k++) {
					sum += get(i, k).doubleValue()* matrix.get(k, j).doubleValue();
				}
				result[i][j] = sum;
			}
		}
		return new Matrix<T>((T[][]) result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMatrix<T> mult(final T value) {
		if (value == null)
			throw new NullPointerException("'value' must not be 'null'");
		final Number[][] result = new Number[getRows()][getColumns()];
		for (int i = 0; i < getRows(); ++i) {
			for (int j = 0; j < getColumns(); ++j) {
				result[i][j] = get(i, j).doubleValue() * value.doubleValue();
			}
		}
		return new Matrix<T>((T[][]) result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMatrix<T> getTransposed() {
		final Number[][] result = new Number[getColumns()][getRows()];
		for (int i = 0; i < getRows(); ++i) {
			for (int j = 0; j < getColumns(); ++j) {
				result[j][i] = elements[i][j];
			}
		}

		return new Matrix<T>((T[][]) result);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(elements);
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Matrix))
			return false;
		@SuppressWarnings("unchecked")
		final Matrix<T> other = (Matrix<T>) obj;
		if (!Arrays.deepEquals(elements, other.elements))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder();
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				result.append(String.format("%7.2f",
						elements[i][j].doubleValue()));
			}
			result.append("\n");
		}
		return result.toString();
	}
}
