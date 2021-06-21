package leetcode.DP;

/**
 * @author manoji on 2/21/20.
 */
public class GuessNumberHigherOrLower {

  public int getMoneyAmount(int n) {
    return getMoneyAmountRecursive(1, n);
  }

  private int getMoneyAmountRecursive(int startNumber, int endNumber) {
    if (startNumber > endNumber) {
      return 0;
    }
    if (startNumber == endNumber) {
      return startNumber;
    }

    int total = 0;
    for (int i = startNumber; i <= endNumber; i++) {
      total = getMoneyAmountRecursive(startNumber, i - 1) + getMoneyAmountRecursive(i + 1, endNumber) + i;
    }
    return total;
  }

  public static void main(String args[]) {
    GuessNumberHigherOrLower guessNumberHigherOrLower = new GuessNumberHigherOrLower();

    int val = guessNumberHigherOrLower.getMoneyAmount(10);

    System.out.println(val);

  }

}
