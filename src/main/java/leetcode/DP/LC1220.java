package leetcode.DP;


import java.util.HashMap;

public class LC1220 {


  public int countVowelPermutation(int n) {
    long[][] dp = new long[n + 1][5];
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < "aeiou".length(); i++) {
      map.put("aeiou".charAt(i), i);
    }
    for (char ch : "aeiou".toCharArray()) {
      dp[0][map.get(ch)] = 1;
    }
    int mod = (int) (Math.pow(10, 9) + 7);
    for (int i = 1; i < n; i++) {
      dp[i][map.get('a')] = (dp[i - 1][map.get('e')] + dp[i - 1][map.get('i')] + dp[i - 1][map.get('u')]) % mod;
      dp[i][map.get('e')] = (dp[i - 1][map.get('i')] + dp[i - 1][map.get('a')]) % mod;
      dp[i][map.get('i')] = (dp[i - 1][map.get('e')] + dp[i - 1][map.get('o')]) % mod;
      dp[i][map.get('o')] = dp[i - 1][map.get('i')] % mod;
      dp[i][map.get('u')] = (dp[i - 1][map.get('o')] + dp[i - 1][map.get('i')]) % mod;
    }
    long ans = 0;
    for (char ch : "aeiou".toCharArray()) {
      ans = (ans % mod + dp[n - 1][map.get(ch)] % mod) % mod;
    }
    return (int) (ans % mod);
  }

  public static void main(String[] args) {
    LC1220 l = new LC1220();
    System.out.println(l.countVowelPermutation(50));
  }
}



