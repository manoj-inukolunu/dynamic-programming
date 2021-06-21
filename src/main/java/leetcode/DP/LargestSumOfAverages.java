package leetcode.DP;

import java.util.HashMap;

/**
 * @author manoji on 2/7/20.
 */
public class LargestSumOfAverages {

  public double largestSumOfAverages(int[] A, int K) {
    HashMap<String, Double> map = new HashMap<>();
    return largestSumOfAveragesRecur(A, 0, A.length - 1, K, map);
  }

  private double largestSumOfAveragesRecur(int[] A, int index, int endIndex, int k, HashMap<String, Double> map) {
    if (index == endIndex) {
      return A[index];
    }
    if (index > endIndex) {
      return 0;
    }
    String key = index + "|" + endIndex + "|" + k;
    if (map.containsKey(key)) {
//			return map.get(key);
    }
    if (k == 1) {
      int val = 0;
      for (int i = index; i <= endIndex; i++) {
        val += A[i];
      }
      return val / (endIndex - index + 1.0d);
    }
    double max = Double.MIN_VALUE;
    for (int i = index; i <= endIndex; i++) {
      double val = getSum(A, index, i) + largestSumOfAveragesRecur(A, i + 1, endIndex, k - 1, map);
      if (Double.compare(val, max) == 1) {
        max = val;
      }
    }
    map.put(key, max);
    return max;
  }


  private double getSum(int[] a, int i, int endIndex) {
    int v = 0;
    for (int j = i; j <= endIndex; j++) {
      v += a[j];
    }
    return v / (endIndex - i + 1.0d);
  }

  public static void main(String args[]) {
    int[] arr = new int[]{9, 1, 2, 3, 9};

    LargestSumOfAverages largestSumOfAverages = new LargestSumOfAverages();
    System.out.println(largestSumOfAverages.largestSumOfAverages(arr, 3));
  }


}
