package leetcode.DP;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author manoji on 1/28/20.
 */
public class StockWithTransactionFee {

  public int maxProfit(int[] prices, int fee) {
    Integer[][] d = new Integer[prices.length][2];
    return maxProfit2(prices, 0, false, 0, fee);
  }

  private int maxProfitRecursive(int[] prices, int currentIndex, Integer held, int boughtIndex, int fee,
      Integer[][] dp) {
    if (currentIndex >= prices.length) {
      return 0;
    }
    if (dp[currentIndex][held] != null) {
      return dp[currentIndex][held];
    }
    if (held == 1) {
      //sell on currentDay
      if (prices[currentIndex] > prices[boughtIndex] + fee) {
        int profitSellOnCurrentDay =
            (prices[currentIndex] - prices[boughtIndex] - fee) + maxProfitRecursive(prices, currentIndex + 1, 0, -1, fee,
                dp);
        int dontSellOnCurrentDay = maxProfitRecursive(prices, currentIndex + 1, held, boughtIndex, fee, dp);
        dp[currentIndex][held] = Math.max(profitSellOnCurrentDay, dontSellOnCurrentDay);
        return Math.max(profitSellOnCurrentDay, dontSellOnCurrentDay);
      } else {
        int dontSellOnCurrentDay = maxProfitRecursive(prices, currentIndex + 1, held, boughtIndex, fee, dp);
        dp[currentIndex][held] = dontSellOnCurrentDay;
        return dontSellOnCurrentDay;
      }
    } else {
      int profitBuyOnCurrentDay = maxProfitRecursive(prices, currentIndex + 1, 1, currentIndex, fee, dp);
      int skipCurrentDay = maxProfitRecursive(prices, currentIndex + 1, held, boughtIndex, fee, dp);
      dp[currentIndex][held] = Math.max(profitBuyOnCurrentDay, skipCurrentDay);
      return Math.max(profitBuyOnCurrentDay, skipCurrentDay);
    }
  }

  private int maxProfit1(int[] prices, int index, int fee, int[] dp) {
    if (dp[index] != -1 && dp[index] != 0) {
      return dp[index];
    }
    int maxProfit = 0;
    for (int i = index; i < prices.length; i++) {
      int currProfitBuy = 0;
      for (int j = i + 1; j < prices.length; j++) {
        if (prices[j] + fee > prices[i]) {
          int sum = prices[j] - prices[i] - fee + maxProfit1(prices, j, fee, dp);
          if (sum > currProfitBuy) {
            currProfitBuy = sum;
          }
        }
      }
      if (currProfitBuy > maxProfit) {
        maxProfit = currProfitBuy;
      }
    }
    dp[index] = maxProfit;
    return maxProfit;
  }

  private int maxProfit2(int[] prices, int day, boolean hold, int stock, int fee) {
    if (day >= prices.length) {
      return 0;
    }
    if (hold) {
      if (prices[day] > stock + fee) {
        //sell
        int sell = prices[day] - stock - fee + maxProfit2(prices, day + 1, false, -1, fee);
        //dont sell
        int dontSell = maxProfit2(prices, day + 1, hold, stock, fee);
        return Math.max(sell, dontSell);
      } else {
        int dontSell = maxProfit2(prices, day + 1, hold, stock, fee);
        return dontSell;
      }
    } else {
      int buy = -prices[day] + maxProfit2(prices, day + 1, true, prices[day], fee);
      int dontBuy = maxProfit2(prices, day + 1, hold, stock, fee);
      return Math.max(buy, dontBuy);
    }
  }


  public static void main(String args[]) throws Exception {
    StockWithTransactionFee stockWithTransactionFee = new StockWithTransactionFee();
    Scanner scanner = new Scanner(new FileInputStream(new File("/Users/manoji/test-data.txt")));
    List<Integer> integerList = new ArrayList<>();
    while (scanner.hasNextLine()) {
      String line[] = scanner.nextLine().split(",");
      for (String num : line) {
        integerList.add(Integer.parseInt(num));
      }
    }
    int[] stocks = integerList.stream().mapToInt(i -> i).toArray();
    int[] test = new int[]{1, 3, 2, 8, 4, 9};
    int[] test1 = new int[]{1, 3, 7, 5, 10, 3};
    System.out.println(stockWithTransactionFee.maxProfit(test, 2));//  6806));
  }
}
