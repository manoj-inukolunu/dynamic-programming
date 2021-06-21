package leetcode.DP;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author manoji on 2/2/20.
 */
public class CanIWin {

  private int maxChoosableInteger;

  private String getKey(boolean[] array, int current, int player) {
    String key = Arrays.toString(array);
    return key + "|" + current + "|" + player;
  }

  private int winner(boolean[] array, int desiredTotal, int current, int player, HashMap<String, Integer> map, int numUsed) {
    String key = getKey(array, current, player);
    if (map.containsKey(key)) {
      return map.get(key);
    }
    System.out.println(numUsed);
    if (numUsed >= array.length) {
      return -1;
    }
    if (current >= desiredTotal) {
      return player == 2 ? 1 : 2;
    }
    if (player == 1) {
      for (int i = 1; i <= maxChoosableInteger; i++) {
        if (array[i]) {
          continue;
        }
        array[i] = true;
        int won = winner(array, desiredTotal, current + i, 2, map, ++numUsed);
        //map.put(getKey(array, current + i, 2), won);
        array[i] = false;
        --numUsed;
        if (won == 1) {
          return 1;
        }
      }
      return 2;
    } else {
      for (int i = 1; i <= maxChoosableInteger; i++) {
        if (array[i]) {
          continue;
        }
        array[i] = true;
        int won = winner(array, desiredTotal, current + i, 1, map, ++numUsed);
        //map.put(getKey(array, current + i, 1), won);
        array[i] = false;
        --numUsed;
        if (won == 2) {
          return 2;
        }
      }
      return 1;
    }
  }


  public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    this.maxChoosableInteger = maxChoosableInteger;
    if (maxChoosableInteger >= desiredTotal) {
      return true;
    }
    HashMap<String, Integer> map = new HashMap<>();
    boolean array[] = new boolean[maxChoosableInteger + 2];
    int whoWon = winner(array, desiredTotal, 0, 1, map, 0);
    if (whoWon == 1) {
      return true;
    }
    return false;
  }

  public static void main(String args[]) {
    CanIWin canIWin = new CanIWin();
    System.out.println(canIWin.canIWin(5, 50));
  }
}
