package leetcode.DP;


import java.util.HashMap;

/**
 * @author manoji on 3/19/20.
 */
public class MinCostTree {

  private void inorder(TreeNode root) {
    if (root == null) {
      return;
    }

    inorder(root.left);
    System.out.print(root.val + ",");
    inorder(root.right);

  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    HashMap<Integer, Integer> map = new HashMap();
    for (int i = 0; i < inorder.length; i++) {
      map.put(inorder[i], i);
    }
    return build(preorder, inorder, 0, map);
  }

  //3, 9, 20, 15, 7
  //9, 3, 15, 20, 7
  private TreeNode build(int[] preorder, int[] inorder, int current, HashMap<Integer, Integer> map) {
    if (current >= preorder.length || current < 0) {
      return null;
    }
    TreeNode root = new TreeNode(preorder[current]);
    int index = map.get(preorder[current]);
    root.left = build(preorder, inorder, current + 1, map);
    root.right = build(preorder, inorder, current + (index) + 1, map);
    return root;
  }

  public int mctFromLeafValues(int[] arr) {
    return mctR(arr, 0, arr.length, 0);
  }

  private int mctR(int[] arr, int begin, int end, int sum) {
    if (begin >= end) {
      return 0;
    }
    return 1;
  }

  public static void main(String args[]) {
    MinCostTree mct = new MinCostTree();

    TreeNode node = mct.buildTree(new int[]{1, 2, 3}, new int[]{2, 3, 1});

    System.out.println(new Codec().serialize(node));
  }

}
