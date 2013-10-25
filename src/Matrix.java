import java.util.Scanner;

/**
 * Matrix that stores all the integer values
 * @author Sean
 */
public class Matrix {

	private int[][] matrix;
	
	/**
	 * Constructs a matrix
	 * @param rows - number of rows in matrix
	 * @param cols - number of columns in matrix
	 */
	public Matrix(int rows, int cols) {
		matrix = new int[rows][cols];
	}
	
	public void fillMatrix() {
		Scanner console = new Scanner(System.in);
		for (int row = 0; row < matrix.length; row++) {
			System.out.print("Number in row " + (row + 1) + ":");
			String line = console.nextLine();
			String[] numbers = line.split(" ");
			if (numbers.length !=  matrix[row].length) throw new IllegalArgumentException();
			for (int col = 0; col < matrix[row].length; col++) {
				matrix[row][col] = Integer.parseInt(numbers[col]);
			}
		}
	}
	
	public String toString() {
		String matrixString = "[\n";
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				int num = matrix[row][col];
				matrixString += num;
				if (num < 10) matrixString += " ";
			}
			matrixString += "\n";
		}
		matrixString += "]";
		return matrixString;
	}
	
}
