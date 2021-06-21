package leetcode.DP;

/**
 * @author manoji on 1/25/20.
 */
public class ArithmeticSlices {


  private int numSlices = 0;

  public int numberOfArithmeticSlices(int[] A) {
    Boolean[][] arr = new Boolean[A.length][A.length];
    num(A, 0, 0, 1, A[1] - A[0], 2, arr);
    return numSlices;
  }


  private void num(int[] arr, int startIndex, int prevIndex, int currentIndex, Integer diff, int length, Boolean[][] dp) {
    //System.out.println("Checking array " + startIndex + " " + currentIndex);
    if (dp[startIndex][currentIndex] != null) {
      return;
    } else {
      dp[startIndex][currentIndex] = true;
    }
    if (currentIndex >= arr.length) {
      return;
    }
    if (length >= 3) {
      numSlices++;
    }
    if (currentIndex + 1 < arr.length) {
      if (diff == arr[currentIndex + 1] - arr[currentIndex]) {
        num(arr, startIndex, currentIndex, currentIndex + 1, diff, ++length, dp);
        if (currentIndex <= arr.length - 3) {
          num(arr, currentIndex, currentIndex, currentIndex + 1, arr[currentIndex + 1] - arr[currentIndex], 2, dp);
        }
      } else {
        if (currentIndex <= arr.length - 3) {
          num(arr, currentIndex, currentIndex, currentIndex + 1, arr[currentIndex + 1] - arr[currentIndex], 2, dp);
        }
      }
    }
  }

  public int numberOfArithmeticSlicesIterative(int[] A) {
    int[] count = new int[A.length];
    int sum = 0;
    for (int i = 2; i < count.length; i++) {
      if (A[i - 1] - A[i - 2] == A[i] - A[i - 1]) {
        count[i] = 1 + count[i - 1];
        sum += count[i];
      }
    }
    return sum;
  }


  public static void main(String args[]) {
    int arr[] = new int[]{1, 1, 2, 5, 7};
    ArithmeticSlices arithmeticSlices = new ArithmeticSlices();
//		System.out.println(arithmeticSlices.isAritmetic(new int[]{2, 3, 4}));
    System.out.println(arithmeticSlices.numberOfArithmeticSlices(arr));
  }

}
