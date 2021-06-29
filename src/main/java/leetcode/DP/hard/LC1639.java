package leetcode.DP.hard;


import java.util.Arrays;

public class LC1639 {

    int mod = (int) (Math.pow(10, 9) + 7);

    public int numWays(String[] words, String target) {
        int rows = words[0].length();
        int cols = 26;
        int[][] dp = new int[rows + 1][target.length()];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int[][] map = new int[rows][cols];
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                map[i][idx]++;
            }
        }
        return go(map, 0, 0, target, dp);
    }

    private int go(int[][] words, int idx, int tIdx, String target, int[][] dp) {
        if (tIdx >= target.length()) {
            return 1;
        }
        if (idx >= words.length) {
            return 0;
        }
        if (dp[idx][tIdx] != -1) {
            return dp[idx][tIdx];
        }
        int ch = target.charAt(tIdx) - 'a';
        long total = 0;
        total += go(words, idx + 1, tIdx, target, dp) % mod;
        total += ((long) words[idx][ch] * go(words, idx + 1, tIdx + 1, target, dp)) % mod;
        dp[idx][tIdx] = (int) (total % mod);
        return dp[idx][tIdx];
    }

    public static void main(String[] args) {
        LC1639 l = new LC1639();
        String[] words = new String[]{"acca", "bbbb", "caca"};
        int val = l.numWays(words, "aba");
        String[] words1 = new String[]{"abba", "baab"};
        int val1 = l.numWays(words1, "bab");
        String[] words2 = new String[]{"abcd"};
        int val2 = l.numWays(words2, "abcd");
        String[] words3 = new String[]{"abab", "baba", "abba", "baab"};
        int val3 = l.numWays(words3, "abba");
        System.out.println(val);
        System.out.println(val1);
        System.out.println(val2);
        System.out.println(val3);

    }
}



