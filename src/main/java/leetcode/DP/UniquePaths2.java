package leetcode.DP;

/**
 * @author manoji on 2/1/20.
 */
public class UniquePaths2 {

  public int unI(int[][] obstacleGrid) {
    int[][] grid = new int[obstacleGrid.length][obstacleGrid[0].length];
    int rows = obstacleGrid.length;
    int columns = obstacleGrid[0].length;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (canComeFromLeft(j, obstacleGrid, i) && canComeFromTop(i, obstacleGrid, j)) {
          grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
        } else if (canComeFromLeft(j, obstacleGrid, i) && !canComeFromTop(i, obstacleGrid, j)) {
          grid[i][j] = grid[i][j - 1];
        } else if (!canComeFromLeft(j, obstacleGrid, i) && canComeFromTop(i, obstacleGrid, j)) {
          grid[i][j] = grid[i - 1][j];
        } else {
          grid[i][j] = 1;
        }
      }
    }
    return grid[rows - 1][columns - 1];
  }

  private boolean canComeFromLeft(int currentColumn, int[][] obstacleGrid, int currentRow) {
    return currentColumn - 1 >= 0 && obstacleGrid[currentRow][currentColumn - 1] != 1;
  }

  private boolean canComeFromTop(int currentRow, int[][] obstacleGrid, int currentColumn) {
    return currentRow - 1 >= 0 && obstacleGrid[currentRow - 1][currentColumn] != 1;
  }

  private boolean canGoRight(int column, int row, int numColumns, int[][] grid) {
    return column + 1 < numColumns && grid[row][column + 1] != 1;
  }

  private boolean canGoDown(int column, int row, int numRows, int[][] grid) {
    return row + 1 < numRows && grid[row + 1][column] != 1;
  }

  private int recur(int[][] grid, int row, int column, int numRows, int numColumns) {
    if (row == numRows - 1 && column == numColumns - 1 && grid[row][column] != 1) {
      return 1;
    }
    int goRight = 0;
    if (canGoRight(column, row, numColumns, grid)) {
      goRight = recur(grid, row, column + 1, numRows, numColumns);
    }
    int goDown = 0;
    if (canGoDown(column, row, numRows, grid)) {
      goDown = recur(grid, row + 1, column, numRows, numColumns);
    }
    return goRight + goDown;
  }

  public static void main(String args[]) {
    UniquePaths2 uniquePaths2 = new UniquePaths2();
    int[][] arr = new int[][]{
        {1}
    };
    System.out.println(uniquePaths2.unI(arr));

    System.out.println(uniquePaths2.unr(arr));
  }

  private int unr(int[][] arr) {
    return recur(arr, 0, 0, arr.length, arr[0].length);
  }


}
