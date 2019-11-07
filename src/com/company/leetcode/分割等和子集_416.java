package com.company.leetcode;

/**
 * @author Eric Cheng
 */
public class 分割等和子集_416 {

  public static void main(String[] args) {
    int[] a = { 1, 5, 11, 5 };
    System.out.println(canPartition(a));
  }

  public static boolean canPartition(int[] nums) {
    if (nums == null || nums.length == 0) {
      return false;
    }
    int sum = sum(nums);
    if (sum % 2 != 0) {
      return false;
    }
    //TODO:
    return false;
  }

  private static int sum(int[] nums) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    return sum;
  }
}
