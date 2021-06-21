package leetcode.DP;

import java.util.Arrays;

/**
 * @author manoji on 2/29/20.
 */
public class OnesAndZeroes {

  public int findMaxForm(String[] strs, int m, int n) {
    Integer[][][] arr = new Integer[m + 1][n + 1][strs.length + 1];
    return findMaxFormRecur(strs, m, n, 0, 0, arr);
  }

  public int findMaxFormRecur(String[] strs, int m, int n, int index, int count, Integer[][][] arr) {
    if (m == 0 || n == 0) {
      return count;
    }
    if (index >= strs.length) {
      return count;
    }
    if (arr[m][n][index] != null) {
      return arr[m][n][index];
    }
    int[] zeroesAndOnes = getCount(strs[index]);
    if (m - zeroesAndOnes[0] >= 0 && n - zeroesAndOnes[1] >= 0) {
      int incl = findMaxFormRecur(strs, m - zeroesAndOnes[0], n - zeroesAndOnes[1], index + 1, count + 1, arr);
      int excl = findMaxFormRecur(strs, m, n, index + 1, count, arr);
      arr[m][n][index] = Math.max(incl, excl);
      return Math.max(incl, excl);
    } else {
      return findMaxFormRecur(strs, m, n, index + 1, count, arr);
    }
  }


  private int[] getCount(String str) {
    int zeroes = 0;
    int ones = 0;
    for (Character c : str.toCharArray()) {
      if (c == '0') {
        zeroes++;
      } else if (c == '1') {
        ones++;
      }
    }
    return new int[]{zeroes, ones};
  }


  public static void main(String args[]) {
    OnesAndZeroes onesAndZeroes = new OnesAndZeroes();
    String arr[] = new String[]{"10", "0001", "111001", "1", "0"};
    String arr1[] = new String[]{"10", "0", "1"};
    String arr2[] = new String[]{"011", "1", "11", "0", "010", "1", "10", "1", "1", "0", "0", "0", "01111", "011", "11", "00", "11", "10", "1", "0",
        "0", "0", "0", "101", "001110", "1", "0", "1", "0", "0", "10", "00100", "0", "10", "1", "1", "1", "011", "11", "11", "10", "10", "0000",
        "01",
        "1", "10", "0"};
    String arr3[] = new String[]{
        "0111000", "010101", "111", "000010", "01101", "0", "1", "01", "1010", "00", "1111", "001", "111000", "011", "10", "1101001111"
    };
    int val = onesAndZeroes.findMaxForm(arr3, 9, 80);
    System.out.println(val);

    System.out.println(Arrays.toString(onesAndZeroes.getCount("1101001111")));
  }
}
