package leetcode.DP;


import java.util.Arrays;

public class LC1335 {

  public int minDifficulty(int[] jobDifficulty, int d) {
    int[][] dp = new int[jobDifficulty.length + 1][d + 1];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    int val = go(jobDifficulty, 0, 1, d, dp);
    return val >= Integer.MAX_VALUE / 2 ? -1 : val;
  }

  private int go(int[] arr, int start, int day, int totalDays, int[][] dp) {
    if (start >= arr.length && day < totalDays) {
      return Integer.MAX_VALUE / 2;
    }
    if (day > totalDays && start < arr.length) {
      return Integer.MAX_VALUE / 2;
    }
    if (day > totalDays) {
      return 0;
    }
    if (start >= arr.length) {
      return Integer.MAX_VALUE / 2;
    }
    if (dp[start][day] != -1) {
      return dp[start][day];
    }
    int best = Integer.MAX_VALUE;
    int curr = 0;
    for (int i = start; i < arr.length; i++) {
      curr = Math.max(arr[i], curr);
      best = Math.min(curr + go(arr, i + 1, day + 1, totalDays, dp), best);
    }
    dp[start][day] = best;
    return best;
  }

  public static void main(String[] args) {
    LC1335 l = new LC1335();
    System.out.println(l.minDifficulty(new int[]{6, 5, 4, 3, 2, 1}, 2));
  }
}



