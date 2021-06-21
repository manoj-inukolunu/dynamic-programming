package leetcode.DP;

/**
 * @author manoji on 2020-01-21.
 */
public class IntegerBreak {

  public int integerBreak(int n) {
    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = i + 1;
    }
    Integer[][] dp = new Integer[n + 1][n + 1];
    return intB(nums, 0, n, n, dp);
  }

  private int intB(int[] nums, int index, int sum, int number, Integer[][] dp) {
    if (index >= nums.length) {
      return 1;
    }
    if (dp[index][sum] != null) {
      return dp[index][sum];
    }
    int includingNum = 1;
    if (nums[index] <= sum && nums[index] != number) {
      includingNum = nums[index] * intB(nums, index, sum - nums[index], number, dp);
    }
    int excludingNum = intB(nums, index + 1, sum, number, dp);
    dp[index][sum] = Math.max(includingNum, excludingNum);
    return Math.max(includingNum, excludingNum);
  }

  public static void main(String args[]) {
    IntegerBreak integerBreak = new IntegerBreak();
    Integer intBreak = integerBreak.integerBreak(10);
    System.out.println();
    System.out.println(intBreak);
  }

}
