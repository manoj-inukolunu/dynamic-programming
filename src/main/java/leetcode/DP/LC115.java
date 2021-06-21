package leetcode.DP;


import java.util.Arrays;

public class LC115 {

  public int numDistinct(String s, String t) {
    int[][] dp = new int[s.length() + 1][t.length() + 1];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    return dfs(s, t, 0, 0, dp);
  }

  private int dfs(String s, String t, int i, int j, int[][] dp) {
    if (j >= t.length()) {
      return 1;
    }
    if (i >= s.length()) {
      return 0;
    }
    if (dp[i][j] != -1) {
      return dp[i][j];
    }
    if (s.charAt(i) == t.charAt(j)) {
      int incl = dfs(s, t, i + 1, j + 1, dp);
      int excl = dfs(s, t, i + 1, j, dp);
      dp[i][j] = incl + excl;
      return incl + excl;
    } else {
      dp[i][j] = dfs(s, t, i + 1, j, dp);
      return dp[i][j];
    }
  }

  public static void main(String[] args) {
    LC115 l = new LC115();
    System.out.println(l.numDistinct("babgbag", "bag"));
  }
}



