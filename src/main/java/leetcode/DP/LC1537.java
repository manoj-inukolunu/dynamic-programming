package leetcode.DP;


import java.util.Arrays;

public class LC1537 {

  boolean[][] seen;

  public int maxSum(int[] nums1, int[] nums2) {
    seen = new boolean[2][];
    seen[0] = new boolean[nums1.length];
    seen[1] = new boolean[nums2.length];
    int[][] dp = new int[2][];
    dp[0] = new int[nums1.length];
    dp[1] = new int[nums2.length];
    Arrays.fill(dp[0], -1);
    Arrays.fill(dp[1], -1);
    for (boolean[] arr : seen) {
      Arrays.fill(arr, false);
    }
    int one = go(nums1, nums2, 0, 0, -1, dp);
    for (boolean[] arr : seen) {
      Arrays.fill(arr, false);
    }
    Arrays.fill(dp[0], -1);
    Arrays.fill(dp[1], -1);
    int two = go(nums1, nums2, 0, 1, -1, dp);

    return Math.max(one, two);
  }


  private int go(int[] nums1, int[] nums2, int idx, int arr, int prevArr, int[][] dp) {
    if (arr == 1 && idx >= nums2.length) {
      return 0;
    }
    if (arr == 0 && idx >= nums1.length) {
      return 0;
    }
    if (dp[arr][idx] != -1) {
      return dp[arr][idx];
    }
    int best = Integer.MIN_VALUE;
    if (arr == 0) {
      best = Math.max(best, go(nums1, nums2, idx + 1, arr, arr, dp) + nums1[idx]);
      int otherIdx = getIdx(nums2, nums1[idx]);
      if (otherIdx >= 0) {
        best = Math.max(best, go(nums1, nums2, otherIdx + 1, 1, 0, dp) + nums1[idx]);
      }
    } else {
      best = Math.max(best, nums2[idx] + go(nums1, nums2, idx + 1, arr, arr, dp));
      int otherIdx = getIdx(nums1, nums2[idx]);
      if (otherIdx >= 0) {
        best = Math.max(best, go(nums1, nums2, otherIdx + 1, 0, 1, dp) + nums2[idx]);
      }
    }
    dp[arr][idx] = best;
    return best;
  }

  private int getIdx(int[] arr, int num) {
    return Arrays.binarySearch(arr, num);
  }

  public static void main(String[] args) {
    LC1537 l = new LC1537();
    System.out.println(l.maxSum(new int[]{2, 4, 5, 8, 10}, new int[]{4, 6, 8, 9}));
  }
}



