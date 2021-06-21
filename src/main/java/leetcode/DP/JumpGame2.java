package leetcode.DP;

/**
 * @author manoji on 3/1/20.
 */
public class JumpGame2 {


  int minJmpsFinal = Integer.MAX_VALUE;

  public boolean canJump(int[] nums) {
    Boolean[][] memo = new Boolean[nums.length][nums.length];
    canJumpRecur(nums, 0, memo, 0);
    return false;
  }

  private void canJumpRecur(int[] nums, int index, Boolean[][] memo, int num) {
    if (memo[index][num] == null) {
      if (index == nums.length - 1) {
        if (num < minJmpsFinal) {
          minJmpsFinal = num;
        }
        return;
      }
      if (index > nums.length) {
        return;
      }
      for (int i = 1; i <= nums[index]; i++) {
        canJumpRecur(nums, i + index, memo, num + 1);
      }
      memo[index][num] = true;
    }
  }

  public static void main(String args[]) {
    JumpGame2 jumpGame2 = new JumpGame2();

    System.out.println(jumpGame2.canJump(new int[]{1, 2, 1, 1, 1}));
  }
}
