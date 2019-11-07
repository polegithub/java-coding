package com.company.leetcode;

import java.util.Arrays;

/**
 * @author Eric Cheng
 */
public class 下一个排列_31 {

  public static void main(String[] args) {
    int[] nums = { 1, 2, 3 };
    nextPermutation(nums);
    System.out.println(Arrays.toString(nums));
  }

  /**
   * Accepted
   * 265/265 cases passed (2 ms)
   * Your runtime beats 54.29 % of java submissions
   * Your memory usage beats 89.74 % of java submissions (36.5 MB)
   *
   *
   * 最后一步改成快排之后：
   * Accepted
   * 265/265 cases passed (1 ms)
   * Your runtime beats 100 % of java submissions
   * Your memory usage beats 89.74 % of java submissions (36.5 MB)
   *
   */
  private static void nextPermutation(int[] nums) {
    if (nums == null || nums.length == 0) {
      return;
    }
    int reverseIndex = -1;
    for (int i = nums.length - 1; i > 0; i--) {
      if (nums[i] > nums[i - 1]) {
        reverseIndex = i - 1;
        break;
      }
    }
    if (reverseIndex >= 0) {
      for (int i = nums.length - 1; i > reverseIndex; i--) {
        if (nums[i] > nums[reverseIndex]) {
          swap(nums, i, reverseIndex);
          break;
        }
      }
      sortAsc(nums, reverseIndex + 1);
    } else {
      sortAsc(nums, 0);
    }
  }

  private static void swap(int[] nums, int left, int right) {
    int leftValue = nums[left];
    nums[left] = nums[right];
    nums[right] = leftValue;
  }

  // `from` in including
  private static void sortAsc(int[] nums, int from) {
    if (from >= nums.length - 1) {
      return;
    }
    quickSort(nums, from, nums.length - 1);
  }

  private static void quickSort(int[] numbers, int start, int end) {
    // 找到一个中间数，小于的放左边，大于的放右边
    if (numbers == null || numbers.length <= 1 || start >= end) {
      return;
    }
    int middleIndex = (start + end) / 2;
    int middleValue = numbers[middleIndex];
    int left = start, right = end;
    while (left <= right) {
      while (numbers[left] < middleValue) {
        left++;
      }
      while (numbers[right] > middleValue) {
        right--;
      }
      if (left <= right) {
        swap(numbers, left, right);
        left++;
        right--;
      }
    }
    quickSort(numbers, start, right);
    quickSort(numbers, left, end);
  }
}
