package leetcode.DP;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author manoji on 3/8/20.
 */
public class OutOfBoundaryPaths {

  public List<String> subdomainVisits(String[] cpdomains) {
    HashMap<String, Integer> map = new HashMap();
    for (int j = 0; j < cpdomains.length; j++) {
      String val = cpdomains[j];
      String[] splits = val.split(" ");
      int currSum = Integer.parseInt(splits[0]);
      String d = splits[1];
      if (map.containsKey(d)) {
        map.put(d, map.get(d) + currSum);
      } else {
        map.put(d, currSum);
      }
//			map.put(d, currSum);
      for (int i = d.length() - 1; i >= 0; i--) {
        if (d.charAt(i) == '.') {
          String subDomain = d.substring(i + 1, d.length());
          if (map.containsKey(subDomain)) {
            map.put(subDomain, map.get(subDomain) + currSum);
          } else {
            map.put(subDomain, currSum);
          }
        }
      }
    }

    List<String> list = new ArrayList();
    for (String key : map.keySet()) {
      list.add(map.get(key) + " " + key);
    }
    return list;
  }

  public int findPaths(int m, int n, int N, int i, int j) {
    BigInteger dp[][][] = new BigInteger[m + 1][n + 1][N + 1];
    BigInteger val = findPathsRecur(m, n, N, i, j, dp);
    return val.mod(BigInteger.valueOf((long) (Math.pow(10, 9) + 7))).intValue();

  }

  public BigInteger findPathsRecur(int m, int n, int N, int i, int j, BigInteger[][][] dp) {
    if (N < 0) {
      return BigInteger.valueOf(0);
    }
    if (i < 0 || i >= m || j < 0 || j >= n) {
      return BigInteger.valueOf(1L);
    }

    if (dp[i][j][N] != null) {
      return dp[i][j][N];
    }

    BigInteger up = findPathsRecur(m, n, N - 1, i - 1, j, dp);
    BigInteger down = findPathsRecur(m, n, N - 1, i + 1, j, dp);
    BigInteger left = findPathsRecur(m, n, N - 1, i, j - 1, dp);
    BigInteger right = findPathsRecur(m, n, N - 1, i, j + 1, dp);
    dp[i][j][N] = up.add(down).add(left).add(right);

    return dp[i][j][N];

  }

  public static void main(String args[]) {
    OutOfBoundaryPaths outOfBoundaryPaths = new OutOfBoundaryPaths();
    System.out.println(outOfBoundaryPaths.subdomainVisits(new String[]{"654 yaw.lmm.ca", "1225 lmm.ca"}));
  }

}
