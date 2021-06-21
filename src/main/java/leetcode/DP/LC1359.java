package leetcode.DP;


import java.util.Arrays;

public class LC1359 {


  private int dfs(int len, int idx, int pickedUp, int left, int[][][] dp) {
    if (idx >= len) {
      return 1;
    }
    if (dp[idx][pickedUp][left] != -1) {
      return dp[idx][pickedUp][left];
    }
    if (pickedUp > 0 && left > 0) {
      long drop = ((long) pickedUp % mod * dfs(len, idx + 1, pickedUp - 1, left, dp) % mod) % mod;
      long pickUp = ((long) left % mod * dfs(len, idx + 1, pickedUp + 1, left - 1, dp) % mod) % mod;
      dp[idx][pickedUp][left] = (int) ((drop % mod + pickUp % mod) % mod);
      return dp[idx][pickedUp][left];
    } else if (pickedUp > 0) {
      dp[idx][pickedUp][left] = (int) ((long) pickedUp % mod * dfs(len, idx + 1, pickedUp - 1, left, dp) % mod) % mod;
      return dp[idx][pickedUp][left];
    } else {
      dp[idx][pickedUp][left] = (int) ((long) left % mod * dfs(len, idx + 1, pickedUp + 1, left - 1, dp) % mod) % mod;
      return dp[idx][pickedUp][left];
    }
  }


  int mod = (int) Math.pow(10, 9) + 7;

  public int countOrders(int n) {
    int[][] dp = new int[n + 1][n + 1];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }

    return dfs(0, n, dp);
  }

  private int fact(int num) {
    long res = 1;
    for (int i = 1; i <= num; i++) {
      res = (res % mod * i % mod) % mod;
    }
    return (int) res % mod;
  }

  private int dfs(int pickedUp, int left, int[][] dp) {
    if (left == 0 && pickedUp == 0) {
      return 1;
    }
    if (dp[pickedUp][left] != -1) {
      return dp[pickedUp][left];
    }
    if (left == 0) {
      dp[pickedUp][left] = fact(pickedUp);
      return dp[pickedUp][left];
    }
    if (pickedUp == 0) {
      dp[pickedUp][left] = (int) ((long) left % mod * dfs(pickedUp + 1, left - 1, dp) % mod) % mod;
      return dp[pickedUp][left];
    }
    int pickUp = (int) ((long) left % mod * dfs(pickedUp + 1, left - 1, dp) % mod) % mod;
    int deliver = (int) ((long) pickedUp % mod * dfs(pickedUp - 1, left, dp) % mod) % mod;

    dp[pickedUp][left] = (pickUp % mod + deliver % mod) % mod;
    return dp[pickedUp][left];

  }


  public static void main(String[] args) {
    LC1359 l = new LC1359();
    System.out.println(l.countOrders(1000));
  }
}



