package leetcode.DP;

import java.util.HashMap;

/**
 * @author manoji on 2/25/20.
 */
public class LastStoneWeight2 {

  public int lastStoneWeightII(int[] stones) {
    return lastStoneWeightRecursive(stones, 0, 0, 0, new HashMap<String, Integer>());
  }

  private int lastStoneWeightRecursive(int[] stones, int sum1, int sum2, int index, HashMap<String, Integer> map) {
    if (index >= stones.length) {
      return Math.abs(sum1 - sum2);
    }
    if (map.containsKey(index + "|" + sum1)) {
      return map.get(index + "|" + sum1);
    }

    int inSum1 = lastStoneWeightRecursive(stones, sum1 + stones[index], sum2, index + 1, map);
    int inSum2 = lastStoneWeightRecursive(stones, sum1, sum2 + stones[index], index + 1, map);

    map.put(index + "|" + sum1, Math.min(inSum1, inSum2));

    return Math.min(inSum1, inSum2);
  }


  public static void main(String args[]) {
    LastStoneWeight2 lastStoneWeight2 = new LastStoneWeight2();
    int[] stones = new int[]{1, 2, 1};
    int val = lastStoneWeight2.lastStoneWeightII(stones);
    System.out.println(val);
  }
}
