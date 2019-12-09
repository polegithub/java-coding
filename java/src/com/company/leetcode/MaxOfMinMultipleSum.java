package com.company.leetcode;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Eric Cheng
 */
public class MaxOfMinMultipleSum {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    //    List<Long> datas = new ArrayList<>();
    //    for (int i = 0; i < n; i++) {
    //      datas.add(scan.nextLong());
    //    }
    //    long max = 0;
    //    for (int i = 0; i < datas.size(); i++) {
    //      Long left = leftSum(datas, i);
    //      Long right = rightSum(datas, i);
    //      Long currentValue = datas.get(i);
    //      long currentMax = currentValue * (left + currentValue + right);
    //      System.out.println(String.valueOf(left) + ", right:" + String.valueOf(right));
    //      System.out.println(
    //          String.valueOf(datas.get(i)) + ", sum:" + String.valueOf(left)
    //              + ", result:"
    //              + String.valueOf(currentMax));
    //      max = Long.max(max, currentMax);
    //    }
    //    System.out.println(max);

    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = scan.nextInt();
    }
    System.out.println(getMaxIntervalSum(nums));
  }

  private static Long leftSum(List<Long> datas, int currentIndex) {
    Long sum = 0L;
    if (currentIndex == 0) {
      return sum;
    }
    Long currenetValue = datas.get(currentIndex);
    for (int i = currentIndex - 1; i >= 0; i--) {
      Long data = datas.get(i);
      if (data < currenetValue) {
        return sum;
      } else {
        sum += data;
      }
    }
    return sum;
  }

  private static Long rightSum(List<Long> datas, int currentIndex) {
    Long sum = 0L;
    if (currentIndex == datas.size() - 1) {
      return sum;
    }
    Long currenetValue = datas.get(currentIndex);
    for (int i = currentIndex + 1; i < datas.size(); i++) {
      Long data = datas.get(i);
      if (data < currenetValue) {
        return sum;
      } else {
        sum += data;
      }
    }
    return sum;
  }

  public static long getMaxIntervalSum(int[] nums) {
    long max = 0;
    long[] preSum = new long[nums.length + 1];
    for (int i = 1; i <= nums.length; i++) {
      preSum[i] = preSum[i - 1] + nums[i - 1];
    }

    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i <= nums.length; i++) {
      int curr = i == nums.length ? -1 : nums[i];
      while (!stack.isEmpty() && curr < nums[stack.peek()]) {
        int num = nums[stack.pop()];
        if (stack.isEmpty()) {
          max = Math.max(max, preSum[i] * num);
        } else {
          max = Math.max(max, (preSum[i] - preSum[stack.peek() + 1]) * num);
        }
      }
      stack.push(i);
    }

    return max;
  }
}
