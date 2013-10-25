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
