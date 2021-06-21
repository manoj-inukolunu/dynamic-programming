package leetcode.DP;

public class MaxProductPath {

  private boolean inside(int row, int col, int[][] grid) {
    return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
  }

  long max(long a, long b, long c, long d) {
    return Math.max(Math.max(a, b), Math.max(c, d));
  }

  long min(long a, long b, long c, long d) {
    return Math.min(Math.min(a, b), Math.min(c, d));
  }


  public int maxProductPath(int[][] grid) {

    Long[][][] dp = new Long[grid.length][grid[0].length][2];
    dp[0][0][0] = Long.valueOf(grid[0][0]);
    dp[0][0][1] = Long.valueOf(grid[0][0]);
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        int curr = grid[i][j];
        if (inside(i - 1, j, grid) && inside(i, j - 1, grid)) {
          dp[i][j][0] = max(curr * dp[i - 1][j][0], curr * dp[i - 1][j][1], curr * dp[i][j - 1][0], curr * dp[i][j - 1][1]);
          dp[i][j][1] = min(curr * dp[i - 1][j][0], curr * dp[i - 1][j][1], curr * dp[i][j - 1][0], curr * dp[i][j - 1][1]);
        } else if (inside(i - 1, j, grid) && !inside(i, j - 1, grid)) {
          dp[i][j][0] = curr * dp[i - 1][j][0];
          dp[i][j][1] = curr * dp[i - 1][j][1];
        } else if (!inside(i - 1, j, grid) && inside(i, j - 1, grid)) {
          dp[i][j][0] = curr * dp[i][j - 1][0];
          dp[i][j][1] = curr * dp[i][j - 1][1];
        }
      }
    }

    int mod = (int) (Math.pow(10, 9) + 7);
    int ans = (int) (Math.max(dp[grid.length - 1][grid[0].length - 1][0], dp[grid.length - 1][grid[0].length - 1][1]) % mod);
    return ans < 0 ? -1 : ans;
  }


  public static void main(String args[]) {
    MaxProductPath m = new MaxProductPath();
    int[][] arr = new int[][]{
        {1, -2, 1},
        {1, -2, 1},
        {3, -4, 1}
    };

    System.out.println(m.maxProductPath(arr));

  }

}
