package leetcode.DP;

import java.util.Arrays;

/**
 * @author manoji on 2/15/20.
 */
public class PartitionToKEqualSubsets {


  public boolean canPartitionKSubsets(int[] nums, int K) {
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }

    if (sum % K != 0 || K <= 0) {
      return false;
    }

    boolean[] picked = new boolean[nums.length];
    Arrays.fill(picked, false);

    return canPartitionRecursive(nums, 0, 0, 0, sum / K, K, picked);
  }

  private boolean canPartitionRecursive(int[] nums, int startIndex, int currentIndex, int currentSum, int targetSum, int partition,
      boolean[] picked) {
    if (partition == 1) {
      return true;
    }

    if (currentSum == targetSum) {
      return canPartitionRecursive(nums, 0, 0, 0, targetSum, partition - 1, picked);
    }
    for (int i = startIndex; i < nums.length; i++) {
      if (!picked[i]) {
        picked[i] = true;
        if (canPartitionRecursive(nums, i + 1, currentIndex + 1, currentSum + nums[i], targetSum, partition, picked)) {
          return true;
        }
        picked[i] = false;
      }
    }
    return false;
  }


  public boolean canPartitionKSubsets1(int[] nums, int k) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    if (k <= 0 || sum % k != 0) {
      return false;
    }
    int[] visited = new int[nums.length];
    return canPartition(nums, visited, 0, k, 0, 0, sum / k);
  }

  public boolean canPartition(int[] nums, int[] visited, int start_index, int k, int cur_sum, int cur_num, int target) {
    if (k == 1) {
      return true;
    }
    if (cur_sum == target) {
      return canPartition(nums, visited, 0, k - 1, 0, 0, target);
    }
    for (int i = start_index; i < nums.length; i++) {
      if (visited[i] == 0) {
        visited[i] = 1;
        if (canPartition(nums, visited, i + 1, k, cur_sum + nums[i], cur_num++, target)) {
          return true;
        }
        visited[i] = 0;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    PartitionToKEqualSubsets ss = new PartitionToKEqualSubsets();
    int[] num = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    System.out.println(ss.canPartitionKSubsets(num, 2));
  }
}
