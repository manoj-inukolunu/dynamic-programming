package leetcode.DP;

/**
 * @author manoji on 1/25/20.
 */
public class UniquePaths {

  public int uniquePaths(int m, int n) {
    int rows = n;
    int columns = m;
    Integer[][] dp = new Integer[rows + 1][columns + 1];
    return numPaths(rows, columns, 0, 0, dp);
  }

  public int numPaths(int rows, int columns, int currentRow, int currentColumn, Integer[][] dp) {

	/*	if (currentRow > rows || currentColumn > columns) {
			return 0;
		}*/
    //reached end
    if (currentRow == rows - 1 && currentColumn == columns - 1) {
      return 1;
    }

    if (dp[currentRow][currentColumn] != null) {
      return dp[currentRow][currentColumn];
    }
    //go left
    int numPathLeft = 0;
    if (canMoveLeft(currentColumn, columns)) {
      numPathLeft = numPaths(rows, columns, currentRow, currentColumn + 1, dp);
    }
    //go down
    int numPathDown = 0;
    if (canMoveDown(currentRow, rows)) {
      numPathDown = numPaths(rows, columns, currentRow + 1, currentColumn, dp);
    }
    dp[currentRow][currentColumn] = numPathLeft + numPathDown;
    return numPathLeft + numPathDown;
  }

  boolean canMoveLeft(int currentColumn, int columns) {
    return currentColumn + 1 < columns;
  }

  boolean canMoveDown(int currentRow, int rows) {
    return currentRow + 1 < rows;
  }

  public static void main(String args[]) {
    UniquePaths paths = new UniquePaths();
    System.out.println(paths.uniquePaths(3, 2));
  }
}
