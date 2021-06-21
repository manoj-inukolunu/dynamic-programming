package leetcode.DP;

/**
 * @author manoji on 2/26/20.
 */
public class MaximumLengthOfRepeatedSubArray {

  public int findLength(int[] A, int[] B) {

    int maxLength = Math.min(A.length, B.length);
    Integer[][][] dp = new Integer[A.length][B.length][maxLength];
    return findLengthRecursive(A, B, 0, 0, 0, dp);
  }

  private int findLengthRecursive(int[] arr1, int[] arr2, int index1, int index2, int count, Integer[][][] dp) {
    if (index1 == arr1.length || index2 == arr2.length) {
      return count;
    }

    if (dp[index1][index2][count] == null) {
      if (arr1[index1] == arr2[index2]) {
        count = findLengthRecursive(arr1, arr2, index1 + 1, index2 + 1, count + 1, dp);
      }

      int count2 = findLengthRecursive(arr1, arr2, index1 + 1, index2, 0, dp);
      int count3 = findLengthRecursive(arr1, arr2, index1, index2 + 1, 0, dp);
      dp[index1][index2][count] = Math.max(count, Math.max(count2, count3));
      return Math.max(count, Math.max(count2, count3));
    }
    return dp[index1][index2][count];
  }

  public static void main(String args[]) {
    MaximumLengthOfRepeatedSubArray maximumLengthOfRepeatedSubArray = new MaximumLengthOfRepeatedSubArray();

    int val = maximumLengthOfRepeatedSubArray.findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7});

    System.out.println(val);
  }
}
