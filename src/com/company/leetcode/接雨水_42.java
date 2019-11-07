package com.company.leetcode;

/**
 * @author Eric Cheng
 */
public class 接雨水_42 {

  public static void main(String[] args) {
    int[] h0 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
    int a = trap(h0);
    System.out.println(a);
  }

  public static int trap(int[] height) {
    if (height.length == 0) {
      return 0;
    }
    return getTrap(height, maxIndex(height));
  }

  /**
   * Accepted
   * 315/315 cases passed (1 ms)
   * Your runtime beats 100 % of java submissions
   * Your memory usage beats 84.89 % of java submissions (37.2 MB)
   */
  private static int getTrap(int[] height, int maxIndex) {
    int trapSum = 0;
    int localMax = 0;
    for (int i = height.length - 1; i > maxIndex; --i) {
      int currentHeight = height[i];
      if (currentHeight > localMax) {
        localMax = currentHeight;
      } else {
        trapSum += (localMax - currentHeight);
      }
    }
    localMax = 0;
    for (int i = 0; i < maxIndex; ++i) {
      int currentHeight = height[i];
      if (currentHeight > localMax) {
        localMax = currentHeight;
      } else {
        trapSum += (localMax - currentHeight);
      }
    }

    return trapSum;
  }

  private static int maxIndex(int[] height) {
    int maxIndex = -1;
    int maxValue = Integer.MIN_VALUE;
    for (int i = 0; i < height.length; ++i) {
      if (height[i] > maxValue) {
        maxIndex = i;
        maxValue = height[i];
      }
    }
    return maxIndex;
  }

}
