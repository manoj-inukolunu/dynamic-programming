package leetcode.DP.hard;


import java.util.ArrayList;
import java.util.List;

public class LC1569 {

  public long numOfWays(int[] nums) {
    List<Integer> less = new ArrayList<>();
    List<Integer> more = new ArrayList<>();
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] < nums[0]) {
        less.add(nums[i]);
      } else {
        more.add(nums[i]);
      }
    }
    int[] l = less.stream().mapToInt(i -> i).toArray();
    int[] m = more.stream().mapToInt(i -> i).toArray();
    collector.add(nums[0]);
    return dfs(l, m, 0, 0) - 1;
  }

  List<Integer> collector = new ArrayList<>();

  private long dfs(int[] arr1, int[] arr2, int i, int j) {
    if (i >= arr1.length || j >= arr2.length) {
      if (i < arr1.length) {
        for (int k = i; k < arr1.length; k++) {
          collector.add(arr1[k]);
        }
        System.out.println(collector);
        for (int k = i; k < arr1.length; k++) {
          collector.remove(new Integer(arr1[k]));
        }
      } else if (j < arr2.length) {
        for (int k = j; k < arr2.length; k++) {
          collector.add(arr2[k]);
        }
        System.out.println(collector);
        for (int k = j; k < arr2.length; k++) {
          collector.remove(new Integer(arr2[k]));
        }
      } else {
        System.out.println(collector);
      }
      return 1;
    }
    collector.add(arr1[i]);
    dfs(arr1, arr2, i + 1, j);
    collector.remove(new Integer(arr1[i]));
    collector.add(arr2[j]);
    dfs(arr1, arr2, i, j + 1);
    collector.remove(new Integer(arr2[j]));

    return 1;
  }

  public static void main(String[] args) {
    LC1569 l = new LC1569();
    System.out.println(l.numOfWays(new int[]{3, 1, 2, 5, 4, 6}));
  }
}



