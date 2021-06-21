
package leetcode.DP;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author manoji on 1/30/20.
 */

public class Triangle {

  public int minimumTotal(List<List<Integer>> triangle) {
    for (int i = 1; i < triangle.size(); i++) {
      List<Integer> prev = triangle.get(i - 1);
      List<Integer> current = triangle.get(i);
      for (int j = 0; j < current.size(); j++) {
        if (j == 0) {
          current.set(j, prev.get(j) + current.get(j));
        } else if (j == current.size() - 1) {
          current.set(j, prev.get(j - 1) + current.get(j));
        } else {
          current.set(j, Math.min(prev.get(j - 1), prev.get(j)) + current.get(j));
        }
      }
    }
    int min = Integer.MAX_VALUE;
    List<Integer> lastRow = triangle.get(triangle.size() - 1);
    for (int i = 0; i < lastRow.size(); i++) {
      if (lastRow.get(i) < min) {
        min = lastRow.get(i);
      }
    }
    return min;
  }


  public static void main(String args[]) {
    Triangle triangle = new Triangle();

    List<List<Integer>> lists = Lists
        .newArrayList(Lists.newArrayList(2), Lists.newArrayList(3, 4), Lists.newArrayList(6, 5, 7), Lists.newArrayList(4, 1, 8
            , 3));

    System.out.println(triangle.minimumTotal(lists));
  }

}

