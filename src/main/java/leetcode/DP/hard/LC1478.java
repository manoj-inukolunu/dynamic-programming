package leetcode.DP.hard;


import java.util.Arrays;

public class LC1478 {

  int[][] dp;

  public int minDistance(int[] houses, int k) {
    Arrays.sort(houses);
    int[][] cost = new int[houses.length][houses.length];
    for (int i = 0; i < houses.length; i++) {
      for (int j = i; j < houses.length; j++) {
        for (int t = i; t <= j; t++) {
          cost[i][j] += (Math.abs(houses[(i + j) / 2] - houses[t]));
        }
      }
    }
    dp = new int[houses.length][houses.length];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    return dfs(0, houses.length - 1, k, cost);
  }

  private int dfs(int start, int end, int k, int[][] cost) {
    if (start > end) {
      return Integer.MAX_VALUE / 2;
    }
    if (k == 1) {
      return cost[start][end];
    }
    if (dp[start][end] != -1) {
      return dp[start][end];
    }

    int best = Integer.MAX_VALUE;
    for (int i = start; i <= end; i++) {
      best = Math.min(best, cost[start][i] + dfs(i + 1, end, k - 1, cost));
    }
    dp[start][end] = best;
    return best;
  }

  public static void main(String[] args) {
    LC1478 l = new LC1478();
    int ans = l.minDistance(new int[]{3, 6, 14, 10}, 4);
    System.out.println(ans);
  }
}



