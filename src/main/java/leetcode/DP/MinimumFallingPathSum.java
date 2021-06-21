package leetcode.DP;


import java.util.Arrays;

/**
 * @author manoji on 1/30/20.
 */
public class MinimumFallingPathSum {

  public int minFallingPathSum(int[][] A) {

    int sum = Integer.MAX_VALUE;
    Integer[][] dp = new Integer[A.length + 1][A[0].length + 1];
    for (int i = 0; i < A[0].length; i++) {
      int val = minFPathSum(A, 0, i, A.length, A[0].length, A[0][i], dp);
      System.out.println("Val for " + A[0][i] + " is " + val);
      if (val < sum) {
        sum = val;
      }
    }
    for (Integer[] row : dp) {
      System.out.println(Arrays.toString(row));
    }
    return sum;
  }

  private int minFPathSum(int[][] grid, int currentRow, int currentColumn, int numRows, int numColumns, int sum, Integer[][] dp) {
    if (currentRow >= numRows) {
      return sum;
    }
    if (dp[currentRow][currentColumn] != null) {
      return dp[currentRow][currentColumn];
    }
    if (currentRow + 1 < numRows) {
      int col0Sum = Integer.MAX_VALUE;
      if (currentColumn - 1 >= 0) {
        col0Sum = minFPathSum(grid, currentRow + 1, currentColumn - 1, numRows, numColumns, sum + grid[currentRow + 1][currentColumn - 1], dp);
      }
      int col1Sum = minFPathSum(grid, currentRow + 1, currentColumn, numRows, numColumns, sum + grid[currentRow + 1][currentColumn], dp);
      int col2Sum = Integer.MAX_VALUE;
      if (currentColumn + 1 < numColumns) {
        col2Sum = minFPathSum(grid, currentRow + 1, currentColumn + 1, numRows, numColumns, sum + grid[currentRow + 1][currentColumn + 1], dp);
      }
      dp[currentRow][currentColumn] = Math.min(col0Sum, Math.min(col1Sum, col2Sum));
      return Math.min(col0Sum, Math.min(col1Sum, col2Sum));
    }
    return sum;
  }

  public static void main(String args[]) {
    MinimumFallingPathSum minumumPathSum = new MinimumFallingPathSum();
		/*
		[[10,-98,44],[-20,65,34],[-100,-1,74]]
		[[-52,47,-77,-56],[59,-34,63,-80],[-71,40,-91,15],[82,7,36,-95]]
		 */
    //[[10,-98,44],[-20,65,34],[-100,-1,74]]
    int[][] grid = new int[][]{
        {-52, 47, -77, -56},
        {59, -34, 63, -80},
        {-71, 40, -91, 15},
        {82, 7, 36, -95}
    };

    int[][] arr = new int[grid.length][grid[0].length];
    int columns = grid[0].length;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (canComeFromTop(i) && canComeFromTopLeft(i, j) && canComeFromTopRight(i, j, columns)) {
          arr[i][j] = grid[i][j] + Math.min(arr[i - 1][j - 1], Math.min(arr[i - 1][j], arr[i - 1][j + 1]));
        } else if (canComeFromTop(i) && !canComeFromTopLeft(i, j) && !canComeFromTopRight(i, j, columns)) {
          arr[i][j] = grid[i][j] + arr[i - 1][j];
        } else if (canComeFromTop(i) && canComeFromTopLeft(i, j) && !canComeFromTopRight(i, j, columns)) {
          arr[i][j] = grid[i][j] + Math.min(arr[i - 1][j], arr[i - 1][j - 1]);
        } else if (canComeFromTop(i) && canComeFromTopRight(i, j, columns) && !canComeFromTopLeft(i, j)) {
          arr[i][j] = grid[i][j] + Math.min(arr[i - 1][j], arr[i - 1][j + 1]);
        } else if (!canComeFromTop(i) && !canComeFromTopLeft(i, j) && !canComeFromTopRight(i, j, columns)) {
          arr[i][j] = grid[i][j];
        }
      }
    }

    for (int[] row : arr) {
      System.out.println(Arrays.toString(row));
    }
    System.out.println(minumumPathSum.minFallingPathSum(grid));
  }


  private static boolean canComeFromTopLeft(int currentRow, int currentColumn) {
    return (currentRow - 1 >= 0 && currentColumn - 1 >= 0);
  }

  private static boolean canComeFromTop(int currentRow) {
    return (currentRow - 1 >= 0);
  }

  private static boolean canComeFromTopRight(int currentRow, int currentColumn, int numColumns) {
    return (currentRow - 1 >= 0 && currentColumn + 1 < numColumns);
  }

}
