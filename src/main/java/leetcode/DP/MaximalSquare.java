package leetcode.DP;

/**
 * @author manoji on 4/27/20.
 */
public class MaximalSquare {

  public int maximalSquare(char[][] matrix) {
    int[][] res = new int[matrix.length][matrix[0].length];
    int max = 0;

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        res[i][j] = matrix[i][j];
        if (i > 0 && j > 0 && matrix[i][j] == '1') {
          res[i][j] = Math.min(res[i][j - 1], Math.min(res[i - 1][j], res[i - 1][j - 1]));
          if (max < res[i][j]) {
            max = res[i][j];
          }
        }
      }
    }
    return max;
  }


}
