package leetcode.DP.hard;


import java.util.HashMap;
import java.util.Objects;

public class LC871 {

  class Pair {

    int a, b, c;

    public Pair(int a, int b, int c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return a == pair.a && b == pair.b && c == pair.c;
    }

    @Override
    public int hashCode() {
      return Objects.hash(a, b, c);
    }
  }

  HashMap<Pair, Integer> map = new HashMap<>();

  public int minRefuelStops(int target, int fuel, int[][] stations) {
    int val = maxDist(stations, 0, 0, fuel, target);
    return val == Integer.MAX_VALUE ? -1 : val;
  }

  private int maxDist(int[][] stations, int idx, int added, int currFuel, int target) {
    if (idx >= stations.length) {
      return currFuel >= target ? added : Integer.MAX_VALUE;
    }
    Pair p = new Pair(idx, added, currFuel);
    if (map.containsKey(p)) {
      return map.get(p);
    }
    if (stations[idx][0] <= currFuel) {
      int addFuelAtStation = maxDist(stations, idx + 1, added + 1, currFuel + stations[idx][1], target);
      int noAdd = maxDist(stations, idx + 1, added, currFuel, target);
      map.put(p, Math.min(noAdd, addFuelAtStation));
      return Math.min(noAdd, addFuelAtStation);
    }
    map.put(p, Integer.MAX_VALUE);
    return Integer.MAX_VALUE;
  }

  public static void main(String[] args) {
    LC871 l = new LC871();
    int[][] stations = new int[][]{
//        {10, 60}, {20, 30}, {30, 30}, {60, 40}
//        {25, 25}, {50, 25}, {75, 25}
//        {50, 50}
//        {25, 25}, {50, 50}
//        {25, 25}, {50, 100}, {100, 100}, {150, 40}
//        {25, 27}, {36, 187}, {140, 186}, {378, 6}, {492, 202}, {517, 89}, {579, 234}, {673, 86}, {808, 53}, {954, 49}
        {13, 21}, {26, 115}, {100, 47}, {225, 99}, {299, 141}, {444, 198}, {608, 190}, {636, 157}, {647, 255}, {841, 123}
    };
    System.out.println(l.minRefuelStops(1000, 299, stations));
  }
}



