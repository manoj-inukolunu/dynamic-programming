package leetcode.DP;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author manoji on 2020-01-03.
 */
public class Solution1 {

  public int kConcatenationMaxSum(int[] arr, int k) {
    int[] arrNew = new int[arr.length * k];
    int l = 0;
    for (int i = 0; i < k; i++) {
      for (int j = 0; j < arr.length; j++) {
        arrNew[l++] = arr[j];
      }
    }
//    System.out.println(Arrays.toString(arrNew));

    int sum = maxSubArraySum(arrNew);
    if (sum < 0) {
      return 0;
    } else {
      return (int) (sum % (Math.pow(10, 9) + 7));
    }
  }

  public int maxSubArraySum(int[] nums) {
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


  public static void main(String arg[]) throws Exception {

    Solution1 solution1 = new Solution1();
    //testContiguousArray(solution1);
    System.out.println(solution1.kConcatenationMaxSum(new int[]{1, -2, 1}, 5));
  }

  private static void testContiguousArray(Solution1 solution1) throws FileNotFoundException {
    int[] arr = new int[]{23, 2, 6, 4, 7};
    int[] arrWithZeroes = new int[]{0, 0};
    int[] arr1 = new int[]{1, 0};

    Scanner scanner = new Scanner(new FileInputStream(new File("/Users/manoji/test.txt")));
    List<Integer> integers = new ArrayList();
    while (scanner.hasNextLine()) {
      String[] line = scanner.nextLine().split(",");
      integers.addAll(Arrays.stream(line).map(str -> Integer.parseInt(str)).collect(Collectors.toList()));
    }
    int[] largeArray = integers.stream().mapToInt(i -> i).toArray();
    int[] tleArray = new int[]{405, 504, 203, 96, 43, 50, 214, 327, 120, 345, 33, 314, 377, 62, 431, 379, 114, 208, 106, 345, 391, 513, 9, 405, 452,
        186, 295, 33, 423, 122, 355, 311, 192, 429, 320, 360, 85, 96, 32, 258, 407, 71, 436, 370, 365, 199, 443, 521, 262, 232, 355, 241, 250, 10,
        258, 324, 335, 446, 474, 385, 74, 101, 111, 162, 349, 149, 51, 399, 371, 139, 199, 264, 118, 155, 134, 518, 388, 113, 520, 441, 384, 449, 14,
        104, 267, 92, 477, 50, 505, 368, 466, 519, 105, 443, 31, 21, 485, 146, 115, 94};
    long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    System.out.println(solution1.checkSubarraySum(tleArray, 337));
    long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    long actualMemUsed = afterUsedMem - beforeUsedMem;
    System.out.println(actualMemUsed);
  }

  public boolean checkSubarraySum(int[] nums, int k) {
//    Boolean[][] dp = new Boolean[nums.length + 1][nums.length + 1];
    return csri(nums, k);
  }

  public boolean csri(int[] nums, int k) {
    for (int i = 0; i < nums.length - 1; i++) {
      int sum = nums[i];
      for (int j = i + 1; j < nums.length; j++) {
        sum += nums[j];
        if ((k == 0 && sum == 0) || (k != 0 && sum % k == 0)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean cssr(int[] nums, int startIndex, int currentIndex, int sum, int k) {
    if (k == 0 && sum == 0 && (currentIndex - startIndex) >= 2) {
      return true;
    }
    if (k != 0 && sum % k == 0 && (currentIndex - startIndex) >= 2) {
      return true;
    }
//    if (dp[startIndex][currentIndex] == null) {
    if (currentIndex >= nums.length) {
      return false;
    }
    return cssr(nums, startIndex, currentIndex + 1, sum + nums[currentIndex], k) || cssr(nums, currentIndex, currentIndex + 1, nums[currentIndex], k);
      /*if (val1) {
        dp[startIndex][currentIndex] = val1;
      } else {
        boolean val2 =;
        dp[startIndex][currentIndex] = val1 || val2;
      }
    }
    return dp[startIndex][currentIndex];*/
  }
}
