package com.company.basicsorter;

/**
 * @author Eric Cheng
 */
public class PoHeap {

  public PoHeap(int[] values) {
    this.values = values;
  }

  int[] values;

  int parent(int i) {
    return (i - 1) / 2;
  }

  int leftChild(int i) {
    return i * 2 + 1;
  }

  int rightChild(int i) {
    return i * 2 + 2;
  }

  // 构建堆
  private void allocHeap(int[] nums) {

  }

  // 插入堆
  private void siftUp(int index, int value) {
    if (index > values.length) {
      return;
    }
    values[index] = value;
    while (index != 0) {
      int p = parent(index);
      if (values[p] > values[index]) {
        swap(values, p, index);
      }
      index = p;
    }
  }

  private void siftDown(int index, int value) {
    if (value > values[0]) {
      values[0] = value;
      int p = 0;
      while (p < index) {
        int minChild = leftChild(index);
        if (minChild + 1 < index && values[minChild] > values[minChild + 1])
          minChild++;
        if (minChild < index && values[p] > values[minChild]) {
          swap(values, p, minChild);
          p = minChild;
        } else
          break;
      }
    }
  }

  private static void swap(int[] nums, int left, int right) {
    int leftValue = nums[left];
    nums[left] = nums[right];
    nums[right] = leftValue;
  }
}
