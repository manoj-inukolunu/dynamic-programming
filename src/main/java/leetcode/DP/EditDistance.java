package leetcode.DP;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author manoji on 3/7/20.
 */
public class EditDistance {

    /**
     * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }
     */
    class Solution {

        int max = Integer.MIN_VALUE;

        class Data {

            TreeNode node;
            int dir;

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof Data)) {
                    return false;
                }
                Data data = (Data) o;
                return dir == data.dir &&
                        Objects.equals(node, data.node);
            }

            @Override
            public int hashCode() {
                return Objects.hash(node, dir);
            }

            public Data(TreeNode node, int dir) {
                this.node = node;
                this.dir = dir;
            }

        }

        public int longestZigZag(TreeNode root) {
            HashMap<Data, Integer> map = new HashMap();
            ddfs(root, map);
            return max;
        }


        private void ddfs(TreeNode root, HashMap<Data, Integer> map) {
            if (root == null) {
                return;
            }
            int val = Math.max(dfs(root, 0, map), dfs(root, 1, map)) - 1;
            if (val > max) {
                max = val;
            }
            ddfs(root.left, map);
            ddfs(root.right, map);
        }

        private int dfs(TreeNode root, int direction, HashMap<Data, Integer> map) {
            if (root == null) {
                return 0;
            }
            Data data = new Data(root, direction);
            if (map.containsKey(data)) {
                return map.get(data);
            }
            if (direction == 0) {
                int val = 1 + dfs(root.right, 1, map);
                map.put(data, val);
                return val;
            } else {
                int val = 1 + dfs(root.left, 0, map);
                map.put(data, val);
                return val;
            }
        }
    }

    public int minDistance(String word1, String word2) {
        HashMap<String, Integer> map = new HashMap<>();
        return eDist(word1, word2, 0, 0, map);
    }

    private int eDist(String word1, String word2, int index1, int index2, HashMap<String, Integer> map) {
        if (index1 >= word1.length() && index2 < word2.length()) {
            return word2.length() - index2;
        } else if (index1 < word1.length() && index2 >= word2.length()) {
            return word1.length() - index1;
        } else if (index1 >= word1.length() && index2 >= word2.length()) {
            return 0;
        }
        String key = index1 + "|" + index2;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (word1.charAt(index1) == word2.charAt(index2)) {
            return eDist(word1, word2, index1 + 1, index2 + 1, map);
        }
        // delete
        int del = 1 + eDist(word1, word2, index1, index2 + 1, map);
        int replace = 1 + eDist(word1, word2, index1 + 1, index2 + 1, map);
        int insert = 1 + eDist(word1, word2, index1 + 1, index2, map);
        map.put(key, Math.min(del, Math.min(replace, insert)));
        return Math.min(del, Math.min(replace, insert));
    }

    public static void main(String args[]) {
        EditDistance editDistance = new EditDistance();

        System.out.println(editDistance.minDistance("intention", "execution"));
    }

}
