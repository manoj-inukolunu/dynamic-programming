package leetcode.DP;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author manoji on 1/27/20.
 */
public class LongestIncreasingSequence {

  private int maxLength = 0;

  private int diff = -8645;

  public int lengthOfLIS(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    Integer[][] dp = new Integer[nums.length + 1][nums.length + 1];
    return lis(nums, -1, 0, dp);
  }

  public int findLISLength(int[] nums) {
    int[] dp = new int[nums.length];
    dp[0] = 1;

    int maxLength = 1;
    for (int i = 1; i < nums.length; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] - nums[j] == diff && dp[i] <= dp[j]) {
          dp[i] = dp[j] + 1;
          maxLength = Math.max(maxLength, dp[i]);
        }
      }
    }
    return maxLength;
  }

  private int lis(int[] nums, int prevIndex, int currentIndex, Integer[][] dp) {
    if (currentIndex >= nums.length) {
      return 0;
    }
    int including = 0;
    if (dp[currentIndex][prevIndex + 1] != null) {
      return dp[currentIndex][prevIndex + 1];
    }
    if (prevIndex == -1 || nums[currentIndex] - nums[prevIndex] == diff) {
      including = 1 + lis(nums, currentIndex, currentIndex + 1, dp);
    }
    int excluding = lis(nums, prevIndex, currentIndex + 1, dp);
    dp[currentIndex][prevIndex + 1] = Math.max(including, excluding);
    return Math.max(including, excluding);
  }

  public static void main(String[] args) throws Exception {
    LongestIncreasingSequence longestIncreasingSequence = new LongestIncreasingSequence();
    File file = new File("/Users/manoji/data.txt");
    Scanner scanner = new Scanner(file);
    List<Integer> integer = new ArrayList();
    while (scanner.hasNextLine()) {
      String[] arr = scanner.nextLine().split(",");
      for (String str : arr) {
        integer.add(Integer.parseInt(str));
      }
    }
    int[] array = new int[]{10, 9, 2, 5, 3, 7, 101, 18};//integer.stream().mapToInt(i -> i).toArray();
    System.out.println(longestIncreasingSequence.findLISLength(array));
  }
}
