package leetcode.DP;

import java.util.HashMap;

public class FourKeysKeyboard {

  public int maxA(int N) {
    HashMap<String, Integer> map = new HashMap();
    return maxA(N, 0, 0, 0, map);
    //return max;
  }

  public int maxA(int total, int curr, int sel, int buff, HashMap<String, Integer> map) {
    if (curr >= total) {
      return 0;
    }

    String key = curr + "|" + sel + "|" + buff;
    if (map.containsKey(key)) {
      // System.out.println("Found");
      return map.get(key);
    }
    int key1 = 1 + maxA(total, curr + 1, sel, buff, map);
    int key2 = maxA(total, curr + 1, key1, buff, map);
    int key3 = maxA(total, curr + 1, sel, sel, map);
    int key4 = buff + maxA(total, curr + 1, sel, buff, map);

    map.put(key, Math.max(Math.max(key1, key2), Math.max(key3, key4)));
    return Math.max(Math.max(key1, key2), Math.max(key3, key4));
  }

  public static void main(String args[]) {
    FourKeysKeyboard f = new FourKeysKeyboard();
    System.out.println(f.maxA(30));
  }

}
