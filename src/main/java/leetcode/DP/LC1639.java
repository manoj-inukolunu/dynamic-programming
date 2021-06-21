package leetcode.DP;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LC1639 {

  class Pair {

    int first;
    int second;

    public Pair(int first, int second) {
      this.first = first;
      this.second = second;
    }

    @Override
    public String toString() {
      return "Pair{" +
          "first=" + first +
          ", second=" + second +
          '}';
    }
  }

  public int numWays(String[] words, String target) {
    HashMap<Character, Map<Integer, Integer>> map = new HashMap<>();
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      for (int j = 0; j < word.length(); j++) {
        char ch = word.charAt(j);
        Map<Integer, Integer> countMap = map.getOrDefault(ch, new HashMap<>());
        countMap.put(j, countMap.getOrDefault(j, 0) + 1);
        map.put(ch, countMap);
      }
    }
    HashMap<Character, List<Entry<Integer, Integer>>> sortedMap = new HashMap<>();
    for (char ch : map.keySet()) {
      sortedMap.put(ch, new ArrayList<>(map.get(ch).entrySet()));
      sortedMap.get(ch).sort(Comparator.comparingInt(Entry::getKey));
    }
    System.out.println(sortedMap);
    return dfs(sortedMap, 0, 0, target);
  }

  private int dfs(HashMap<Character, List<Entry<Integer, Integer>>> map, int tIdx, int wIdx, String target) {
    if (tIdx >= target.length()) {
      return 1;
    }
    char ch = target.charAt(tIdx);
    if (map.containsKey(ch)) {
      List<Entry<Integer, Integer>> entryList = map.get(ch);
    }
    return 0;
  }


  public static void main(String[] args) {
    LC1639 l = new LC1639();

    String[] words = new String[]{"acca", "bbbb", "caca"};
    String target = "aba";
    l.numWays(words, target);
  }
}



