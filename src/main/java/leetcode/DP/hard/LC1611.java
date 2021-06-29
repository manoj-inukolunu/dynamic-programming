package leetcode.DP.hard;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class LC1611 {

  class Pair {

    int num;
    int steps;

    public Pair(int num, int steps) {
      this.num = num;
      this.steps = steps;
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
      return num == pair.num;
    }

    @Override
    public int hashCode() {
      return Objects.hash(num);
    }
  }


  public int minimumOneBitOperations(int n) {
    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(n, 0));
    HashSet<Pair> seen = new HashSet<>();
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (curr.num == 0) {
        return curr.steps;
      }
      if (!seen.contains(curr)) {
        seen.add(curr);
        int op2;
        int k = (int) (2 + (Math.log(Integer.lowestOneBit(curr.num)) / Math.log(2)));
        op2 = toggleKthBit(curr.num, k);

        int op1 = curr.num ^ 1;
        queue.add(new Pair(op1, curr.steps + 1));
        queue.add(new Pair(op2, curr.steps + 1));
      }
    }
    return -1;
  }

  int toggleKthBit(int n, int k) {
    return (n ^ (1 << (k - 1)));
  }

  public static void main(String[] args) {
    LC1611 l = new LC1611();
    System.out.println(l.minimumOneBitOperations(333));
  }
}



