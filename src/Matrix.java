import java.util.Arrays;
import java.util.Scanner;

/**
 * Matrix that stores all the integer values
 * @author Sean
 */
public class Matrix {

	private double[][] matrix;
	private boolean selected;
	
	/**
	 * Constructs a matrix
	 * @param rows - number of rows in matrix
	 * @param cols - number of columns in matrix
	 * @throws IllegalArgumentException if the rows or columns are zero
	 */
	public Matrix(int rows, int columns) {
		if (rows == 0 || columns == 0) throw new IllegalArgumentException();
		matrix = new double[rows][columns];
		selected = false;
	}
	
	/**
	 * @return - number of rows in matrix
	 */
	public int getRows() {
		return matrix.length;
	}
	
	/**
	 * @return - number of columns in matrix
	 */
	public int getColumns() {
		return matrix[0].length;
	}
	
	/**
	 * @return - if matrix is selected
	 */
	public boolean getSelected() {
		return selected;
	}
	
	/**
	 * Sets the selected state of a matrix
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Fills matrix with user entered numbers
	 */
	public void fillMatrix() {
		Scanner console = new Scanner(System.in);
		for (int row = 0; row < this.getRows(); row++) {
			System.out.print("Numbers in row " + (row + 1) + ": ");
			String line = console.nextLine();
			String[] numbers = line.split(" ");
			if (numbers.length !=  this.getColumns()) throw new IllegalArgumentException();
			for (int col = 0; col < this.getColumns(); col++) {
				matrix[row][col] = Double.parseDouble(numbers[col]);
			}
		}
	}
	
	/**
	 * Adds and stores values in matrix
	 * @param otherMatrix - matrix being added to
	 */
	public Matrix add(Matrix otherMatrix) {
		if (otherMatrix.getRows() != this.getRows()
				|| otherMatrix.getColumns() != this.getColumns()) {
			throw new IllegalArgumentException();
		}
		Matrix sum = new Matrix(this.getRows(), this.getColumns());
		
		for (int row = 0; row < this.getRows(); row++) {
			for (int col = 0; col < this.getColumns(); col++) {
				sum.matrix[row][col] = matrix[row][col] + otherMatrix.matrix[row][col];
			}
		}
		
		return sum;
	}
	
	/**
	 * Multiplies matrix by scalar
	 * @param scalar - number that the matrix is going to grow by
	 */
	public void scalarMultiplcation(double scalar) {
		for (int row = 0; row < this.getRows(); row++) {
			for (int col = 0; col < this.getColumns(); col++) {
				matrix[row][col] *= scalar; 
			}
		}
	}
	
	/**
	 * Multiplies two matrices if they have correct rows and columns
	 * @param other - matrix multiply current one by
	 * @return product of matrices
	 */
	public Matrix multiply(Matrix other) {
		if (this.getColumns() != other.getRows()) {
			throw new IllegalArgumentException();
		}
		Matrix product = new Matrix(this.getRows(), other.getColumns());
		
		for (int i = 0; i < this.getColumns(); i++) {
			for (int j = 0; j < other.getColumns(); j++) {
				int sum = 0;
				for (int k = 0; k < this.getColumns(); k++) {
					sum += matrix[i][k] * other.matrix[k][j];
				}
				product.matrix[i][j] = sum;
			}
		}

		return product;
	}
	
	/**
	 * Raises matrix to certain power
	 * @param power - power being raised to
	 * @param ma
	 */
	public void power(int power, Matrix ma) {
		Matrix result = ma;
		for (int i = 0; i < power-1; i++) {
			result = multiply(result);
		}
		System.out.println(result.toString());
	}
	
	/**
	 * Transposes a matrix only if it is a square matrix
	 */
	public void transpose() {
		Matrix n = new Matrix(this.getColumns(), this.getRows());
		for (int row = 0; row < this.getRows(); row++) {
			for (int col = 0; col < this.getColumns(); col++){
				n.matrix[col][row] = matrix[row][col];
			}
		}
		this.matrix = n.matrix;
	}
	
	/**
	 * Gets the inverse of a 2 x 2 matrix
	 * @return inverse of matrix
	 */
	public Matrix inverse() {
		double determinant = (double) this.determinant();
		Matrix inverse = new Matrix(getRows(), getColumns());
		inverse.matrix[0][0] = matrix[1][1];
		inverse.matrix[1][1] = matrix[0][0];
		inverse.matrix[0][1] = matrix[1][0]*-1;
		inverse.matrix[1][0] = matrix[0][1]*-1;
		inverse.scalarMultiplcation(1.0/determinant);
		return inverse;
	}
	
	/**
	 * Finds the determinant of only a 2 x 2 matrix,
	 * does not handle a matrix with determinant of 0
	 * @throws IllegalStateException if the matrix is not 2 x 2
	 * @return determinant of 2 x 2 matrix
	 */
	public int determinant() {
		if (this.getRows() != this.getColumns() || this.getRows() != 2) {
			throw new IllegalStateException();
		}
		
		int diagnol = 1;
		int nonDiagnol = 1;
		
		for (int row = 0; row < this.getRows(); row++) {
			for (int col = 0; col < this.getColumns(); col++) {
				if (row == col) {
					diagnol *= matrix[row][col];
				} else {
					nonDiagnol *= matrix[row][col];
				}
			}
		}
		return diagnol - nonDiagnol;
	}
	
	/**
	 * @return - grids printed values
	 */
	public String toString() {
		String matrixString = "\n[\n";
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				double num = matrix[row][col];
				matrixString += num + " ";
				if (num < 10) matrixString += " ";
			}
			matrixString += "\n";
		}
		matrixString += "\t]";
		return matrixString;
	}
	
}
