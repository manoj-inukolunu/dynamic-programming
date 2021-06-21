package leetcode.DP;

/**
 * @author manoji on 1/25/20.
 */
public class MinumumPathSum {

  public int minPathSum(int[][] grid) {

    int rows = grid.length;
    int columns = grid[0].length;

    Integer[][] dp = new Integer[rows + 1][columns + 1];

    return minPath(grid, 0, 0, dp);

  }

  private int minPath(int[][] grid, int currentRow, int currentColumn, Integer[][] dp) {
    if (currentRow == grid.length - 1 && currentColumn == grid[0].length - 1) {
      return grid[currentRow][currentColumn];
    }
    if (dp[currentRow][currentColumn] != null) {
      return dp[currentRow][currentColumn];
    }
    int moveRight = Integer.MAX_VALUE;
    if (canMoveRight(currentColumn, grid[0].length)) {
      moveRight = grid[currentRow][currentColumn] + minPath(grid, currentRow, currentColumn + 1, dp);
    }

    int moveDown = Integer.MAX_VALUE;
    if (canMoveDown(currentRow, grid.length)) {
      moveDown = grid[currentRow][currentColumn] + minPath(grid, currentRow + 1, currentColumn, dp);
    }

    if (moveDown == Integer.MAX_VALUE && moveRight != Integer.MAX_VALUE) {
      dp[currentRow][currentColumn] = moveRight;
      return moveRight;
    }

    if (moveRight == Integer.MAX_VALUE && moveDown != Integer.MAX_VALUE) {
      dp[currentRow][currentColumn] = moveDown;
      return moveDown;
    }

    if (moveRight == Integer.MAX_VALUE && moveDown == Integer.MAX_VALUE) {
      dp[currentRow][currentColumn] = 0;
      return 0;
    }
    dp[currentRow][currentColumn] = Math.min(moveRight, moveDown);
    return Math.min(moveRight, moveDown);
  }


  private boolean canMoveRight(int currentColumn, int totalColumns) {
    return currentColumn + 1 < totalColumns;
  }

  private boolean canMoveDown(int currentRow, int totalRows) {
    return currentRow + 1 < totalRows;
  }

  public static void main(String args[]) {
    MinumumPathSum minumumPathSum = new MinumumPathSum();
    int[][] grid = new int[][]{
        {1, 3, 1},
        {1, 1, 1},
        {4, 2, 1}
    };
    System.out.println(minumumPathSum.minPathSum(grid));
  }

}
