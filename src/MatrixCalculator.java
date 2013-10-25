/**
 * Allows user to do operations to matracies. 
 * @author Sean
 *
 */
public class MatrixCalculator {

	public static void main(String[] args) {
		Matrix matrixOne = new Matrix(2, 2);
		matrixOne.fillMatrix();
		System.out.println(matrixOne.toString());
	}

}
