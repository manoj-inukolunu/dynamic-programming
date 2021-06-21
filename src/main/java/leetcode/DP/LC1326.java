package leetcode.DP;


import java.util.Arrays;

public class LC1326 {


  public int minTaps(int n, int[] ranges) {

    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 0; i <= n; i++) {
      int left = Math.max(i - ranges[i], 0);
      int right = Math.min(i + ranges[i], n);
      for (int j = left + 1; j <= right; j++) {
        dp[j] = Math.min(dp[j], dp[left] + 1);
      }
    }
    return dp[n] != Integer.MAX_VALUE ? dp[n] : -1;

  }

  public static void main(String[] args) {
    LC1326 l = new LC1326();
    System.out.println(l.minTaps(7, new int[]{1, 2, 1, 0, 2, 1, 0, 1}));
  }
}



