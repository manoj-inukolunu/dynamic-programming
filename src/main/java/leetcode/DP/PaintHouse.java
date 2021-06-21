package leetcode.DP;

public class PaintHouse {

  public int minCost(int[][] costs) {
    return minCost(costs, 0, 0);
  }

  private int minCost(int[][] costs, int house, int cost) {
    if (house > costs.length) {
      return cost;
    }

    int red = minCost(costs, house + 1, cost + costs[house][0]);
    int blue = minCost(costs, house + 1, cost + costs[house][1]);
    int green = minCost(costs, house + 1, cost + costs[house][2]);

    return Math.min(red, Math.min(blue, green));
  }

}
