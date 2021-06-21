package leetcode.DP;

import java.util.Arrays;

/**
 * @author manoji on 3/7/20.
 */
public class GreatestSumDivBy3 {

  public int maxSumDivThree(int[] A) {
    int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
    for (int a : A) {
      int[] dp2 = new int[3];
      for (int i = 0; i < 3; ++i) {
        dp2[(i + a) % 3] = Math.max(dp[(i + a) % 3], dp[i] + a);
      }
      System.out.println("DP 2 " + Arrays.toString(dp2));
      System.out.println("DP 1 " + Arrays.toString(dp));
      dp = dp2;
    }
    return dp[0];
  }


  public static void main(String args[]) {
    GreatestSumDivBy3 gd = new GreatestSumDivBy3();

    System.out.println(gd.maxSumDivThree(new int[]{3, 6, 5, 1, 8}));
  }
}
