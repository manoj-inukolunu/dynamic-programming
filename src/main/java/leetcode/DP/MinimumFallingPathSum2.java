package leetcode.DP;

/**
 * @author manoji on 3/7/20.
 */
public class MinimumFallingPathSum2 {

  public int minFallingPathSum(int[][] arr) {
    int min = Integer.MAX_VALUE;
    for (int i = 1; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        int currMax = Integer.MAX_VALUE;
        for (int k = 0; k < arr[i].length; k++) {
          if (k != j) {
            int val = arr[i - 1][k] + arr[i][j];
            if (val < currMax) {
              currMax = val;
            }
          }
        }
        arr[i][j] = currMax != Integer.MAX_VALUE ? currMax : arr[i][j];
      }
    }

    for (int i = 0; i < arr[arr.length - 1].length; i++) {
      if (arr[arr.length - 1][i] < min) {
        min = arr[arr.length - 1][i];
      }
    }
    return min;
  }

  public static void main(String args[]) {
    MinimumFallingPathSum2 sum2 = new MinimumFallingPathSum2();

    int[][] arr = new int[][]{
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    System.out.println(sum2.minFallingPathSum(arr));
  }

}
