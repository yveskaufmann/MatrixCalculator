package org.yvka.Beleg1.operations.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.yvka.Beleg1.matrix.Matrix;
import org.yvka.Beleg1.matrix.MatrixFactory;
import org.yvka.Beleg1.matrix.operations.CommonMatrixOperations;

/**
 * TestCases which make sure that the rank determination functions correctly. 
 * 
 * @author Yves Kaufmann
 *
 */
public class TestRankDetermination {
	
	protected static MatrixFactory factory = MatrixFactory.get();
	
	/**
	 * Tests the determination of the rank for identity matrices
	 */
	@Test
	@SuppressWarnings("static-method")
	public void testIdentityMatrix() {
		Matrix identyMatrix3x3 = factory.createMatrixFromArray(new double [][] {
				{1.0, 0.0, 0.0},
				{0.0, 1.0, 0.0},
				{0.0, 0.0, 1.0}
			});
		
		Matrix identyMatrix1x1 = factory.createMatrixFromArray(new double [][] {
			{1.0}		
		});
		
		assertEquals("The rank of quadtratic identity matrix must be rank(M) = rows(M)", 3, 
				CommonMatrixOperations.determineRankOfMatrix(identyMatrix3x3));
		
		assertEquals("The rank of quadtratic identity matrix must be rank(M) = rows(M)", 1, 
				CommonMatrixOperations.determineRankOfMatrix(identyMatrix1x1));
	}
	
	/**
	 * Tests the determination of the rank for non quadratic matrices
	 */
	@Test
	@SuppressWarnings("static-method")
	public void testNonQuadtraticMatrices() {
		Matrix matrix2x6 = factory.createMatrixFromArray(new double [][] {
				{2.0, 2.0, 6.0, 0.0, 4.0, 12.0},
				{1.0, 1.0, 3.0, 1.0, 3.0, 8.0},
				{2.0, 1.0, 3.0, 4.0, 6.0, 15.0},
				{0.0, 2.0, 6.0, 3.0, 7.0, 16.0},
		});
		
		Matrix matrix3x1 = factory.createMatrixFromArray(new double [][] {
				{1.0},
				{1.0},
				{1.0}
		});
	
		
		assertEquals("The rank of a matrix must be rank(M) = min(rows(M),cols(M)) - NonZeroRows(Gauss(M)) ", 3, 
				CommonMatrixOperations.determineRankOfMatrix(matrix2x6));
		
		assertEquals("The rank of a matrix must be rank(M) = min(rows(M),cols(M)) - NonZeroRows(Gauss(M)) ", 1, 
				CommonMatrixOperations.determineRankOfMatrix(matrix3x1));
			
	}
}
