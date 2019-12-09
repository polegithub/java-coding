package com.company.basicsorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Eric Cheng
 */
public class BasicSorter {

  public static void main(String[] args) {
    int[] a = { 5, 16, 17, 8, 9 };
    int[] b = { 19, 27, 47, 6, 65, 4, 97, 2, 1 };
    selectionSort2(b);
    System.out.println(Arrays.toString(b));
    List<Integer> result = bucketSort(converToList(b), 3);
    System.out.println(result);
  }

  private static List<Integer> converToList(int[] nums) {
    List<Integer> list = new ArrayList<>();
    for (int num : nums) {
      list.add(num);
    }
    return list;
  }

  // 这里省略了index的check
  private static void swap(int[] nums, int left, int right) {
    int leftValue = nums[left];
    nums[left] = nums[right];
    nums[right] = leftValue;
  }

  // 这里省略了index的check
  private static void swap(List<Integer> nums, int left, int right) {
    int leftValue = nums.get(left);
    nums.set(left, nums.get(right));
    nums.set(right, leftValue);
  }

  // 冒泡排序
  private static void bubbleSort(int[] nums) {
    for (int i = 0; i < nums.length; ++i) {
      for (int j = 0; j < nums.length - 1 - i; ++j) {
        if (nums[j] > nums[j + 1]) {
          swap(nums, j, j + 1);
        }
      }
    }
  }

