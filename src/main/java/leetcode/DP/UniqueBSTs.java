package leetcode.DP;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 1/28/20.
 */
public class UniqueBSTs {

  public int numTrees(int n) {
    int[] arr = new int[n];

    for (int i = 0; i < n; i++) {
      arr[i] = i + 1;
    }
    return numTreesRecur(arr, 0, arr.length - 1);
  }

  public List<TreeNode> generateTrees(int n) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = i + 1;
    }
    List<TreeNode> dp[][] = new List[arr.length + 1][arr.length + 1];
    return generateTrees(arr, 0, arr.length - 1, dp);
  }

  private List<TreeNode> generateTrees(int[] arr, int startIndex, int endIndex, List<TreeNode> dp[][]) {
    if (startIndex == endIndex) {
      List<TreeNode> nodes = new ArrayList<>();
      nodes.add(new TreeNode(arr[startIndex]));
      return nodes;
    }
    if (startIndex > endIndex) {
      return null;
    }
    if (dp[startIndex][endIndex] != null) {
      return dp[startIndex][endIndex];
    }
    List<TreeNode> total = new ArrayList();
    for (int i = startIndex; i <= endIndex; i++) {
      List<TreeNode> left = generateTrees(arr, startIndex, i - 1, dp);
      List<TreeNode> right = generateTrees(arr, i + 1, endIndex, dp);
      if (left != null && right == null) {
        for (TreeNode node : left) {
          TreeNode root = new TreeNode(arr[i]);
          root.left = node;
          total.add(root);
        }
      } else if (left == null && right != null) {
        for (TreeNode node : right) {
          TreeNode root = new TreeNode(arr[i]);
          root.right = node;
          total.add(root);
        }
      } else if (left != null && right != null) {
        for (TreeNode node : left) {
          TreeNode root = new TreeNode(arr[i]);
          root.left = node;
          for (TreeNode nodeRight : right) {
            root.right = nodeRight;
            total.add(root);
          }
        }
      }
    }
    dp[startIndex][endIndex] = total;
    return total;
  }

  public int numTreesRecur(int[] arr, int startIndex, int endIndex) {
    if (startIndex > endIndex) {
      return 0;
    }
    if (startIndex == endIndex) {
      return 1;
    }
    int numTreesLeft;
    int numTreesRight;
    int total = 0;
    for (int i = startIndex; i <= endIndex; i++) {
      numTreesLeft = numTreesRecur(arr, startIndex, i - 1);
      numTreesRight = numTreesRecur(arr, i + 1, endIndex);
      if (numTreesLeft == 0 && numTreesRight == 0) {
        total = 0;
      } else if (numTreesLeft == 0 && numTreesRight != 0) {
        total += numTreesRight;
      } else if (numTreesLeft != 0 && numTreesRight == 0) {
        total += numTreesLeft;
      } else {
        total += (numTreesLeft * numTreesRight);
      }
    }
    return total;
  }


  public static void main(String args[]) {
    UniqueBSTs uniqueBSTs = new UniqueBSTs();
    List<TreeNode> root = uniqueBSTs.generateTrees(3);
    for (TreeNode node : root) {
      System.out.println(new Codec().serialize(node));
    }
  }
}
