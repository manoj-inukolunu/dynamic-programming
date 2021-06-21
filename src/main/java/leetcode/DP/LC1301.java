package leetcode.DP;


import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1301 {

  int mod = (int) (Math.pow(10, 9) + 7);

  static class Pair {

    int sum;
    int num;

    public Pair(int sum, int num) {
      this.sum = sum;
      this.num = num;
    }
  }


  int getVal(char ch) {
    if (ch == 'E') {
      return 0;
    } else if (Character.isDigit(ch)) {
      return Character.getNumericValue(ch);
    } else {
      return 0;
    }
  }

  public int[] pathsWithMaxScore(List<String> board) {
    char[][] grid = new char[board.size()][board.get(0).length()];
    for (int i = 0; i < board.size(); i++) {
      grid[i] = board.get(i).toCharArray();
    }
    Pair[][] arr = new Pair[grid.length][grid[0].length];
    int n = grid.length, m = grid[0].length;
    arr[n - 1][m - 1] = new Pair(getVal(grid[n - 1][m - 1]), 1);
    for (int i = n - 1; i >= 0; i--) {
      for (int j = m - 1; j >= 0; j--) {
        char ch = grid[i][j];
        int val = getVal(ch);
        List<Pair> list = new ArrayList<>();
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {1, 1}};
        for (int[] dir : dirs) {
          int prevR = dir[0] + i;
          int prevC = dir[1] + j;
          if (prevR < n && prevC < m && grid[prevR][prevC] != 'X' && arr[prevR][prevC] != null) {
            list.add(arr[prevR][prevC]);
          }
        }
        list.sort((o1, o2) -> -Integer.compare(o1.sum, o2.sum));
        if (!list.isEmpty()) {
          if (list.size() == 3 && list.get(0).sum == list.get(1).sum && list.get(1).sum == list.get(2).sum) {
            arr[i][j] = new Pair(list.get(0).sum + val, mod3(list));
          } else if (list.size() > 2 && list.get(0).sum == list.get(1).sum) {
            arr[i][j] = new Pair(list.get(0).sum + val, mod2(list));
          } else {
            arr[i][j] = new Pair(list.get(0).sum + val, list.get(0).num);
          }
        }
      }
    }
    return arr[0][0] != null ? new int[]{arr[0][0].sum, arr[0][0].num} : new int[]{0, 0};
  }

  private int mod2(List<Pair> list) {
    return (list.get(0).num % mod + list.get(1).num % mod) % mod;
  }

  private int mod3(List<Pair> list) {
    return ((list.get(0).num % mod + list.get(1).num % mod) % mod + list.get(2).num % mod) % mod;
  }

  public static void main(String[] args) {
    LC1301 l = new LC1301();
    System.out.println(Arrays.toString(l.pathsWithMaxScore(Lists.newArrayList("E11345", "X452XX", "3X43X4", "422812", "284522", "13422S"))));
  }
}



