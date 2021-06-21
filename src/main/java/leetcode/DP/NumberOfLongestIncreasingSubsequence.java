package leetcode.DP;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author manoji on 2/11/20.
 */
public class NumberOfLongestIncreasingSubsequence {

  public int findNumberOfLIS(int[] nums) {
    return 1;
  }

  private void list(int nums[]) {
    int[] lis = new int[nums.length];
    Arrays.fill(lis, 1);
    TreeMap<Integer, Integer>[] count = new TreeMap[nums.length];
    int[] counts = new int[nums.length];
    Arrays.fill(counts, 1);
    for (int i = 0; i < count.length; i++) {
      TreeMap<Integer, Integer> hashMap = new TreeMap<>();
      hashMap.put(1, 1);
      count[i] = hashMap;
    }
    int maxLength = 1;
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          lis[i] = Math.max(lis[j] + 1, lis[i]);
          TreeMap<Integer, Integer> jMap = count[j];
          int lastKey = jMap.lastKey();
          TreeMap<Integer, Integer> iMap = count[i];
          if (iMap.containsKey(lastKey + 1)) {
            iMap.put(lastKey + 1, iMap.get(lastKey + 1) + jMap.get(lastKey));
          } else {
            iMap.put(lastKey + 1, jMap.get(lastKey));
          }
          count[i] = iMap;
          if (lis[i] > maxLength) {
            maxLength = lis[i];
          }
        }
      }
    }
    int num = 0;
    for (int i = 0; i < count.length; i++) {
//			System.out.println(count[i]);
      if (count[i].containsKey(maxLength)) {
        num += count[i].get(maxLength);
      }
    }

    System.out.println(num);
    System.out.println(maxLength);
  }

  public static void main(String args[]) {
    NumberOfLongestIncreasingSubsequence ls = new NumberOfLongestIncreasingSubsequence();

    int[] arr = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
    ls.list(arr);
  }

}
