package leetcode.DP;

import com.leetcode.dfs.TreeNode;

/**
 * @author manoji on 3/2/20.
 */
public class LinkedListInBinaryTree {


  public class ListNode {

    int val;
    ListNode next;

    public ListNode(int x) {
      val = x;
    }
  }

  public boolean isSubPath(ListNode head, TreeNode root) {
    return isSubPathTop(head, root);
  }

  private boolean isSubPathTop(ListNode head, TreeNode root) {
    if (root == null) {
      return false;
    }
    if (!isSubPathRecur(head, root, null)) {
      return isSubPathTop(head, root.left) || isSubPathTop(head, root.right);
    }
    return true;
  }

  public boolean isSubPathRecur(ListNode head, TreeNode root, Boolean found) {
    if (head == null && root == null) {
      return true;
    }
    if (root == null && head != null) {
      return false;
    }
    if (head == null) {
      return true;
    }

    if (found != null && found && head.val != root.val) {
      return false;
    } else {
      if (head.val == root.val) {
        return isSubPathRecur(head.next, root.left, true) || isSubPathRecur(head.next, root.right, true);
      } else {
        return isSubPathRecur(head, root.left, false) || isSubPathRecur(head, root.right, false);
      }
    }
  }

	/*public static void main(String args[]) {
		LinkedListInBinaryTree linkedListInBinaryTree = new LinkedListInBinaryTree();

		TreeNode node = new Codec().deserialize("1,null,1,10,1,9");
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(10);
//		listNode.next.next = new ListNode(8);

		System.out.println(linkedListInBinaryTree.isSubPath(listNode, node));

	}*/

}
