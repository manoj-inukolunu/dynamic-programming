package leetcode.DP;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author manoji on 2/26/20.
 */
public class LongestStringChain {

  public int longestStrChain(String[] words) {
    int max = Integer.MIN_VALUE;
    int[] dp = new int[words.length];
    Arrays.fill(dp, 1);

    Arrays.sort(words, Comparator.comparing(s -> s.length()));

    for (int i = 0; i < words.length; i++) {
      for (int j = 0; j < i; j++) {
        if (isPredecessor(words[i], words[j])) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    for (int i = 0; i < dp.length; i++) {
      if (dp[i] > max) {
        max = dp[i];
      }
    }
    return max;
  }

  private boolean isPredecessor(String word1, String word2) {
    if (Math.abs(word1.length() - word2.length()) == 1) {
      int count = 0;
      for (int i = 0; i < word2.length(); i++) {
        if (!word1.contains(word2.charAt(i) + "")) {
          count++;
        }
      }
      return count == 0;
    }
    return false;
  }

  public static void main(String args[]) {
    LongestStringChain longestStringChain = new LongestStringChain();
    int val = longestStringChain.longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"});
    System.out.println(val);
  }

}
