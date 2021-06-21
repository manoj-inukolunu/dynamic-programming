package leetcode.DP;

/**
 * @author manoji on 2/13/20.
 */
public class MaximumSumSubArray {

  public int maxSubArray(int[] nums) {
    int size = nums.length;
    int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

    for (int i = 0; i < size; i++) {
      max_ending_here = max_ending_here + nums[i];
      if (max_so_far < max_ending_here) {
        max_so_far = max_ending_here;
      }
      if (max_ending_here < 0) {
        max_ending_here = 0;
      }
    }
    return max_so_far;
  }

  public int max(int[] nums, int start, int end) {
    int max = Integer.MIN_VALUE;

    for (int i = start; i <= end; i++) {
      int left = max(nums, start, i);
      int right = max(nums, i + 1, end);
      if (max < Math.max(left, right)) {
        max = Math.max(left, right);
      }
    }

    return max;
  }

  private int maxRecur(int[] nums, int start, int end) {
    int sum = 0;
    for (int i = start; i <= end; i++) {
      sum += nums[i];
    }
    return sum;
  }
}
