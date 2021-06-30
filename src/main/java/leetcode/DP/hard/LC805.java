package leetcode.DP.hard;


import java.util.Arrays;

public class LC805 {

    public boolean splitArraySameAverage(int[] nums) {
        Boolean[][][] dp = new Boolean[nums.length][nums.length / 2][100000];
        return go(nums, 0, 0, 0, Arrays.stream(nums).sum(), dp);
    }

    public boolean go(int[] arr, int idx, int sum, int len, int total, Boolean[][][] dp) {
        if (idx >= arr.length || len >= arr.length / 2) {
            return (sum * (arr.length - len) == (total - sum) * len) && len > 0 && (arr.length - len > 0);
        }
        if (dp[idx][len][sum] != null) {
            return dp[idx][len][sum];
        }
        boolean incl = go(arr, idx + 1, sum + arr[idx], len + 1, total, dp);
        boolean excl = go(arr, idx + 1, sum, len, total, dp);

        dp[idx][len][sum] = incl || excl;
        return incl || excl;
    }

    public static void main(String args[]) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] arr2 = new int[]{3, 1};
        int[] arr3 = new int[]{3863, 703, 1799, 327, 3682, 4330, 3388, 6187, 5330, 6572, 938, 6842, 678, 9837, 8256, 6886, 2204, 5262, 6643, 829, 745, 8755, 3549, 6627, 1633, 4290, 7};
        int[] arr4 = new int[]{5000, 5001, 5002, 5003, 5004, 5005, 5006, 5007, 5008, 5009, 5010, 5011, 5012, 5013, 5114, 5115, 5116, 5117, 5118, 5119, 5120, 5121, 5122, 5123, 5124, 5125, 5126, 5127, 6128, 7724};
        LC805 l = new LC805();
        System.out.println(l.splitArraySameAverage(arr3));
    }
}
