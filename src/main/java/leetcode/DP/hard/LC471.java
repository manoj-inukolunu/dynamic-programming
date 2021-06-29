package leetcode.DP.hard;


import java.util.ArrayList;
import java.util.List;

public class LC471 {

  class Pair {

    int count;
    String str;

    public Pair(int count, String str) {
      this.count = count;
      this.str = str;
    }
  }

  public String encode(String s) {
    List<Pair> list = dfs(s, 0, s.length() - 1);
    return "";
  }

  private List<Pair> dfs(String str, int start, int end) {
    if (start == end) {
      Pair p = new Pair(1, str.charAt(start) + "");
      List<Pair> list = new ArrayList<>();
      list.add(p);
      return list;
    }
    if (start > end) {
      return new ArrayList<>();
    }
    List<Pair> ret = new ArrayList<>();
    for (int i = start; i <= end; i++) {
      List<Pair> left = dfs(str, start, i);
      List<Pair> right = dfs(str, i + 1, end);

    }
    return ret;
  }

  public static void main(String[] args) {
    LC471 l = new LC471();
  }
}



