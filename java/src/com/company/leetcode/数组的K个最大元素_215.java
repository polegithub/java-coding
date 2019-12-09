package com.company.leetcode;

import java.util.Arrays;

/**
 * @author Eric Cheng
 */
public class 数组的K个最大元素_215 {

  public static void main(String[] args) {
    int[] nums = { 7,8,9,2,3,4,5 };
    int result = findKthLargest(nums, 2);
    System.out.println(Arrays.toString(nums) + ", and result is : " + result);
  }

  // !!! 这个是从大到小的排序 ！！！
  public static int findKthLargest(int[] nums, int k) {
    int kIndex = k - 1;
    if (kIndex >= nums.length || kIndex < 0) {
      return 0;
    }

    quickSort2(nums, 0, nums.length - 1);
    //    quickSort1(nums, 0, nums.length - 1);
    return nums[kIndex];
  }

  /**
   * Accepted
   * 32/32 cases passed (45 ms)
   * Your runtime beats 30.47 % of java submissions
   * Your memory usage beats 93.88 % of java submissions (38 MB)
   */
  private static void quickSort1(int[] nums, int start, int end) {
    if (end <= start) {
      return;
    }
    int index = dividedIndex(nums, start, end);
    quickSort1(nums, start, index - 1);
    quickSort1(nums, index + 1, end);
  }

  private static int dividedIndex(int[] nums, int start, int end) {
    int left = start;
    int right = end;
    int dividedValue = nums[left];
    while (left < right) {
      while (left < right && nums[right] <= dividedValue) {
        right--;
      }
      if (left < right) {
        nums[left] = nums[right];
        left++;
      }
      while (left < right && nums[left] >= dividedValue) {
        left++;
      }
      if (left < right) {
        nums[right] = nums[left];
        right--;
      }
    }
    nums[left] = dividedValue;
    return left;
  }

  /**
   * Accepted
   * 32/32 cases passed (3 ms)
   * Your runtime beats 91.97 % of java submissions
   * Your memory usage beats 94.81 % of java submissions (37.4 MB)
   */
  private static void quickSort2(int[] nums, int start, int end) {
    if (end <= start) {
      return;
    }
    int left = start;
    int right = end;
    int middleIndex = (start + end) / 2;
    int dividedValue = nums[middleIndex];
    while (left <= right) {
      while (nums[right] < dividedValue) {
        right--;
      }
      while (nums[left] > dividedValue) {
        left++;
      }
      if (left <= right) {
        swap(nums, left, right);
        left++;
        right--;
      }
    }
    // now must: right < left
    quickSort2(nums, start, right);
    quickSort2(nums, left, end);
  }

  private static void swap(int[] nums, int left, int right) {
    int leftValue = nums[left];
    nums[left] = nums[right];
    nums[right] = leftValue;
  }
}

