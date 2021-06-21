package leetcode.DP;


public class LC664 {

  public int strangePrinter(String s) {
    int best = Integer.MAX_VALUE;
    for (char ch = 'a'; ch <= 'z'; ch++) {
      best = Math.min(best, 1 + go(ch, 1, s));
    }
    return best;
  }

  private int go(char last, int idx, String str) {
    if (idx >= str.length()) {
      return 0;
    }
    int best = Integer.MAX_VALUE;
    if (last == str.charAt(idx)) {
      best = Math.min(best, go(last, idx + 1, str));
    } else {
      best = Math.min(best, 1 + go(last, idx, str));
      for (char ch = 'a'; ch <= 'z'; ch++) {
        best = Math.min(best, 1 + go(ch, idx + 1, str));
      }
    }

    return best;
  }

  public static void main(String[] args) {
    LC664 l = new LC664();
  }
}



