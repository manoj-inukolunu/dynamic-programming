package leetcode.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author manoji on 2019-12-30.
 */
public class Solution {

  public int findLongestChain(int[][] pairs) {
    Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
    Integer[] dp = new Integer[pairs.length + 1];

    Arrays.fill(dp, 1);
    for (int i = 1; i < pairs.length; i++) {
      for (int j = 0; j < i; j++) {
        if (pairs[j][i] < pairs[i][0]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    return Collections.max(Arrays.asList(dp));
  }

  public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
    return shoppingOffersRecursive(price, special, needs, 0);
  }

  private int shoppingOffersRecursive(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int currentIndex) {
    if (currentIndex >= special.size()) {
      if (isEmpty(needs)) {
        return 0;
      } else {
        return totalPrice(price, needs);
      }
    }

    int sumIncludingCurrentSpecialOffer = Integer.MAX_VALUE;
    if (canPickSpecialOffer(special.get(currentIndex), needs)) {
      List<Integer> currentSpecialOffer = special.get(currentIndex);
      sumIncludingCurrentSpecialOffer = currentSpecialOffer.get(currentSpecialOffer.size() - 1) + shoppingOffersRecursive(price, special,
          updateNeeds(needs, currentSpecialOffer), currentIndex);
    }
    int sumWithoutCurrentSpecialOffer = shoppingOffersRecursive(price, special, needs, currentIndex + 1);

    int priceExcludingAllOffers = totalPrice(price, needs);

    return Math.min(sumIncludingCurrentSpecialOffer, Math.min(priceExcludingAllOffers, sumWithoutCurrentSpecialOffer));
  }

  private List<Integer> updateNeeds(List<Integer> needs, List<Integer> currentSpecialOffer) {
    List<Integer> newNeeds = new ArrayList<>();
    for (int i = 0; i < needs.size(); i++) {
      newNeeds.add(i, needs.get(i) - currentSpecialOffer.get(i));
    }
    return newNeeds;
  }

  private boolean canPickSpecialOffer(List<Integer> offers, List<Integer> needs) {
    for (int i = 0; i < needs.size(); i++) {
      if (offers.get(i) > needs.get(i)) {
        return false;
      }
    }
    return true;
  }

  private int totalPrice(List<Integer> price, List<Integer> needs) {
    int total = 0;
    for (int i = 0; i < needs.size(); i++) {
      total += price.get(i) * needs.get(i);
    }
    return total;
  }

  private boolean isEmpty(List<Integer> needs) {
    for (int i = 0; i < needs.size(); i++) {
      if (needs.get(i) != 0) {
        return false;
      }
    }
    return true;
  }


  public int numSquares(int n) {

    List<Integer> squares = new ArrayList();
    for (int i = 1; i <= n; i++) {
      int num = i * i;
      if (num <= n) {
        squares.add(num);
      }
    }
    Integer[][] dp = new Integer[squares.size()][n + 1];
    return numSquaresRecursive(squares, 0, n, dp);
  }

  private int numSquaresRecursive(List<Integer> squares, int currentIndex, int sum, Integer[][] dp) {
    if (sum == 0) {
      return 0;
    }

    if (currentIndex >= squares.size()) {
      return Integer.MAX_VALUE;
    }

    if (dp[currentIndex][sum] != null) {
      return dp[currentIndex][sum];
    }

    int minCoinsIncludingSum = Integer.MAX_VALUE;
    if (currentIndex < squares.size() && squares.get(currentIndex) <= sum) {
      int res = numSquaresRecursive(squares, currentIndex, sum - squares.get(currentIndex), dp);
      if (res != Integer.MAX_VALUE) {
        minCoinsIncludingSum = res + 1;
      }
    }

    int minCoinsExcludingSum = numSquaresRecursive(squares, currentIndex + 1, sum, dp);

    dp[currentIndex][sum] = Math.min(minCoinsIncludingSum, minCoinsExcludingSum);

    return Math.min(minCoinsIncludingSum, minCoinsExcludingSum);
  }

  private int countChangeRecursive(List<Integer> denominations, int total, int currentIndex) {
    // base check
    if (total == 0) {
      return 0;
    }

    if (denominations.size() == 0 || currentIndex >= denominations.size()) {
      return Integer.MAX_VALUE;
    }

    // recursive call after selecting the coin at the currentIndex
    // if the coin at currentIndex exceeds the total, we shouldn't process this
    int count1 = Integer.MAX_VALUE;
    if (denominations.get(currentIndex) <= total) {
      int res = countChangeRecursive(denominations, total - denominations.get(currentIndex), currentIndex);
      if (res != Integer.MAX_VALUE) {
        count1 = res + 1;
      }
    }

    // recursive call after excluding the coin at the currentIndex
    int count2 = countChangeRecursive(denominations, total, currentIndex + 1);

    return count1 + count2;
  }

  public int combinationSum4(int[] nums, int target) {
    return csr(nums, target, 0);
  }

  private int csr(int[] nums, int target, int currentIndex) {
    System.out.println(currentIndex);
    if (target == 0) {
      return 1;
    }

    if (currentIndex >= nums.length) {
      return 0;
    }

    int sum1 = 0;
    if (nums[currentIndex] <= target) {
      sum1 = csr(nums, target - nums[currentIndex], currentIndex);
    }
    int sum2 = csr(nums, target, currentIndex + 1);
    return sum1 + sum2;
  }

  private int csr(int[] nums, int target, HashMap<Integer, Integer> map) {
    int totalWays = 0;
    if (target == 0) {
      return 1;
    }
    if (map.containsKey(target)) {
      return map.get(target);
    }
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] <= target) {
        int total = csr(nums, target - nums[i], map);
        map.put(target - nums[i], total);
        totalWays += map.get(target - nums[i]);
      }
    }
    return totalWays;
  }

  public int findNumbers(int[] nums) {
    int total = 0;
    for (int i = 0; i < nums.length; i++) {
      if (String.valueOf(nums[i]).length() % 2 == 0) {
        total++;
      }
    }
    return total;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();

    /*List<Integer> prices = Lists.newArrayList(2, 5);
    List<List<Integer>> offers = Lists.newArrayList(Lists.newArrayList(3, 0, 5), Lists.newArrayList(1, 2, 10));
    List<Integer> needs = Lists.newArrayList(3, 2);

    //[2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
    List<Integer> prices1 = Lists.newArrayList(2, 3, 4);
    List<List<Integer>> offers1 = Lists.newArrayList(Lists.newArrayList(1, 1, 0, 4), Lists.newArrayList(2, 2, 1, 9));
    List<Integer> needs1 = Lists.newArrayList(1, 2, 1);

    System.out.println(solution.shoppingOffers(prices1, offers1, needs1));*/
    System.out.println(solution.findNumbers(new int[]{12, 345, 2, 6, 7896}));
  }

}
