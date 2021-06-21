package leetcode.DP;

/**
 * @author manoji on 2019-12-28.
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }
 */
public class Codec {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if (root == null) {
      return "";
    }
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    StringBuilder sb = new StringBuilder();
    // use tree level order traversal to serialize the tree
    while (!q.isEmpty()) {
      TreeNode n = q.poll();
      if (n == null) {
        sb.append("null,");
      } else {
        sb.append(n.val + ",");
        q.add(n.left);
        q.add(n.right);
      }
//      System.out.println(sb.toString());
    }
    return sb.toString().trim();
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data.equals("")) {
      return null;
    }
    String[] vals = data.split(",");
    if (vals.length == 0) {
      return null;
    }
    Queue<TreeNode> q = new LinkedList<>();
    TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
    q.add(root);
    TreeNode p = null;
    String val;
    for (int i = 1; i < vals.length; ) {
      p = q.poll();
      val = vals[i++];
      if (val.equals("null")) {
        p.left = null;
      } else {
        p.left = new TreeNode(Integer.valueOf(val));
        q.add(p.left);
      }
      if (i < vals.length) {
        val = vals[i++];
        if (val.equals("null")) {
          p.right = null;
        } else {
          p.right = new TreeNode(Integer.valueOf(val));
          q.add(p.right);
        }
      }
    }
    return root;
  }

  public List<List<Integer>> genListFromArr(String arr) {

    Stack<Character> stack = new Stack<>();
    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < arr.length(); i++) {
      if (arr.charAt(i) == ']') {
        while (!stack.isEmpty() && stack.peek() != '[') {
          StringBuffer buffer = new StringBuffer();
          Character ch = stack.pop();
          while (ch != ',') {
            buffer.append(ch);
            ch = stack.pop();
            if (ch == '[') {
              break;
            }
          }
          if (buffer.length() != 0) {
            temp.add(Integer.parseInt(buffer.toString()));
          }
        }
        res.add(new ArrayList(temp));
        temp.clear();
      } else {
        stack.push(arr.charAt(i));
      }
    }

    return res;

  }

  public static void main(String args[]) {
    Codec codec = new Codec();

    TreeNode node = codec.deserialize("5,3,6,2,4,null,8,1,null,null,null,7,9");

    System.out.println(codec.serialize(node));
    String s = "[[1,2,3],[4,5,6,10,11,12,13],[7,8,9]]";
    System.out.println(codec.genListFromArr(s));
  }
}
