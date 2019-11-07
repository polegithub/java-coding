package com.company.basicsorter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Eric Cheng
 */
public class HeapSort {

  /**
   * 堆排序(Heapsort)之Java实现
   * https://blog.csdn.net/kimylrong/article/details/17150475
   */
  public static void main(String[] args) {
    //    int[] array = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3 };
    int[] array = { 9, 6, 5, 3, 11, 10, 1, 2 };

    System.out.println("Before heap:");
    System.out.print(Arrays.toString(array));

    heapSort(array);

    System.out.print("\nAfter heap sort:\n" + Arrays.toString(array));
  }

  public static void heapSort(int[] array) {
    if (array == null || array.length <= 1) {
      return;
    }

    buildMaxHeap(array);
    System.out.print("\nAfter buildMapHeap:\n" + Arrays.toString(array));
    //    List<Integer> list = new ArrayList<>();
    //    parseHeapToList(array, list);
    //    System.out.print("\nparseHeapToList:\n" + list);

    for (int i = array.length - 1; i >= 1; i--) {
      // 每次把最大的取出来，作为数组的最后一个
      swap(array, 0, i);
      // 剩下的重新refresh一下，得到最大的，然后把这个最大的作为倒数第二个，如此循环...
      adjustMaxHeapIfNeed(array, i, 0);
    }
  }

  // build之后，只能确保每个子节点left或者right小于parent，以及最顶部是最大的，不同节点之间是不保证顺序的（最大堆的本质就是这样）
  private static void buildMaxHeap(int[] array) {
    if (array == null || array.length <= 1) {
      return;
    }
    // 因为最后一个节点没有子节点，所以直接从最后一个节点的父节点开始构建，结果是一样的。
    // 可以脑补：
    // 1. 最后一层级的节点，都是：比左大？嗯！，比右大？嗯！，好，返回自己
    // 2. 其他层级的节点，都是：比左大？我比比看...，比右大？我比比看...，比了半天挑最大的返回，而且如果自己不是最大的，还要向上级汇报(递归)
    // 所以最后一层的节点，不用重复了。所以： half = array.length / 2
    int half = array.length / 2;
    for (int i = half; i >= 0; i--) {
      adjustMaxHeapIfNeed(array, array.length, i);
    }
  }

  private static void adjustMaxHeapIfNeed(int[] array, int heapSize, int index) {
    int leftChild = index * 2 + 1;
    int rightChild = index * 2 + 2;

    int largest = index;
    if (leftChild < heapSize && array[leftChild] > array[index]) {
      largest = leftChild;
    }

    if (rightChild < heapSize && array[rightChild] > array[largest]) {
      largest = rightChild;
    }

    if (index != largest) {
      swap(array, index, largest);
      adjustMaxHeapIfNeed(array, heapSize, largest);
    }
  }

  static int parent(int i) {
    return (i - 1) / 2;
  }

  static int leftChild(int i) {
    return i * 2 + 1;
  }

  static int rightChild(int i) {
    return i * 2 + 2;
  }

  private static void parseHeapToList(int[] heap, List<Integer> list) {
    list.add(heap[0]);
    parseHeapToList(heap, list, 0);
  }

  private static void parseHeapToList(int[] heap, List<Integer> list, int startHeapIndex) {
    //    int first = heap[startHeapIndex];
    //    list.add(first);
    int leftChild = leftChild(startHeapIndex);
    int rightChild = rightChild(startHeapIndex);
    if (leftChild < heap.length) {
      list.add(heap[leftChild]);
    }
    if (rightChild < heap.length) {
      list.add(heap[rightChild]);
    }
    if (leftChild < heap.length) {
      parseHeapToList(heap, list, leftChild);
    }
    if (rightChild < heap.length) {
      parseHeapToList(heap, list, rightChild);
    }
  }

  private static void swap(int[] nums, int left, int right) {
    int leftValue = nums[left];
    nums[left] = nums[right];
    nums[right] = leftValue;
  }
}