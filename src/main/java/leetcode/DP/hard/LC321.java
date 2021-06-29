package leetcode.DP.hard;


import java.util.Arrays;
import java.util.Stack;

public class LC321 {

  private int[] maxArray(int[] arr, int k) {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      while (!stack.isEmpty() && arr[i] > stack.peek() && (stack.size() + (arr.length - i)) > k) {
        stack.pop();
      }
      stack.push(arr[i]);
    }
    int[] ans = new int[k];
    for (int i = ans.length - 1; i >= 0; i--) {
      ans[i] = stack.pop();
    }
    return ans;
  }

  public int[] maxArrayNonStack(int[] nums, int k) {
    int n = nums.length;
    int[] ans = new int[k];
    for (int i = 0, j = 0; i < n; ++i) {
      while (n - i + j > k && j > 0 && ans[j - 1] < nums[i]) {
        j--;
      }
      if (j < k) {
        ans[j++] = nums[i];
      }
    }
    return ans;
  }

  private int[] merge(int[] arr1, int[] arr2, int k) {
    int[] ans = new int[k];
    for (int i = 0, j = 0, c = 0; c < k; c++) {
      ans[c] = greater(arr1, arr2, i, j) ? arr1[i++] : arr2[j++];
    }
    return ans;
  }

  private boolean greater(int[] arr1, int[] arr2, int i, int j) {
    while (i < arr1.length && j < arr2.length && arr1[i] == arr2[j]) {
      i++;
      j++;
    }
    return j == arr2.length || (i < arr1.length && arr1[i] > arr2[j]);
  }

  public int[] maxNumber(int[] nums1, int[] nums2, int k) {
    int[] ans = new int[k];
    for (int i = Math.max(0, k - nums2.length); i <= k && i <= nums1.length; i++) {
      int[] ret = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
      if (greater(ret, ans, 0, 0)) {
        ans = ret;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    LC321 l = new LC321();
//    int[] arr = l.maxNumber(new int[]{3, 4, 5, 6}, new int[]{9, 1, 2, 5, 8, 3}, 5);
    System.out.println(Arrays.toString(l.maxArray(new int[]{9, 1, 2, 5, 8, 3}, 4)));
  }
}



