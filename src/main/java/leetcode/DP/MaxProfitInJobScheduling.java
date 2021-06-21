package leetcode.DP;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.IOUtils;

/**
 * @author manoji on 2/20/20.
 */
public class MaxProfitInJobScheduling {

  class Job {

    int startTime;
    int endTime;
    int profit;

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("Job{");
      sb.append("startTime=").append(startTime);
      sb.append(", endTime=").append(endTime);
      sb.append(", profit=").append(profit);
      sb.append('}');
      return sb.toString();
    }

    public Job(int startTime, int endTime, int profit) {
      this.startTime = startTime;
      this.endTime = endTime;
      this.profit = profit;
    }
  }

  public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    List<Job> jobs = new ArrayList<>();
    for (int i = 0; i < startTime.length; i++) {
      jobs.add(new Job(startTime[i], endTime[i], profit[i]));
    }
    Collections.sort(jobs, Comparator.comparing(job -> job.endTime));
    int maxProfit = maxProfitIterative(jobs);
    return maxProfit;
  }

  private int maxProfit(List<Job> jobs, int currentJobIndex, int currentTime, HashMap<String, Integer> map) {
    if (currentJobIndex >= jobs.size()) {
      return 0;
    }

    String key = currentJobIndex + "|" + currentTime;
    if (map.containsKey(key)) {
      return map.get(key);
    }

    int include = -1;
    //include current job;
    if (canInclude(jobs.get(currentJobIndex), currentTime)) {
      include = jobs.get(currentJobIndex).profit + maxProfit(jobs, currentJobIndex + 1, jobs.get(currentJobIndex).endTime, map);
    }
    int exclude = maxProfit(jobs, currentJobIndex + 1, currentTime, map);
    map.put(key, Math.max(include, exclude));
    return Math.max(include, exclude);
  }

  private boolean canInclude(Job job, int time) {
    return job.startTime >= time;
  }

  int latestNonConflict(List<Job> arr, int i) {
    for (int j = i - 1; j >= 0; j--) {
      if (arr.get(j).endTime <= arr.get(i).startTime) {
        return j;
      }
    }
    return -1;
  }

  private int maxProfitIterative(List<Job> jobs) {
    Collections.sort(jobs, Comparator.comparing(job -> job.endTime));
    Integer[] table = new Integer[jobs.size()];
    table[0] = jobs.get(0).profit;
    for (int i = 1; i < jobs.size(); i++) {
      int inclProf = jobs.get(i).profit;
      int l = latestNonConflict(jobs, i);
      if (l != -1) {
        inclProf += table[l];
      }
      table[i] = Math.max(inclProf, table[i - 1]);
    }
    int result = table[jobs.size() - 1];
    return result;
  }


  public static void main(String args[]) throws Exception {
    MaxProfitInJobScheduling maxProfitInJobScheduling = new MaxProfitInJobScheduling();
		/*
[6,15,7,11,1,3,16,2]
[19,18,19,16,10,8,19,8]
[2,9,1,19,5,7,3,19]
		 */
    int[] startTime = new int[]{6, 15, 7, 11, 1, 3, 16, 2};
    int[] endTime = new int[]{19, 18, 19, 16, 10, 8, 19, 8};
    int[] profit = new int[]{2, 9, 1, 19, 5, 7, 3, 19};
    startTime = Arrays.stream(IOUtils.readLines(new FileReader(new File("/Users/manoji/testcase.txt"))).get(0).replaceAll("\\[", "").replaceAll(
        "\\]",
        "").split(",")).mapToInt(value -> Integer.parseInt(value)).toArray();
    endTime = Arrays.stream(IOUtils.readLines(new FileReader(new File("/Users/manoji/testcase.txt"))).get(1).replaceAll("\\[", "").replaceAll(
        "\\]",
        "").split(",")).mapToInt(value -> Integer.parseInt(value)).toArray();
    ;
    profit = Arrays.stream(IOUtils.readLines(new FileReader(new File("/Users/manoji/testcase.txt"))).get(2).replaceAll("\\[", "").replaceAll(
        "\\]",
        "").split(",")).mapToInt(value -> Integer.parseInt(value)).toArray();
    int val = maxProfitInJobScheduling.jobScheduling(startTime, endTime, profit);

    System.out.println(val);
  }
}
