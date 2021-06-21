package leetcode.DP;

import java.util.Arrays;

/**
 * @author manoji on 2/21/20.
 */
public class NumMatrix {

  int[][] prefixMatrix;

  public NumMatrix(int[][] matrix) {
    prefixMatrix = new int[matrix.length][matrix[0].length];
    for (int row = 0; row < prefixMatrix.length; row++) {
      prefixMatrix[row][0] = matrix[row][0];
    }

    for (int row = 0; row < prefixMatrix.length; row++) {
      for (int column = 1; column < prefixMatrix[0].length; column++) {
        prefixMatrix[row][column] = prefixMatrix[row][column - 1] + matrix[row][column];
      }
    }

		/*for (int row = 1; row < prefixMatrix.length; row++) {
			for (int column = 0; column < prefixMatrix[0].length; column++) {
				prefixMatrix[row][column] = prefixMatrix[row - 1][column] + prefixMatrix[row][column];
			}
		}
*/
    for (int[] row : prefixMatrix) {
      System.out.println(Arrays.toString(row));
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    int sum = 0;
    for (int i = row1; i <= row2; i++) {
      if (col1 - 1 >= 0) {
        sum += (prefixMatrix[i][col2] - prefixMatrix[i][col1 - 1]);
      } else {
        sum += prefixMatrix[i][col2];
      }
    }
    return sum;
  }


  public static void main(String args[]) {

    int[][] mat = new int[][]{
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    NumMatrix numMatrix = new NumMatrix(mat);

    System.out.println(numMatrix.sumRegion(1, 0, 2, 1));
  }
}
