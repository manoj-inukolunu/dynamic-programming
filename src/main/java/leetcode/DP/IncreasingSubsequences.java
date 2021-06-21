package leetcode.DP;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 5/11/20.
 */
public class IncreasingSubsequences {


  public List<List<Integer>> findSubsequences(int[] nums) {

    List<List<Integer>> list[] = new List[nums.length];

    for (int i = 0; i < nums.length; i++) {
      List<Integer> curr = new ArrayList<>();
      curr.add(nums[i]);
      List<List<Integer>> l = new ArrayList<>();
      l.add(curr);
      list[i] = l;
    }

    List<List<Integer>> ans = new ArrayList();
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] >= nums[j]) {
          List<List<Integer>> ls = list[j];
          int size = ls.size();
          for (int k = 0; k < size; k++) {
            List<Integer> a = new ArrayList(ls.get(k));
            a.add(nums[i]);
            if (a.size() > 1 && !ans.contains(a)) {
              ans.add(a);
            }
            list[i].add(a);
          }
        }
      }
    }
    return ans;
  }


  public static void main(String args[]) {
    IncreasingSubsequences i = new IncreasingSubsequences();
    int[] arr = new int[]{4, 6, 7, 7};
    System.out.println(i.findSubsequences(arr));
  }

}
