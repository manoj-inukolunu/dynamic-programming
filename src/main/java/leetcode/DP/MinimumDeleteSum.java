package leetcode.DP;

/**
 * @author manoji on 1/24/20.
 */
public class MinimumDeleteSum {

  public int minimumDeleteSum(String s1, String s2) {
    Integer dp[][] = new Integer[s1.length() + 1][s2.length() + 1];
    return minDelSum(s1, s2, 0, 0, dp);
  }

  private int minDelSum(String s1, String s2, int indexS1, int indexS2, Integer[][] dp) {
    if (indexS1 >= s1.length() || indexS2 >= s2.length()) {
      if (indexS1 >= s1.length() && indexS2 < s2.length()) {
        int sum = 0;
        for (int i = indexS2; i < s2.length(); i++) {
          sum += Character.valueOf(s2.charAt(i));
        }
        return sum;
      }
      if (indexS2 >= s2.length() && indexS1 < s1.length()) {
        int sum = 0;
        for (int i = indexS1; i < s1.length(); i++) {
          sum += Character.valueOf(s1.charAt(i));
        }
        return sum;
      }
      return 0;
    }
    if (dp[indexS1][indexS2] != null) {
      return dp[indexS1][indexS2];
    }
    if (s1.charAt(indexS1) == s2.charAt(indexS2)) {
      return minDelSum(s1, s2, indexS1 + 1, indexS2 + 1, dp);
    }
    int delS1 = (int) Character.valueOf(s1.charAt(indexS1)) + minDelSum(s1, s2, indexS1 + 1, indexS2, dp);
    int delS2 = (int) Character.valueOf(s2.charAt(indexS2)) + minDelSum(s1, s2, indexS1, indexS2 + 1, dp);
    dp[indexS1][indexS2] = Math.min(delS1, delS2);
    return Math.min(delS1, delS2);
  }

  public static void main(String args[]) {
    MinimumDeleteSum minimumDeleteSum = new MinimumDeleteSum();
    System.out.println(minimumDeleteSum.minimumDeleteSum("delete", "leet"));
  }

}
