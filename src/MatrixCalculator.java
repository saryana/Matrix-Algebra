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
				System.out.print("What matrix do you want to add to the selected matrix? (e.g. 1, 2) ");
				Matrix sum = selected.add(matrices.get(console.nextInt() - 1));
				matrices.add(sum);
				System.out.println(sum.toString());
			} else if (request.equals("multiply")) {
				System.out.print("What matrix do you want to add to the selected matrix? (e.g. 1, 2) ");
				Matrix product = selected.multiply(matrices.get(console.nextInt() - 1));
				matrices.add(product);
				System.out.println(product.toString());
			} else if (request.equals("pow")) {
				System.out.print("By what power? ");
				selected.power(console.nextInt(), selected);
			} else if (request.equals("sm")) {
				System.out.print("By what scalar? ");
				selected.scalarMultiplcation(console.nextInt());
			} else if (request.equals("trans")) {
				selected.transpose();
			} else if (request.equals("rows")) {
				System.out.println(selected.getRows());
			} else if (request.equals("cols")) {
				System.out.println(selected.getColumns());
			} else if (request.equals("cs")) {
				selectMatrix(console);
				selected = getSelectedMatrix();
			} else if (request.equals("printa")) {
				printAll();
			} else if (request.equals("print")) {
				System.out.println(selected.toString());
			}
			System.out.println();
			printCommands(commands);
			request = console.next();
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
		for (Matrix matrix : matrices) {
			System.out.println(matrix.toString());
		}
	}
	
	/**
	 * Easily allows for addition of commands
	 * @return All the commands the user can select
	 */
	private static ArrayList<String> getCommands() {
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("Q: Quit");
		commands.add("FILL: Fill Matrix");
		commands.add("ADD: Add Matrix");
		commands.add("MULTIPLY: Multiply a matrix");
		commands.add("POW: Raises matrix to certain power");
		commands.add("SM: Scalar Multiply");
		commands.add("TRAN: Transposes");
		commands.add("ROWS: Get Rows");
		commands.add("COLS: Get Columns");
		commands.add("CS: Change Selected");
		commands.add("PRINT: Print Matrix");
		commands.add("PRINTA: Print All Matrices");
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