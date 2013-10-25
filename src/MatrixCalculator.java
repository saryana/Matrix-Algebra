import java.util.ArrayList;
import java.util.Scanner;

/**
 * Allows user to do operations to matrices. 
 * @author Sean
 *
 */
public class MatrixCalculator {

	public static Matrix[] matrices = new Matrix[2];
	
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		ArrayList<String> commands = getCommands();
		
		System.out.print("Dimensions for first matrix? (space seperated) ");
		Matrix matrixOne = new Matrix(console.nextInt(), console.nextInt());
		matrixOne.fillMatrix();
		
		System.out.print("Dimensions for the second matrix? (space seperated) ");
		Matrix matrixTwo = new Matrix(console.nextInt(), console.nextInt());
		matrixTwo.fillMatrix();
		
		matrices[0] = matrixOne;
		matrices[1] = matrixTwo;
		
		System.out.println("Select Matrix (1 or 2)");
		
		if (console.nextInt() == 1) {
			matrixOne.setSelected(true);
		} else {
			matrixTwo.setSelected(true);
		}
		
		printCommands(commands);
		String request = console.next().toLowerCase();
		
		Matrix selected = getSelectedMatrix();
		while (!request.equals("q")) {
			if (request.equals("fill")) {
				selected.fillMatrix();
			} else if (request.equals("add")) {
				System.out.print("What matrix do you want to add to the selected matrix? (e.g. 1, 2) ");
				selected.add(matrices[console.nextInt() - 1]);
			} else if (request.equals("sm")) {
				System.out.print("By what scalar? ");
				selected.scalarMultiplcation(console.nextInt());
			} else if (request.equals("rows")) {
				System.out.println(selected.getRows());
			} else if (request.equals("cols")) {
				System.out.println(selected.getColumns());
			} else if (request.equals("cs")) {
				System.out.println(selected.getRows());
			} else if (request.equals("print")) {
				System.out.println(selected.toString());
			}
			System.out.println();
			printCommands(commands);
			request = console.next();
		}
	}
	
	private static ArrayList<String> getCommands() {
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("Q: Quit");
		commands.add("FILL: Fill Matrix");
		commands.add("ADD: Add Matrix");
		commands.add("SM: Scalar Multiply");
		commands.add("ROWS: Get Rows");
		commands.add("COLS: Get Columns");
		commands.add("CS: Change Selected");
		commands.add("PRINT: Print Matrix");
		commands.add("What would you like to do? ");
		return commands;
	}
	
	private static void printCommands(ArrayList<String> commands) {
		for (String command : commands) {
			System.out.println(command);
		}
	}
	
	private static Matrix getSelectedMatrix() {
		for (Matrix m : matrices) {
			if (m.getSelected()) {
				return m;
			}
		}
		return null;
	}

}
