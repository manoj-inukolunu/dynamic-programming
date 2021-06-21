package leetcode.DP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author manoji on 2/3/20.
 */
public class CheapestFlights {

  class Vertex {

    int cost;
    int stops;
    int number;

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("{");
      sb.append("v=").append(number);
      sb.append(", c=").append(cost);
      sb.append(", l=").append(stops);
      sb.append('}');
      return sb.toString();
    }

    Vertex(int number, int cost, int stops) {
      this.cost = cost;
      this.stops = stops;
      this.number = number;
    }
  }

  class Pair {

    int vertex;
    int cost;

    Pair(int vertex, int cost) {
      this.vertex = vertex;
      this.cost = cost;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("{");
      sb.append(vertex);
      sb.append(",").append(cost);
      sb.append('}');
      return sb.toString();
    }
  }

  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    HashMap<Integer, List<Pair>> map = new HashMap();
    for (int[] row : flights) {
      if (map.containsKey(row[0])) {
        List<Pair> adjacentVertices = map.get(row[0]);
        adjacentVertices.add(new Pair(row[1], row[2]));
        map.put(row[0], adjacentVertices);
      } else {
        ArrayList<Pair> pairList = new ArrayList();
        pairList.add(new Pair(row[1], row[2]));
        map.put(row[0], pairList);
      }
    }
    int val = dijikstraPq(map, src, dst, n, K);
    return val;
  }

  private int dijikstraPq(HashMap<Integer, List<Pair>> map, int src, int dst, int n, int k) {

    PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
    priorityQueue.add(new Vertex(src, 0, -1));

    while (!priorityQueue.isEmpty()) {
      Vertex current = priorityQueue.poll();
      int cost = current.cost;
      int number = current.number;
      int stops = current.stops;

      if (number == dst) {
        return cost;
      }
      if (stops < k) {
        List<Pair> adjacentVertices = map.get(number);
        if (adjacentVertices != null) {
          for (Pair adjacentVertex : adjacentVertices) {
            priorityQueue.add(new Vertex(adjacentVertex.vertex, cost + adjacentVertex.cost, stops + 1));
          }
        }
      }
    }
    return -1;
  }

  public static void main(String args[]) {
    CheapestFlights cheapestFlights = new CheapestFlights();
    int[][] arr = new int[][]{
        {10, 14, 43}, {1, 12, 62}, {4, 2, 62}, {14, 10, 49}, {9, 5, 29}, {13, 7, 53}, {4, 12, 90}, {14, 9, 38}, {11, 2, 64}, {2, 13, 92}, {11, 5,
        42},
        {10, 1, 89}, {14, 0, 32}, {9, 4, 81}, {3, 6,
        97}, {7, 13, 35}, {11, 9, 63}, {5, 7, 82}, {13, 6, 57}, {4, 5, 100}, {2, 9, 34}, {11, 13, 1}, {14, 8, 1}, {12, 10, 42}, {2, 4, 41},
        {0, 6, 55}, {5, 12, 1}, {13, 3, 67}, {3, 13, 36}, {3, 12,
        73}, {7, 5, 72}, {5, 6, 100}, {7, 6, 52}, {4, 7, 43}, {6, 3, 67}, {3, 1, 66}, {8, 12, 30}, {8, 3, 42}, {9, 3, 57}, {12, 6, 31}, {2, 7, 10},
        {14, 4, 91}, {2, 3, 29}, {8, 9, 29}, {2, 11, 65}, {3,
        8, 49}, {6, 14, 22}, {4, 6, 38}, {13, 0, 78}, {1, 10, 97}, {8, 14, 40}, {7, 9, 3}, {14, 6, 4}, {4, 8, 75}, {1, 6, 56}

/*

				{3, 4, 4}, {2, 5, 6}, {4, 7, 10}, {9, 6, 5}, {7, 4, 4}, {6, 2, 10}, {6, 8, 6}, {7, 9, 4}, {1, 5, 4}, {1, 0, 4}, {9, 7, 3}, {7, 0, 5},
				{6, 5, 8}, {1, 7, 6}, {4, 0, 9}, {5, 9, 1}, {8, 7, 3}, {1, 2, 6}, {4, 1, 5}, {5, 2, 4}, {1, 9, 1}, {7, 8, 10}, {0, 4, 2}, {7, 2, 8}
*/

//				{0, 1, 100}, {1, 2, 100}, {0, 2, 500}
//				{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}
    };

    int val = cheapestFlights.findCheapestPrice(15, arr, 1, 4, 10);
//		int val = cheapestFlights.findCheapestPrice(10, arr, 6, 0, 7);
//		int val = cheapestFlights.findCheapestPrice(3, arr, 0, 2, 1);
//		int val = cheapestFlights.findCheapestPrice(4, arr, 0, 3, 1);
    System.out.println(val);
  }


  public boolean isPalindrome(String s) {
    s = s.replaceAll(" ", "");
    StringBuffer buffer = new StringBuffer();
    for (Character c : s.toCharArray()) {
      if (Character.isLetterOrDigit(c)) {
        buffer.append(c);
      }
    }
    String str = buffer.toString();
    String reverse = buffer.reverse().toString();
    return reverse.equalsIgnoreCase(str);
  }

}
