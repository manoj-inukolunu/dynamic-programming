package leetcode.DP.hard;


import java.util.Arrays;

public class LC956 {

  int MAXR = 5005;
  int MAXN = 22;


  public int tallestBillboard(int[] rods) {
    int[][] dp = new int[MAXN][2 * MAXR];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    int seen[][] = new int[MAXN][2 * MAXR];
    for (int[] row : seen) {
      Arrays.fill(row, -1);
    }
    return dfs(rods, 0, 0, 0, dp, seen);
  }

  private int dfs(int[] rods, int idx, int leftSum, int rightSum, int[][] dp, int[][] seen) {
    if (idx >= rods.length) {
      return leftSum == rightSum ? 0 : Integer.MIN_VALUE / 2;
    }
    if (seen[idx][leftSum - rightSum + MAXR] != -1) {
      return dp[idx][leftSum - rightSum + MAXR];
    }
    seen[idx][leftSum - rightSum + MAXR] = 1;

    int noAdd = dfs(rods, idx + 1, leftSum, rightSum, dp, dp);
    int largerRod = rods[idx] + dfs(rods, idx + 1, leftSum + rods[idx], rightSum, dp, dp);
    int smallerRod = dfs(rods, idx + 1, leftSum, rightSum + rods[idx], dp, dp);
    int max = Math.max(noAdd, Math.max(largerRod, smallerRod));
    dp[idx][leftSum - rightSum + MAXR] = max;

    return dp[idx][leftSum - rightSum + MAXR];
  }


  public static void main(String[] args) {
    LC956 l = new LC956();
    System.out.println(l.tallestBillboard(new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 50, 50, 50, 150, 150, 150, 100, 100, 100, 123}));
  }
}