  // 选择排序，最容易想到的版本。
  private static void selectionSort2(int[] nums) {
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        if (nums[i] > nums[j]) {
          swap(nums, j, i);
        }
      }
    }
  }

  // 选择排序
  private static void selectionSort(int[] nums) {
    for (int i = 0; i < nums.length; ++i) {
      int minIndex = i;
      for (int j = i + 1; j < nums.length; ++j) {
        if (nums[j] < nums[minIndex]) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        swap(nums, i, minIndex);
      }
    }
  }

  // 插入排序
  private static void insertSort(int[] nums) {
    for (int i = 1; i < nums.length; ++i) {
      int cur = nums[i];
      int targetIndex = i;
      for (int j = i - 1; j >= 0; --j) {
        if (nums[j] > cur) {
          // 顺次往前挪一位
          nums[j + 1] = nums[j];
          targetIndex = j;
        } else {
          // 找到比他小的，就可以结束战斗了
          break;
        }
      }
      if (targetIndex != i) {
        nums[targetIndex] = cur;
      }
    }
  }

  /**
   * 希尔排序
   * <p>
   * 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
   * 按增量序列个数k，对序列进行k 趟排序；
   * 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
   * 仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
   */
  public static void shellSort(int[] nums) {
    int len = nums.length;
    int temp, gap = len / 2;
    while (gap > 0) {
      for (int i = gap; i < len; i++) {
        temp = nums[i];
        int preIndex = i - gap;
        while (preIndex >= 0 && nums[preIndex] > temp) {
          nums[preIndex + gap] = nums[preIndex];
          preIndex -= gap;
        }
        nums[preIndex + gap] = temp;
      }
      gap /= 2;
    }
  }

  // 归并排序
  private static void mergeSort(int[] nums) {
    if (nums.length == 0) {
      return;
    }
    divideAndMerge(nums, 0, nums.length - 1);
  }

  private static void divideAndMerge(int[] nums, int left, int right) {
    if (right <= left) {
      return;
    }
    // System.out.println(Arrays.toString(nums));
    int middleIndex = (left + right) / 2;
    divideAndMerge(nums, left, middleIndex);
    divideAndMerge(nums, middleIndex + 1, right);
    mergeTwo(nums, left, right);
    // System.out.println(Arrays.toString(nums));
  }

  private static void mergeTwo(int[] nums, int left, int right) {
    // require：左边和右边已经按序
    int[] sorted = new int[nums.length];
    int sortedIndex = left;
    int pointerLeft = left;
    int middleIndex = (left + right) / 2;
    int pointerRight = middleIndex + 1;
    while (pointerLeft <= middleIndex || pointerRight <= right) {
      if (pointerLeft > middleIndex) {
        // 左边的如果取完了，就把右边的剩下的全部赋值
        while (pointerRight <= right) {
          sorted[sortedIndex++] = nums[pointerRight++];
        }
      } else if (pointerRight > right) {
        // 右边的如果取完了，就把左边的剩下的全部赋值
        while (pointerLeft <= middleIndex) {
          sorted[sortedIndex++] = nums[pointerLeft++];
        }
      } else {
        // 谁小用谁
        if (nums[pointerLeft] <= nums[pointerRight]) {
          sorted[sortedIndex++] = nums[pointerLeft++];
        } else {
          sorted[sortedIndex++] = nums[pointerRight++];
        }
      }
    }
    for (int i = left; i <= right; ++i) {
      nums[i] = sorted[i];
    }
  }

  // 快速排序
  private static void quickSort(int[] nums) {
    quickSort(nums, 0, nums.length - 1);
  }

  private static void quickSort(int[] nums, int start, int end) {
    if (end <= start) {
      return;
    }
    int pointerL = start;
    int pointerR = end;
    // 这里取第一个作为参照值，同时把这个值记下来，这样第一个位置就相当于空出来了，可以放别的值
    // 最后的时候，在 `pointerL` 的位置把这个参照值(也是第一个值)填充进去
    int middleValue = nums[start];
    while (pointerL < pointerR) {
      // 顺次，找到右边小于参照值的
      while (pointerL < pointerR && nums[pointerR] >= middleValue) {
        pointerR--;
      }
      if (pointerL < pointerR) {
        // 找到之后，放到左边
        nums[pointerL] = nums[pointerR];
        pointerL++;
      }
      // 顺次，找到左边大于参照值的
      while (pointerL < pointerR && nums[pointerL] <= middleValue) {
        pointerL++;
      }
      if (pointerL < pointerR) {
        // 找到之后，放到右边
        nums[pointerR] = nums[pointerL];
        pointerR--;
      }
    }
    nums[pointerL] = middleValue;
    quickSort(nums, start, pointerL - 1);
    quickSort(nums, pointerL + 1, end);
  }

  // 堆排序
  private static void heapSort(int[] nums) {
    //TODO: 理解了，但需要自己实现1次
  }

  // 计数排序
  private static void countSort(int[] nums) {
    Map<Integer, Integer> numCounter = new HashMap<>();
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      if (numCounter.containsKey(num)) {
        numCounter.put(num, numCounter.get(num) + 1);
      } else {
        numCounter.put(num, 1);
      }
      min = Integer.min(min, num);
      max = Integer.max(max, num);
    }
    int index = 0;
    for (int i = min; i <= max; ++i) {
      if (numCounter.containsKey(i)) {
        for (int j = 0; j < numCounter.get(i); ++j) {
          nums[index] = i;
          index++;
        }
      }
    }
  }

  // 桶排序
  private static List<Integer> bucketSort(List<Integer> nums, int bucketLength) {
    if (nums.size() <= 1) {
      return nums;
    }
    if (nums.size() == 2) {
      if (nums.get(0) > nums.get(1)) {
        swap(nums, 0, 1);
      }
      return nums;
    }
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      min = Integer.min(min, num);
      max = Integer.max(max, num);
    }
    // 这里 + 1， 是因为可能除不尽，所以要加一个桶
    int bucketCount = (max - min) / bucketLength + 1;
    List<List<Integer>> buckets = new ArrayList<>();
    for (int i = 0; i < bucketCount; ++i) {
      buckets.add(new ArrayList<>());
    }
    for (int num : nums) {
      // 这里是关键，分到第几个桶，就是 value / bucketLength
      int bucketIndex = (num - min) / bucketLength;
      List<Integer> bucket = buckets.get(bucketIndex);
      bucket.add(num);
      buckets.set(bucketIndex, bucket);
    }
    List<Integer> sortedResult = new ArrayList<>();
    for (List<Integer> bucket : buckets) {
      if (bucket.size() == 0) {
        continue;
      }
      int subBucketLenght = Integer.min(bucket.size() - 1, bucketLength);
      List<Integer> sortedBucket = bucketSort(bucket, subBucketLenght);
      sortedResult.addAll(sortedBucket);
    }
    return sortedResult;
  }

  // 基数排序：把数字按照位数排序（各位，十位，百位，千位...）

}
