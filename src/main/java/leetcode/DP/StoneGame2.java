package leetcode.DP;

import com.google.common.base.Stopwatch;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author manoji on 2/17/20.
 */
public class StoneGame2 {

  class Pair {

    int score1;
    int score2;

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("Pair{");
      sb.append("score1=").append(score1);
      sb.append(", score2=").append(score2);
      sb.append('}');
      return sb.toString();
    }

    Pair(int score1, int score2) {
      this.score1 = score1;
      this.score2 = score2;
    }
  }

  public int stoneGameII1(int[] piles) {
    int[] stones = new int[piles.length + 1];
    for (int i = 1; i < stones.length; i++) {
      stones[i] = piles[i - 1];
    }

    return maxScore(piles, 1, 1);
  }

  public int maxScore(int[] piles, int M, int index) {
    if (index >= piles.length) {
      return 0;
    }

    int X = 1;
    for (int i = index; i <= (2 * M - 1); i++) {
      //picking upto i
      int currSum = sum(index, piles, i);
      int nextM = Math.max(X, M);
      for (int j = i + 1; j <= nextM && j < piles.length; j++) {
        maxScore(piles, nextM, j);
      }
    }
    return 1;
  }

  public int stoneGameII(int[] piles) {
    int[] stones = new int[piles.length + 1];
    for (int i = 1; i < stones.length; i++) {
      stones[i] = piles[i - 1];
    }
    HashMap<String, Integer> map = new HashMap<>();
    int[] prefix = new int[piles.length + 1];
    prefix[1] = stones[1];
    for (int i = 2; i < stones.length; i++) {
      prefix[i] = prefix[i - 1] + stones[i];
    }
    Integer pair = stoneGame(prefix, stones, 1, 1, 1, map);
    return pair;
  }

  private Integer stoneGame(int[] prefix, int[] stones, int M, int index, int player, HashMap<String, Integer> map) {
    if (index >= stones.length) {
      return 0;
    }
    String key = M + "|" + index + "|" + player;
    if (map.containsKey(key)) {
      return map.get(key);
    }
    if (player == 1) {
      Integer max = Integer.MIN_VALUE;
      int X = 1;
      for (int i = index; i <= index + (2 * M - 1) && i < stones.length; i++) {
        int currSum = sumRange(prefix, index, i);
        Integer score = currSum + stoneGame(prefix, stones, Math.max(X, M), i + 1, 2, map);
        X++;
        if (score > max) {
          max = score;
        }
      }
      map.put(key, max);
      return max;
    } else {
      Integer min = Integer.MAX_VALUE;
      int X = 1;
      for (int i = index; i <= index + (2 * M - 1) && i < stones.length; i++) {
        Integer score = stoneGame(prefix, stones, Math.max(X, M), i + 1, 1, map);
        X++;
        if (score < min) {
          min = score;
        }
      }
      map.put(key, min);
      return min;
    }
  }


  public int sumRange(int[] prefixArray, int i, int j) {
    if (i > 0) {
      return prefixArray[j] - prefixArray[i - 1];
    }
    return prefixArray[j];
  }


  private int sum(int index, int[] stones, int j) {
    int sum = 0;
    for (int i = index; i <= j; i++) {
      sum += stones[i];
    }
    return sum;
  }


  public static void main(String args[]) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    StoneGame2 stoneGame2 = new StoneGame2();
    int piles[] = new int[]{3111, 4303, 2722, 2183, 6351, 5227, 8964, 7167, 9286, 6626, 2347, 1465, 5201, 7240, 5463, 8523, 8163, 9391, 8616, 5063,
        7837, 7050, 1246, 9579, 7744, 6932, 7704, 9841, 6163, 4829, 7324, 6006, 4689, 8781, 621};
    int val = stoneGame2.stoneGameII(piles);
    System.out.println(val);
    System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
  }
}
