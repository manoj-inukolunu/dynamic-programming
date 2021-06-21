package leetcode.DP;

import java.util.HashMap;

/**
 * @author manoji on 2/9/20.
 */
public class PredictWinner {

  public boolean PredictTheWinner(int[] nums) {
    HashMap<String, Integer> map = new HashMap<>();
    if (predict(nums, 0, nums.length - 1, 0, 0, 1, map) == 1) {
      return true;
    }
    return false;
  }

  private int predict(int[] nums, int start, int end, int player1Score, int player2Score, int player, HashMap<String, Integer> map) {
    if (map.containsKey(start + "|" + end + "|" + player1Score + "|" + player2Score + "|" + player)) {
      return map.get(start + "|" + end + "|" + player1Score + "|" + player2Score + "|" + player);
    }
    if (terminalState(start, end)) {
      return player1Score >= player2Score ? 1 : 2;
    }
    if (player == 1) {
      int startScore = predict(nums, start + 1, end, player1Score + nums[start], player2Score, 2, map);
      map.put((start + 1) + "|" + end + "|" + (player1Score + nums[start]) + "|" + player2Score + "|" + 1, startScore);
      int endScore = predict(nums, start, end - 1, player1Score + nums[end], player2Score, 2, map);
      map.put(start + "|" + (end - 1) + "|" + (player1Score + nums[end]) + "|" + player2Score + "|" + 1, endScore);
      if (startScore == 1 || endScore == 1) {
        return 1;
      }
      return 2;
    } else {
      int startScore = predict(nums, start + 1, end, player1Score, player2Score + nums[start], 1, map);
      map.put((start + 1) + "|" + end + "|" + player1Score + "|" + (player2Score + nums[start]) + "|" + 1, startScore);
      int endScore = predict(nums, start, end - 1, player1Score, player2Score + nums[end], 1, map);
      map.put((start) + "|" + (end - 1) + "|" + player1Score + "|" + (player2Score + nums[end]) + "|" + 1, endScore);
      if (startScore == 2 || endScore == 2) {
        return 2;
      }
      return 1;
    }
  }


  private boolean terminalState(int start, int end) {
    return start > end;
  }


  public static void main(String args[]) {
    PredictWinner predictWinner = new PredictWinner();
    int[] nums = new int[]{1, 5, 233, 7};

    boolean val = predictWinner.PredictTheWinner(nums);
    System.out.println(val);
  }
}
