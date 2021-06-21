package leetcode.DP;


import java.util.HashMap;

public class LC1659 {

  HashMap<String, Integer> dp = new HashMap<>();

  public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < n; i++) {
      buffer.append("0");
    }
    return go(buffer.toString(), introvertsCount, extrovertsCount, 0, m, n);
  }

  private int go(String prev, int iCount, int eCount, int idx, int m, int n) {
    if (idx >= (m * n) || (iCount == 0 && eCount == 0)) {
      return 0;
    }
    String state = prev + ":" + iCount + ":" + eCount + ":" + idx;
    if (dp.containsKey(state)) {
      return dp.get(state);
    }
    //dont fill
    int best = 0, col = idx % n, row = idx / n;
    best = Math.max(best, go(prev.substring(1) + "0", iCount, eCount, idx + 1, m, n));

    if (iCount > 0) {
      int newScore = 120;
      newScore = calScore(prev.charAt(0) - '0', 1, newScore);

      if (col != 0) {
        newScore = calScore(prev.charAt(prev.length() - 1) - '0', 1, newScore);
      }
      best = Math.max(best, newScore + go(prev.substring(1) + "1", iCount - 1, eCount, idx + 1, m, n));
    }
    if (eCount > 0) {
      int newScore = 40;
      newScore = calScore(prev.charAt(0) - '0', 2, newScore);

      if (col != 0) {
        newScore = calScore(prev.charAt(prev.length() - 1) - '0', 2, newScore);
      }
      best = Math.max(best, newScore + go(prev.substring(1) + "2", iCount, eCount - 1, idx + 1, m, n));
    }
    dp.put(state, best);
    return best;
  }

  private int calScore(int p1, int p2, int score) {
    if (p1 == 1 && p2 == 1) {
      return score - 60;
    }
    if (p1 == 2 && p2 == 2) {
      return score + 40;
    } else if (p1 == 1 && p2 == 2) {
      return score - 10;
    } else if (p1 == 2 && p2 == 1) {
      return score - 10;
    }
    return score;
  }

  public static void main(String[] args) {
    LC1659 l = new LC1659();
    System.out.println(l.getMaxGridHappiness(2, 3, 1, 2));
  }
}



