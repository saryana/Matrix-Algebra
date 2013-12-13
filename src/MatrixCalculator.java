import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Allows user to do operations to matrices. 
 * @author Sean
 *
 */
public class MatrixCalculator {

	public static List<Matrix> matrices;
	
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		ArrayList<String> commands = getCommands();
		
		System.out.print("How many matracies do you want? ");
		int count = console.nextInt();
		matrices = new ArrayList<Matrix>();
		
		for (int i = 0; i < count; i++) {
			System.out.print("Dimensions for matrix " + (i + 1) + "? (space seperated) ");
			int rows =  console.nextInt();
			int cols = console.nextInt();
			matrices.add(new Matrix(rows, cols));
			matrices.get(i).fillMatrix();
		}
		selectMatrix(console);
		
		printCommands(commands);
		String request = console.next().toLowerCase();
		Matrix selected = getSelectedMatrix();
		
		while (!request.equals("q")) {
			if (request.equals("fill")) {
				selected.fillMatrix();
			} else if (request.equals("add")) {
				System.out.print("Dimensions for matrix? (space seperated) ");
				int rows = console.nextInt();
				int cols = console.nextInt();
				Matrix newMatrix = new Matrix(rows, cols);
				newMatrix.fillMatrix();
				matrices.add(newMatrix);
			} else if (request.equals("addm")) {
				System.out.print("What matrix do you want to add to the"
						+ " selected matrix? (e.g. 1, 2) ");
				Matrix sum = selected.add(matrices.get(console.nextInt() - 1));
				matrices.add(sum);
				System.out.println(sum.toString());
			} else if (request.equals("multiply")) {
				System.out.print("What matrix do you want to add to the"
						+ " selected matrix? (e.g. 1, 2) ");
				Matrix product = selected.multiply(matrices.get(console.nextInt() - 1));
				matrices.add(product);
				System.out.println(product.toString());
			} else if (request.equals("power")) {
				System.out.print("By what power? ");
				selected.power(console.nextInt(), selected);
			} else if (request.equals("scalar")) {
				System.out.print("By what scalar? ");
				selected.scalarMultiplcation(console.nextDouble());
			} else if (request.equals("trans")) {
				selected.transpose();
			} else if (request.equals("inverse")) {
				Matrix inverse = selected.inverse();
				System.out.println(inverse);
				matrices.add(inverse);
			} else if (request.equals("det")) {
				System.out.println("1/" + selected.determinant());
			} else if (request.equals("rows")) {
				System.out.println(selected.getRows());
			} else if (request.equals("cols")) {
				System.out.println(selected.getColumns());
			} else if (request.equals("change")) {
				selectMatrix(console);
				selected = getSelectedMatrix();
			} else if (request.equals("print")) {
				System.out.println(selected.toString());

			} else if (request.equals("printa")) {
				printAll();
			}
			System.out.println();
			printCommands(commands);
			request = console.next();
			System.out.println();
		}
	}
	
	/**
	 * Selects the matrix that user wants to use for computations
	 */
	private static void selectMatrix(Scanner console) {
		printAll();
		System.out.print("Which Matrix should be selected? (enter number) ");
		int selection = console.nextInt();
		for (Matrix matrix : matrices) {
			matrix.setSelected(false);
		}
		matrices.get(selection - 1).setSelected(true);
	}

	/**
	 * Prints all the matrices
	 */
	private static void printAll() {
		for (int i = 0; i < matrices.size(); i++) {
			System.out.print("Matrix " + (i + 1) + ": ");
			System.out.println(matrices.get(i).toString());
		}
	}
	
	/**
	 * Easily allows for addition of commands
	 * @return All the commands the user can select
	 */
	private static ArrayList<String> getCommands() {
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("Q: Quit");
		commands.add("FILL: Changes content of matrix");
		commands.add("ADD: Adds a matrix to the list");
		commands.add("ADDM: Adds two matrices and stores it");
		commands.add("MULTIPLY: Multiplies two matrices and stores it");
		commands.add("POWER: Raises matrix to certain power and replaces it");
		commands.add("SCALAR: Multiples a matrix by a scalar and replaces it");
		commands.add("TRANS: Transposes a matrix and replaces it");
		commands.add("INVERSE: Finds inverse of (2x2) matrix");
		commands.add("DET: Finds determinant of (2x2) matrix");
		commands.add("ROWS: Get Rows");
		commands.add("COLS: Get Columns");
		commands.add("CHANGE: Change selected matrix");
		commands.add("PRINT: Prints selected matrix");
		commands.add("PRINTA: Prints all matrices");
		commands.add("What would you like to do? ");
		return commands;
	}
	
	/**
	 * Displays all the commands
	 * @param commands - commands the user can select from
	 */
	private static void printCommands(ArrayList<String> commands) {
		for (String command : commands) {
			System.out.println(command);
		}
	}
	
	/**
	 * @return matrix that is currently selected, null if none
	 */
	private static Matrix getSelectedMatrix() {
		for (Matrix m : matrices) {
			if (m.getSelected()) {
				return m;
			}
		}
		return null;
	}

}