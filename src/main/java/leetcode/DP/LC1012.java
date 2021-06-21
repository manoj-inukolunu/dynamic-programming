package leetcode.DP;


import java.util.HashSet;
import java.util.Set;

public class LC1012 {


  public int numDupDigitsAtMostN(int N) {
    return N - noRep(N);
  }

  private int noRep(int n) {
    String str = n + "";
    int digits = str.length();
    int total = 0;
    for (int i = 1; i <= digits - 1; i++) {
      int count = 9;
      int num = 9;
      for (int j = i - 1; j >= 1; j--) {
        count *= (num--);
      }
      total += count;
    }
    int count = 0;
    for (int i = 1; i <= Character.getNumericValue(str.charAt(0)); i++) {
      if (i != Character.getNumericValue(str.charAt(0))) {
        count += go(str, 1, true);
      } else {
        count += go(str, 1, false);
      }
    }
    return total + count;
  }

  private int go(String str, int idx, boolean isSmaller) {
    if (idx >= str.length()) {
      return 1;
    }
    int count = 0;
    if (isSmaller) {
      count += (9 - idx + 1) * go(str, idx + 1, isSmaller);
    } else {
      Set<Integer> used = new HashSet<>();
      for (int i = 0; i < idx; i++) {
        used.add(Character.getNumericValue(str.charAt(i)));
      }
      for (int i = 0; i < Character.getNumericValue(str.charAt(idx)); i++) {
        if (!used.contains(i)) {
          count += go(str, idx + 1, true);
        }
      }
      if (!used.contains(Character.getNumericValue(str.charAt(idx)))) {
        count += go(str, idx + 1, false);
      }
    }
    return count;
  }

  public static void main(String[] args) {
    LC1012 l = new LC1012();
    System.out.println(l.numDupDigitsAtMostN(1234));
  }
}



