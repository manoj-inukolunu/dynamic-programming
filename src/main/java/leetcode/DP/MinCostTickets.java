package leetcode.DP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author manoji on 2020-01-23.
 */
public class MinCostTickets {

  private int maxDay = -1;

  public int mincostTickets(int[] days, int[] costs) {
    maxDay = maxDay(days);
    HashMap<Integer, Integer> map = new HashMap<>();

    Set<Integer> numDays = new HashSet<>();
    for (int i = 0; i < days.length; i++) {
      numDays.add(days[i]);
    }

    return cost(costs, numDays, 1, map);
  }

  int maxDay(int[] days) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < days.length; i++) {
      if (max < days[i]) {
        max = days[i];
      }
    }

    return max;
  }

  private int cost(int[] costs, Set<Integer> numDays, int currentDay, HashMap<Integer, Integer> map) {
    if (currentDay <= maxDay) {
      if (map.containsKey(currentDay)) {
        return map.get(currentDay);
      }
      //pick 30 days
      int thirtyDays = costs[2] + cost(costs, numDays, currentDay + 30, map);
      //pick 1 day
      int oneDay = costs[0] + cost(costs, numDays, currentDay + 1, map);
      //pick 7 days
      int sevenDays = costs[1] + cost(costs, numDays, currentDay + 7, map);

      if (numDays.contains(currentDay)) {
        map.put(currentDay, Math.min(oneDay, Math.min(sevenDays, thirtyDays)));
        return Math.min(oneDay, Math.min(sevenDays, thirtyDays));
      }
      //no travel on days where we dont have to travel.
      int noTravel = cost(costs, numDays, currentDay + 1, map);
      map.put(currentDay, Math.min(Math.min(oneDay, sevenDays), Math.min(thirtyDays, noTravel)));
      return Math.min(Math.min(oneDay, sevenDays), Math.min(thirtyDays, noTravel));
    }
    return 0;
  }

  public static void main(String args[]) {
    MinCostTickets minCostTickets = new MinCostTickets();

    int[] days = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
    int[] cost = new int[]{2, 7, 15};
    System.out.println(minCostTickets.mincostTickets(days, cost));
  }


}
