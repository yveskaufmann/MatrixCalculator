package org.yvka.Beleg1.matrix;

import java.util.Iterator;

import org.yvka.Beleg1.matrix.iteration.MatrixElement;
import org.yvka.Beleg1.matrix.iteration.MatrixIterator;
import org.yvka.Beleg1.matrix.operations.CommonMatrixOperations;
import org.yvka.Beleg1.matrix.operations.MatrixIO;


/**
 * Default Implementation of a 64 bit floating point matrix.
 * 
 * @author Yves Kaufmann
 *
 */
public class MatrixImpl implements Matrix {
	
	protected double [][]m_Matrix;
	protected int m_NumOfRows;
	protected int m_NumOfCols;
	
	/**
	 * <p>
	 * Create a new matrix with the specified dimension and fills 
	 * her elements with zero. 
	 * </p>
	 * @param rows <br>the Matrix's rows number.<br>
	 * 			   Must be inside the interval [1,7].
	 * @param cols <br>the Matrix's columns number.<br>
	 * 			   Must be inside the interval [1,7].
	 */
	public MatrixImpl(int rows, int cols) {
		if(rows <=0 || rows > 7 || cols <= 0 || cols>7) {
			throw new IllegalArgumentException(
				String.format("Invalid dimension [%d, %d]", rows, cols)
			);
		}
		m_Matrix = new double[rows][cols];
		m_NumOfRows = rows;
		m_NumOfCols = cols;
		CommonMatrixOperations.zero(this);
	}
	
	/**
	 * <p>
	 * Create a new matrix by the specified data array,
	 * which contains all elements of the array.
	 * </p>
	 * 
	 * @param data the Elements of the array.
	 */
	public MatrixImpl(double data[][]) {
		this(data.length, data.length < 1 ? 0 : data[0].length);
		CommonMatrixOperations.fillByArray(this, data);	
	}
	
	/**
	 * Create a new matrix by copying the specified matrix.
	 * The resulting matrix will have the same dimension and same elements.
	 * 
	 * @param matrix the matrix which is used as template for the copy process. 
	 */
	public MatrixImpl(Matrix matrix) {
		this(matrix.toArray());
	}
	
	@Override
	public Matrix addScalar(int row, int col, double scalarValue) {
		ensureElementIndexIsInBound(row, col);
		m_Matrix[row][col] = m_Matrix[row][col] + scalarValue;
		return this;
	}

	@Override
	public Matrix add(Matrix otherMatrix) throws IllegalMatrixComputationException {
		return CommonMatrixOperations.add(this, otherMatrix);
	}

	@Override
	public Matrix multiplyBy(double scalarValue) {
		return CommonMatrixOperations.multiplyBy(this, scalarValue);
	}

	@Override
	public Matrix multiplyBy(Matrix otherMatrix) throws IllegalMatrixComputationException {
		return CommonMatrixOperations.multiplyBy(this, otherMatrix);
	}

	@Override
	public Matrix transposition() {
		return CommonMatrixOperations.transposition(this);
	}

	@Override
	public double get(int row, int col) {
		ensureElementIndexIsInBound(row, col);
		return get_unsafe(row, col);
	}

	@Override
	public double get_unsafe(int row, int col) {
		return m_Matrix[row][col];
	};
	
	@Override
	public void set(int row, int col, double value) {
		ensureElementIndexIsInBound(row, col);
		set_unsafe(row, col, value);
	}
	
	@Override
	public void set_unsafe(int row, int col, double value) {
		m_Matrix[row][col] = value;
	}

	@Override
	public int getNumRows() {
		return this.m_NumOfRows;
	}

	@Override
	public int getNumCols() {
		return m_NumOfCols;
	}

	@Override
	public double[][] toArray() {
		double [][]copy = m_Matrix.clone(); // make sure that the data is encapsulated.
		return copy;
	}
	
	@Override
	public Matrix copy() {
		return new MatrixImpl(this);
	}

	@Override
	public boolean isInBounds(int row, int col) {
		return !(col < 0 || col >= m_NumOfCols || row <  0 || row >= m_NumOfRows);
	}

	@Override
	public Iterator<MatrixElement> iterator() {
		return new MatrixIterator(this);
	}
	
	@Override
	public void print() {
		MatrixIO.print(this);
	}
	
	@Override
	public String toString() {
		return MatrixIO.toString(this);
	}
	
	/**
	 * Ensure that the specified element index is inside the matrix. 
	 * 
	 * @param row the Element's row index.
	 * @param col the Elements column index.
	 */
	private void ensureElementIndexIsInBound(int row, int col) {
		if(!isInBounds(row, col)) {
			throw new IllegalArgumentException("The specified element is out of Bounds.");
		}
	}

}
