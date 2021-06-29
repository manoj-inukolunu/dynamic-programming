package leetcode.DP.hard;


public class LC1449 {


  public String largestNumber(int[] cost, int target) {
    String[] dp = new String[target + 1];
    for (int i = 0; i < cost.length; i++) {
      if (cost[i] <= target) {
        dp[cost[i]] = (i + 1) + "";
      }
    }
    for (int i = 1; i <= target; i++) {
      String currMax = null;
      for (int j = 0; j < cost.length; j++) {
        int prevTarget = i - cost[j];
        if (prevTarget >= 0 && dp[prevTarget] != null) {
          String toCheck = (j + 1) + dp[prevTarget];
          String toCheck1 = dp[prevTarget] + (j + 1);
          currMax = getMax(currMax, getMax(dp[i], getMax(toCheck, toCheck1)));
        }
      }
      if (currMax != null) {
        dp[i] = currMax;
      }
    }
    return dp[target] == null ? "0" : dp[target];
  }

  private String getMax(String a, String b) {
    if (a != null && b != null) {
      if (a.length() == b.length()) {
        return a.compareTo(b) > 0 ? a : b;
      } else {
        return a.length() > b.length() ? a : b;
      }
    }
    return a != null ? a : b;
  }

  public static void main(String[] args) {
    LC1449 l = new LC1449();
    String ans = l.largestNumber(new int[]{6, 10, 15, 40, 40, 40, 40, 40, 40}, 47);
    System.out.println(ans);

  }
}



