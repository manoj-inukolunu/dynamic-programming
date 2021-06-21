package leetcode.DP;

import java.util.HashMap;

/**
 * @author manoji on 3/6/20.
 */
public class LongestArithmeticSubsequenceOfAGIvenDifference {

  public int longestSubsequence(int[] arr, int difference) {
    int[] dp = new int[arr.length];
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      dp[i] = 1;
      if (!map.containsKey(arr[i] - difference)) {
        for (int j = 0; j < i; j++) {
          if (arr[i] - arr[j] == difference) {
            dp[i] = Math.max(dp[i], dp[j] + 1);
            map.put(arr[i], dp[i]);
            map.put(arr[i] - difference, dp[i] - 1);
          }
        }
      } else {
        int count = map.get(arr[i] - difference);
        map.put(arr[i], count + 1);
        dp[i] = count + 1;
      }
    }
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < dp.length; i++) {
      if (dp[i] > max) {
        max = dp[i];
      }
    }
    return max;
  }

  public static void main(String args[]) {
    LongestArithmeticSubsequenceOfAGIvenDifference l = new LongestArithmeticSubsequenceOfAGIvenDifference();
    System.out.println(l.longestSubsequence(new int[]{1, 2, 3, 4}, 1));
  }

}
