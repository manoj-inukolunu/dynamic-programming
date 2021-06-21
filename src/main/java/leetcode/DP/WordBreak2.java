package leetcode.DP;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * @author manoji on 3/14/20.
 */
public class WordBreak2 {


  public List<String> wordBreak(String s, List<String> wordDict) {
    Stack<String> list = new Stack<>();
    List<String> strings = new ArrayList<>();
    wordBreakRecur(s, 0, s.length(), wordDict, list, strings);
    return strings;
  }

  private boolean wordBreakRecur(String s, int begin, int end, List<String> wordDict, Stack<String> list, List<String> strings) {
    if (begin > end) {
      return false;
    }
    if (begin == end) {
      StringBuffer buffer = new StringBuffer();
      for (String str : list) {
        buffer.append(str);
        buffer.append(" ");
      }
      buffer.deleteCharAt(buffer.length() - 1);
      strings.add(buffer.toString());
      return true;
    }
    boolean found = false;
    HashSet<String> visited = new HashSet<>();
    for (int i = begin + 1; i <= end; i++) {
      String str = s.substring(begin, i);
      if (wordDict.contains(str) && !visited.contains(str)) {
        list.push(str);
        visited.add(str);
        if (wordBreakRecur(s, i, end, wordDict, list, strings)) {
          found = true;
        }
        list.pop();
        //visited.remove(str);
      }
    }
    return found;
  }

  public static void main(String args[]) {
    WordBreak2 wordBreak = new WordBreak2();
    System.out.println(wordBreak.wordBreak(
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
        Lists.newArrayList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")));
  }


}
