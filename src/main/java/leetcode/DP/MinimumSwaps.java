package leetcode.DP;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.math.BigInteger;
import java.util.HashMap;

/**
 * @author manoji on 2/1/20.
 */
public class MinimumSwaps {

  static class Solution {

    class Data {

      int leftSum;
      int rightSum;
      TreeNode node;

      public Data(int leftSum, int rightSum, TreeNode node) {
        this.leftSum = leftSum;
        this.rightSum = rightSum;
        this.node = node;
      }

      @Override
      public String toString() {
        final StringBuilder sb = new StringBuilder("Data{");
        sb.append("leftSum=").append(leftSum);
        sb.append(", rightSum=").append(rightSum);
        sb.append(", node=").append(node);
        sb.append('}');
        return sb.toString();
      }
    }

    BigInteger max = BigInteger.ONE;
    HashMap<TreeNode, Data> map = new HashMap();

    long mod = 10000000007l;
    int max1 = Integer.MIN_VALUE;

    public int maxProduct(TreeNode root) {
      dfs(root);
      int total = map.get(root).leftSum + map.get(root).rightSum + root.val;
      ddfs(root, total);
      return max1;
    }

    private void ddfs(TreeNode root, int total) {
      if (root == null) {
        return;
      }
      //delete left edge
      int leftSum = map.get(root).leftSum;
      int rightSum = map.get(root).rightSum;

      int left = (int) (((total - leftSum) % mod * (leftSum % mod)) % mod);
      int right = (int) (((total - rightSum) % mod * (rightSum % mod)) % mod);
      int val = Math.max(left, right);
      if (val > max1) {
        max1 = val;
      }
      ddfs(root.left, total);
      ddfs(root.right, total);
    }

    private int[] dfs(TreeNode root) {
      if (root == null) {
        return new int[]{0, 0};
      }
      int leftSum = 0;
      if (root.left != null) {
        int[] sum = dfs(root.left);
        leftSum = root.left.val + sum[0] + sum[1];
      }
      int rightSum = 0;
      if (root.right != null) {
        int[] sum = dfs(root.right);
        rightSum = root.right.val + sum[0] + sum[1];
      }
      Data data = new Data(leftSum, rightSum, root);
      map.put(root, data);
      return new int[]{leftSum, rightSum};
    }
  }

  public static void main(String args[]) {
    Solution minimumSwaps = new Solution();
    System.out.println(minimumSwaps.maxProduct(new Codec().deserialize("1,null,2,3,4,null,null,5,6")));
  }
}
