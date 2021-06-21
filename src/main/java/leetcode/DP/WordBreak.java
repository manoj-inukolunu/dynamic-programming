package leetcode.DP;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author manoji on 3/14/20.
 */
public class WordBreak {


  public double findMaxAverage(int[] nums, int k) {
    if (nums.length == 1) {
      return Double.valueOf(nums[0]);
    }
    int[] pre = new int[nums.length];
    pre[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      pre[i] = pre[i - 1] + nums[i];
    }
    System.out.println(nums.length);
    Double max = Double.MIN_VALUE;
    for (int i = 0; i + k - 1 < nums.length; i++) {
      int sum = (sumRange(pre, i, i + k - 1));
      System.out.println(sum);
      Double avg = sum / (double) k;
      if (avg.compareTo(max) > 0) {
        max = avg;
      }
    }
    return max;
  }

  int sumRange(int[] pre, int i, int j) {
    if (i > 0) {
      return pre[j] - pre[i - 1];
    }
    return pre[j];
  }

/*

	public int hIndex(int[] citations) {
		Arrays.sort(citations);
		for (int i = 0; i < citations.length; i++) {
			if (citations[i] >= i) {

			}
		}
	}
*/

  private final List<String> strings = new ArrayList();

  public List<String> wordBreak(String s, List<String> wordDict) {
    wordBreakRecursive(s, wordDict, new Stack<>());
    return strings;
  }

  private boolean wordBreakRecursive(String s, List<String> wordDict, Stack<String> list) {
    if (s.length() == 0) {
      return true;
    }
    boolean found = false;
    for (int i = 1; i <= s.length(); i++) {
      String subs = s.substring(0, i);
      if (wordDict.contains(subs)) {
        String str = s.substring(i);
        list.push(subs);
        Boolean exists = wordBreakRecursive(str, wordDict, list);
        if (exists) {
          if (i == s.length()) {
            StringBuffer buffer = new StringBuffer();
            for (String _str : list) {
              buffer.append(_str);
              buffer.append(" ");
            }
            buffer.deleteCharAt(buffer.length() - 1);
            strings.add(buffer.toString());
          }
          found = true;
        }
        list.pop();
      }
    }
    return found;
  }

  public static void main(String args[]) {
    WordBreak wordBreak = new WordBreak();
    System.out.println(wordBreak.findMaxAverage(
        new int[]{8860, -853, 6534, 4477, -4589, 8646, -6155, -5577, -1656, -5779, -2619, -8604, -1358, -8009, 4983, 7063, 3104, -1560, 4080, 2763,
            5616, -2375, 2848, 1394, -7173, -5225, -8244, -809, 8025, -4072, -4391, -9579, 1407, 6700, 2421, -6685, 5481, -1732, -8892, -6645, 3077,
            3287, -4149, 8701, -4393, -9070, -1777, 2237, -3253, -506, -4931, -7366, -8132, 5406, -6300, -275, -1908, 67, 3569, 1433, -7262, -437,
            8303, 4498, -379, 3054, -6285, 4203, 6908, 4433, 3077, 2288, 9733, -8067, 3007, 9725, 9669, 1362, -2561, -4225, 5442, -9006, -429, 160,
            -9234, -4444, 3586, -5711, -9506, -79, -4418, -4348, -5891}, 93));
  }


}
