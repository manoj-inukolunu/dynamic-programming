package leetcode.DP;

/**
 * @author manoji on 3/7/20.
 */
public class LongestFibonacciSequence {

  public int lenLongestFibSubseq(int[] A) {
    int[] dp = new int[A.length];
    int[] pre = new int[A.length];
    int max = 0;
    for (int i = 3; i < A.length; i++) {
      for (int j = 0; j < i; j++) {
        for (int k = 0; k < j; k++) {
          if (A[i] == A[j] + A[k]) {
            dp[i] = Math.max(dp[i], 1 + dp[j]);
            if (dp[i] > max) {
              max = dp[i];
            }
          }
        }
      }
    }
    return max;
  }

  public static void main(String args[]) {
    LongestFibonacciSequence lf = new LongestFibonacciSequence();

    System.out.println(lf.lenLongestFibSubseq(new int[]{1, 3, 7, 11, 12, 14, 18}));
  }
}
