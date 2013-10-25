import java.util.Scanner;

/**
 * Matrix that stores all the integer values
 * @author Sean
 */
public class Matrix {

	private int[][] matrix;
	private boolean selected;
	
	/**
	 * Constructs a matrix
	 * @param rows - number of rows in matrix
	 * @param cols - number of columns in matrix
	 * @throws IllegalArgumentException if the rows or columns are zero
	 */
	public Matrix(int rows, int columns) {
		if (rows == 0 || columns == 0) throw new IllegalArgumentException();
		matrix = new int[rows][columns];
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
				matrix[row][col] = Integer.parseInt(numbers[col]);
			}
		}
	}
	
	/**
	 * Adds and stores values in matrix
	 * @param otherMatrix - matrix being added to
	 */
	public void add(Matrix otherMatrix) {
		if (otherMatrix.getRows() != this.getRows()
				|| otherMatrix.getColumns() != this.getColumns()) {
			throw new IllegalArgumentException();
		}
		
		for (int row = 0; row < this.getRows(); row++) {
			for (int col = 0; col < this.getColumns(); col++) {
				matrix[row][col] += otherMatrix.matrix[row][col];
			}
		}
	}
	
	/**
	 * Multiplies matrix by scalar
	 * @param scalar - number that the matrix is going to grow by
	 */
	public void scalarMultiplcation(int scalar) {
		for (int row = 0; row < this.getRows(); row++) {
			for (int col = 0; col < this.getColumns(); col++) {
				matrix[row][col] *= scalar; 
			}
		}
	}
	
	/**
	 * Multiplies matrix by scalar
	 * @param scalar - number that the matrix is going to grow by
	 */
	public void scalarMultiplcation(Matrix otherMatrix) {
		if (otherMatrix.getRows() != this.getColumns()
				&& otherMatrix.getColumns() != this.getRows()) {
			throw new IllegalArgumentException();
		}
		for (int row = 0; row < this.getRows(); row++) {
			for (int col = 0; col < this.getColumns(); col++) {
				
			}
		}
	}
	
	/**
	 * Multiplies two matrices if they have correct rows and columns
	 * @param other - matrix multiply current one by
	 * @return product of matrices
	 */
	public Matrix multiply(Matrix other) {
		if (this.getRows() != other.getColumns()
				|| this.getColumns() != other.getRows()) {
			throw new IllegalArgumentException();
		}
		Matrix product = new Matrix(this.getRows(), other.getColumns());
		
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getRows(); j++) {
				int sum = 0;

				for (int k = 0; k < this.getColumns(); k++) {
					int o = matrix[i][k];
					int o2 = other.matrix[k][i];
					sum += o * o2;
				}
				product.matrix[i][j] = sum;
			}
		}
		System.out.println(product.toString());
		return product;
	}
	
	/**
	 * @return - grids printed values
	 */
	public String toString() {
		String matrixString = "\n[\n";
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				int num = matrix[row][col];
				matrixString += num + " ";
				if (num < 10) matrixString += " ";
			}
			matrixString += "\n";
		}
		matrixString += "\t]";
		return matrixString;
	}
	
}
