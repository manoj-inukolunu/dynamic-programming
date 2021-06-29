package leetcode.DP.hard;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class LC940 {


  public int distinctSubseqII(String str) {
    int MAX_CHAR = 256;
    int mod = (int) (Math.pow(10, 9) + 7);
    int[] last = new int[MAX_CHAR];
    Arrays.fill(last, -1);
    int n = str.length();
    BigInteger[] dp = new BigInteger[n + 1];
    dp[0] = BigInteger.ONE;
    for (int i = 1; i <= n; i++) {
      dp[i] = dp[i - 1].multiply(BigInteger.valueOf(2));
      if (last[str.charAt(i - 1)] != -1) {
        dp[i] = dp[i].subtract(dp[last[str.charAt(i - 1)]]);
      }
      last[str.charAt(i - 1)] = (i - 1);
    }
    return dp[n].subtract(BigInteger.ONE).mod(BigInteger.valueOf(mod)).intValue();
  }

  private int go(String str, HashMap<Character, TreeSet<Integer>> map, int idx) {
    if (idx < 0) {
      return 1;
    }
    char ch = str.charAt(idx);
    TreeSet<Integer> prev = map.get(ch);
    Integer lower = prev.lower(idx);
    if (lower != null) {
      return 2 * go(str, map, idx - 1) - go(str, map, lower);
    } else {
      return 2 * go(str, map, idx - 1);
    }
  }

  public static void main(String[] args) {
    LC940 l = new LC940();
    System.out.println(l.distinctSubseqII("aaa"));
  }
}



