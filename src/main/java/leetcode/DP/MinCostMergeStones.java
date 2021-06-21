package leetcode.DP;

import com.google.common.base.Stopwatch;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author manoji on 2/13/20.
 */
public class MinCostMergeStones {

  public int merge(int[] stones, int start, int end, int k) {
    if (start == end) {
      return stones[start];
    }
    if (start > end) {
      return 0;
    }
    if (end - start == k - 1) {
      return sums(stones, start, end);
    }
    int min = Integer.MAX_VALUE;
    for (int i = start; i <= end; i++) {
      int mergeCost = Integer.MAX_VALUE;
      if (canInclude(stones, i, k)) {
        mergeCost = merge(stones, start, i - 1, k)
            + sums(stones, i, i + k - 1)
            + merge(stones, i + k, end, k);
      }
      if (mergeCost < min) {
        min = mergeCost;
      }
    }
    System.out.println("Cost for merging start=" + start + " end=" + end + " = " + min);
    return min;

  }

  public int mergeStones(int[] stones, int K) {
//		HashMap<String, Integer> map = new HashMap<>();
    int val = merge(stones, 0, stones.length - 1, K);

    return val == Integer.MAX_VALUE ? -1 : val;
  }


  int sums(int[] nums, int start, int end) {
    int sum = 0;
    for (int i = start; i <= end; i++) {
      sum += nums[i];
    }
    return sum;
  }

  private int mergeStonesRecur(int[] stones, int currentIndex, int k, HashMap<String, Integer> map) {
    String key = Arrays.toString(stones);

    if (map.containsKey(key)) {
      return map.get(key);
    }
    //	System.out.println("Solving for key =" + key);
    if (currentIndex >= stones.length) {
      return Integer.MAX_VALUE;
    }
    if (stones.length == 1) {
      return 0;
    }
    if (stones.length < k) {
      return Integer.MAX_VALUE;
    }

    int include = Integer.MAX_VALUE;
    if (canInclude(stones, currentIndex, k)) {
      int sums = sums(stones, currentIndex, currentIndex + k - 1);
      int res = mergeStonesRecur(getArray(stones, currentIndex, k, sums), 0, k, map);
      if (res != Integer.MAX_VALUE) {
        include = sums + res;
      }
    }
    int exclude = mergeStonesRecur(stones, currentIndex + 1, k, map);
    map.put(key, Math.min(include, exclude));
    return Math.min(include, exclude);
  }

  private int[] getArray(int[] stones, int currentIndex, int k, int sums) {
    int[] arr = new int[stones.length - (k - 1)];
    arr[currentIndex] = sums;

    for (int i = 0; i < currentIndex; i++) {
      arr[i] = stones[i];
    }

    int j = currentIndex + k - 1;
    for (int i = currentIndex + 1; i < arr.length; i++) {
      arr[i] = stones[++j];
    }
    return arr;
  }

  private boolean canInclude(int[] stones, int currentIndex, int k) {
    return (currentIndex + k - 1) < stones.length;
  }

  public static void main(String args[]) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    MinCostMergeStones minCostMergeStones = new MinCostMergeStones();
    int[] arr = new int[]{3, 2, 4, 1};
    int val = minCostMergeStones.mergeStones(arr, 2);

    System.out.println(val);

    System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
  }

  class Solution {

    public int maxProduct(int[] nums) {
      return (int) maximumSumSubarray(nums, nums.length);
    }


    private long maximumSumSubarray(int arr[], int n) {
      long min_prefix_sum = 0L;
      Long res = Long.MIN_VALUE;
      Long prefix_sum[] = new Long[n];
      prefix_sum[0] = Long.valueOf(arr[0]);
      for (int i = 1; i < n; i++) {
        prefix_sum[i] = prefix_sum[i - 1] * arr[i];
      }

      for (int i = 0; i < n; i++) {
        res = Math.max(res, prefix_sum[i] / min_prefix_sum);
        min_prefix_sum = Math.min(min_prefix_sum, prefix_sum[i]);
      }

      return res;
    }

  }

}
