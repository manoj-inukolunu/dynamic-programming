package leetcode.DP.hard;

import java.util.Arrays;

public class LC1340 {
    int[] dp;

    public int maxJumps(int[] arr, int d) {
        int ans = 0;
        dp = new int[arr.length];
        Arrays.fill(dp, -1);
        int[][] reachable = new int[arr.length][2];
        for (int[] row : reachable) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < arr.length; i++) {
            int end = i + d;
            for (int j = i + 1; j < arr.length && j <= end; j++) {
                if (arr[j] < arr[i]) {
                    reachable[i][0] = j;
                } else {
                    break;
                }
            }
            end = i - d;
            for (int j = i - 1; j >= 0 && j >= end; j--) {
                if (arr[j] < arr[i]) {
                    reachable[i][1] = j;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            ans = Math.max(ans, go(i, reachable));
        }
        return ans;
    }

    private int go(int idx, int[][] reachable) {
        if (dp[idx] != -1) {
            return dp[idx];
        }
        int start = reachable[idx][1] == -1 ? idx : reachable[idx][1], end = reachable[idx][0] == -1 ? idx : reachable[idx][0];
        int visitedIndicies = 1, val = 0;
        for (int i = start; i <= end && i >= 0; i++) {
            if (i == idx) {
                continue;
            }
            val = Math.max(val, go(i, reachable));
        }
        visitedIndicies += val;
        dp[idx] = Math.max(visitedIndicies, dp[idx]);
        return dp[idx];
    }

    public static void main(String args[]) {
        int[] arr = new int[]{3, 3, 3, 3, 3};
        int d = 3;
        LC1340 l = new LC1340();
        System.out.println(l.maxJumps(arr, d));

    }
}
