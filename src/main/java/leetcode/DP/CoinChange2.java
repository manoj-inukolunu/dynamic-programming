package leetcode.DP;

import java.util.Arrays;

public class CoinChange2 {

  public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    for (int i = 1; i <= amount; i++) {
      for (int j = 0; j < coins.length; j++) {
        if (i == j) {
          dp[i]++;
        }
        if (i - coins[j] > 0) {
          dp[i] = dp[i - coins[j]];
        }
      }
    }
    System.out.println(Arrays.toString(dp));
    return dp[amount];
  }

}
