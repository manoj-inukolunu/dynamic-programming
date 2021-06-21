package leetcode.DP;


import java.util.TreeMap;

public class LC975 {

  public int oddEvenJumps(int[] arr) {
    int[] oArr = new int[arr.length];
    int[] eArr = new int[arr.length];
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int i = arr.length - 1; i >= 0; i--) {
      int num = arr[i];
      if (map.containsKey(num)) {
        oArr[i] = map.get(num);
        eArr[i] = map.get(num);
      } else {
        Integer higher = map.higherKey(num);
        if (higher != null) {
          oArr[i] = map.get(higher);
        } else {
          oArr[i] = -1;
        }
        Integer lower = map.lowerKey(num);
        if (lower != null) {
          eArr[i] = map.get(lower);
        } else {
          eArr[i] = -1;
        }
      }
      map.put(num, i);
    }
    int count = 0;
    Boolean[][] dp = new Boolean[arr.length + 1][2];
    for (int i = 0; i < arr.length; i++) {
      if (go(arr, i, 1, oArr, eArr, dp)) {
        count++;
      }
    }
    return count;
  }

  private boolean go(int[] arr, int idx, int odd, int[] oArr, int[] eArr, Boolean[][] dp) {
    if (idx < 0) {
      return false;
    }
    if (idx == arr.length - 1 || idx >= arr.length) {
      return true;
    }
    if (dp[idx][odd] != null) {
      return dp[idx][odd];
    }
    boolean possible;
    if (odd == 1) {
      possible = go(arr, oArr[idx], 0, oArr, eArr, dp);
    } else {
      possible = go(arr, eArr[idx], 1, oArr, eArr, dp);
    }
    dp[idx][odd] = possible;
    return possible;
  }

  public static void main(String[] args) {
    LC975 l = new LC975();
    System.out.println(l.oddEvenJumps(new int[]{5, 1, 3, 4, 2}));
  }
}



