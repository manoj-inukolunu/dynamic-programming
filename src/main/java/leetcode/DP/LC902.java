package leetcode.DP;


public class LC902 {

  public int atMostNGivenDigitSet(String[] digits, int n) {
    String str = n + "";
    int count = 0;
    int totalDigits = str.length();
    for (int i = 1; i <= totalDigits - 1; i++) {
      count += (Math.pow(digits.length, i));
    }
    for (int i = 0; i < digits.length; i++) {
      if (Character.getNumericValue(str.charAt(0)) > Integer.parseInt(digits[i])) {
        count += go(str, 1, 0, digits);
      } else if (Character.getNumericValue(str.charAt(0)) == Integer.parseInt(digits[i])) {
        count += go(str, 1, 1, digits);
      }
    }
    return count;
  }

  private int go(String str, int idx, int isSmaller, String[] digits) {
    if (idx >= str.length()) {
      return 1;
    }
    int total = 0;
    if (isSmaller == 0) {
      total += (digits.length * go(str, idx + 1, 0, digits));
    } else {
      int maxDigit = Character.getNumericValue(str.charAt(idx));
      for (String s : digits) {
        int digit = Integer.parseInt(s);
        if (digit < maxDigit) {
          total += go(str, idx + 1, 0, digits);
        } else if (digit == maxDigit) {
          total += go(str, idx + 1, 1, digits);
        }
      }
    }
    return total;
  }

  public static void main(String[] args) {
    LC902 l = new LC902();
    String[] digits = new String[]{"1", "2", "3", "4", "6", "7", "9"};
    System.out.println(l.atMostNGivenDigitSet(digits, 333));
  }
}



