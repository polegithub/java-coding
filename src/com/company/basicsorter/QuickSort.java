package com.company.basicsorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Eric Cheng
 */
public class QuickSort {

  public static void main(String[] args) {
    startQuickSort();
  }

  private static void startQuickSort() {
    Scanner scan = new Scanner(System.in);
    int count = scan.nextInt();
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      list.add(scan.nextInt());
    }
    if (list.isEmpty()) {
      return;
    }
    int[] nums = list.stream().mapToInt(i -> i).toArray();
    long startTime4 = System.nanoTime();;   //获取开始时间
    quickSort4(nums, 0, list.size() - 1);
    long endTime4 = System.nanoTime();; //获取结束时间
    System.out.println("quickSort4，程序运行时间： " + (endTime4 - startTime4) + "ms");
    System.out.println(Arrays.toString(nums));

    long startTime3 = System.nanoTime();;   //获取开始时间
    List<Integer> sorted = quickSort3(list, 0, list.size() - 1);
    long endTime3 = System.nanoTime();; //获取结束时间
    System.out.println("quickSort4，程序运行时间： " + (endTime3 - startTime3) + "ms");
    for (int value : sorted) {
      System.out.println(value);
    }
  }

  // ----------------------------------startQuickSort4-----------------------------------------//
  private static void quickSort4(int[] nums, int start, int end) {
    if (nums.length == 0 || end <= start) {
      return;
    }
    int middleIndex = dividedIndexOfArray(nums, start, end);
    quickSort4(nums, start, middleIndex - 1);
    quickSort4(nums, middleIndex + 1, end);
  }

  private static int dividedIndexOfArray(int[] nums, int start, int end) {
    if (end <= start) {
      return 0;
    }
    int dividedIndex = start;
    int leftPoint = dividedIndex;
    int rightPoint = end;
    int dividedValue = nums[dividedIndex];

    while (leftPoint < rightPoint) {
      while (leftPoint < rightPoint && nums[rightPoint] >= dividedValue) {
        rightPoint--;
      }
      if (leftPoint < rightPoint) {
        nums[leftPoint] = nums[rightPoint];
        leftPoint++;
      }
      while (leftPoint < rightPoint && nums[leftPoint] <= dividedValue) {
        leftPoint++;
      }
      if (leftPoint < rightPoint) {
        nums[rightPoint] = nums[leftPoint];
        rightPoint--;
      }
    }
    nums[leftPoint] = dividedValue;
    return leftPoint;
  }

  // ----------------------------------startQuickSort4-----------------------------------------//

  // ----------------------------------startQuickSort3(目前几个里最佳)-----------------------------------------//
  // 2019-10-19 17:58:19， 虽然实现了，但是很多细节还是没搞通透，需要再反复练习，反复练习，深入骨髓才行
  private static List<Integer> quickSort3(List<Integer> numbers, int start, int end) {
    // 找到一个中间数，小于的放左边，大于的放右边
    if (numbers == null || numbers.size() <= 1 || start >= end) {
      return numbers;
    }
    int middleIndex = (start + end) / 2;
    int middleValue = numbers.get(middleIndex);
    int left = start, right = end;
    while (left <= right) {
      while (numbers.get(left) < middleValue) {
        left++;
      }
      while (numbers.get(right) > middleValue) {
        right--;
      }
      if (left <= right) {
        swap(numbers, left, right);
        left++;
        right--;
      }
    }
    quickSort3(numbers, start, right);
    quickSort3(numbers, left, end);
    return numbers;
  }

  private static void swap(List<Integer> numbers, int left, int right) {
    int leftValue = numbers.get(left);
    numbers.set(left, numbers.get(right));
    numbers.set(right, leftValue);
  }
  // ----------------------------------startQuickSort3(目前几个里最佳)-----------------------------------------//

  // ----------------------------------startQuickSort2-----------------------------------------//
  private static List<Integer> quickSort2(List<Integer> list) {
    if (list.size() <= 1) {
      return list;
    }
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    int middleIndex = list.size() / 2;
    int middleValue = list.get(middleIndex);
    for (int i = 0; i < list.size(); ++i) {
      if (i != middleIndex) {
        int value = list.get(i);
        if (value < middleValue) {
          left.add(value);
        } else {
          right.add(value);
        }
      }
    }
    left = quickSort2(left);
    right = quickSort2(right);
    left.add(middleValue);
    left.addAll(right);
    return left;
  }
  // ----------------------------------startQuickSort2-----------------------------------------//

  // ----------------------------------startQuickSort1-----------------------------------------//
  static void startQuickSort1() {
    Scanner scan = new Scanner(System.in);
    int count = scan.nextInt();
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      list.add(scan.nextInt());
    }
    if (list.isEmpty()) {
      return;
    }
    quickSort1(list, 0, list.size() - 1);
    for (int value : list) {
      System.out.println(value);
    }
  }

  private static List<Integer> quickSort1(List<Integer> list, int startIndex, int endIndex) {
    if (list.size() <= 1 || startIndex >= endIndex) {
      return list;
    }
    if (endIndex - startIndex == 1) {
      if (list.get(startIndex) > list.get(endIndex)) {
        int end = list.get(endIndex);
        list.set(endIndex, list.get(startIndex));
        list.set(startIndex, end);
      }
      return list;
    }
    List<Integer> leftlist = new ArrayList<>();
    List<Integer> rightlist = new ArrayList<>();
    int middleIndex = (startIndex + endIndex) / 2;
    int middleValue = list.get(middleIndex);
    for (int i = startIndex; i <= endIndex; i++) {
      int value = list.get(i);
      if (value < middleValue) {
        leftlist.add(value);
      } else if (value > middleValue) {
        rightlist.add(value);
      }
    }
    leftlist.add(middleValue);
    leftlist.addAll(rightlist);

    for (int i = startIndex; i <= endIndex; i++) {
      list.set(i, leftlist.get(i - startIndex));
    }
    if (endIndex > startIndex) {
      quickSort1(list, startIndex, middleIndex);
      quickSort1(list, middleIndex, endIndex);
    }
    return list;
  }
  //----------------------------------startQuickSort1-----------------------------------------//
}
