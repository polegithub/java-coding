package com.company.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Eric Cheng
 */

public class 合并K个排序链表_23 {

  private static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    ListNode[] lists = new ListNode[3];
    lists[0] = randomCreatorListNode(3);
    lists[1] = randomCreatorListNode(4);
    lists[2] = randomCreatorListNode(5);
    ListNode result = mergeKLists3(lists);
    System.out.println(result);
  }

  static ListNode randomCreatorListNode(int length) {
    if (length <= 0) {
      length = 3;
    }
    Random ran = new Random();
    ListNode listNode = new ListNode(ran.nextInt(20));
    ListNode nextNodeRef = listNode;
    for (int i = 0; i < length; ++i) {
      int value = nextNodeRef.val;
      ListNode nextNode = new ListNode(ran.nextInt(4) + value);
      nextNodeRef.next = nextNode;
      nextNodeRef = nextNode;
    }
    return listNode;
  }

  // ---------------------------------- 转成array直接sort -----------------------------------------//

  /**
   * Accepted
   * 131/131 cases passed (11 ms)
   * Your runtime beats 57.97 % of java submissions
   * Your memory usage beats 84.91 % of java submissions (40.3 MB)
   */
  static ListNode mergeKLists3(ListNode[] lists) {
    List<Integer> values = new ArrayList<>();
    for (ListNode listNode : lists) {
      addListToArray(values, listNode);
    }
    Collections.sort(values);
    return convertedByArray(values);
  }

  static void addListToArray(List<Integer> values, ListNode listNode) {
    if (listNode == null) {
      return;
    }
    values.add(listNode.val);
    ListNode nextNode = listNode.next;
    while (nextNode != null) {
      values.add(nextNode.val);
      nextNode = nextNode.next;
    }
  }
  // ---------------------------------- 转成array直接sort -----------------------------------------//

  // ---------------------------------- 按照array的merge -----------------------------------------//

  /**
   * 从第2个开始，按顺序往第一个开始merge
   */
  static ListNode mergeKLists1(ListNode[] lists) {
    List<Integer> values = new ArrayList<>();
    for (ListNode listNode : lists) {
      mergeListToArray(values, listNode);
    }
    return convertedByArray(values);
  }

  /**
   * 两两merge，再循环，再两两merge
   */
  static ListNode mergeKLists2(ListNode[] lists) {
    List<Integer> values = new ArrayList<>();
    int listSize = lists.length;
    if (listSize >= 2) {
      while (listSize > 1) {
        if (listSize % 2 == 0) {
          for (int i = 0; i < listSize; i += 2) {
            //            lists[i / 2] = me
          }
        }
      }
    }
    for (ListNode listNode : lists) {
      mergeListToArray(values, listNode);
    }
    return convertedByArray(values);
  }

  static ListNode convertedByArray(List<Integer> values) {
    if (values == null || values.size() == 0) {
      return null;
    }
    ListNode listNode = null;
    ListNode nextNode = null;
    for (Integer value : values) {
      if (listNode == null) {
        listNode = new ListNode(value);
        nextNode = listNode;
      } else {
        nextNode.next = new ListNode(value);
        nextNode = nextNode.next;
      }
    }
    return listNode;
  }

  /**
   * Accepted
   * 131/131 cases passed (11 ms)
   * Your runtime beats 57.97 % of java submissions
   * Your memory usage beats 81.16 % of java submissions (40.9 MB)
   */
  static List<Integer> mergeListToArray(List<Integer> values, ListNode listNode) {
    if (listNode == null) {
      return values;
    }
    if (values.size() == 0) {
      addListToArray(values, listNode);
    } else {
      ListNode nextNode = listNode.next;
      int lastIndex = insertValueToArray(values, listNode.val, 0, values.size() - 1);
      while (nextNode != null) {
        lastIndex = insertValueToArray(values, nextNode.val, lastIndex, values.size() - 1);
        nextNode = nextNode.next;
      }
    }
    return values;
  }

  static int insertValueToArray(List<Integer> values, int value, int startIndex, int endIndex) {
    if (endIndex < startIndex) {
      return -1;
    }
    if (values.size() == 0) {
      values.add(value);
      return 0;
    }
    if (values.get(endIndex) <= value) {
      values.add(endIndex + 1, value);
      return endIndex + 1;
    }
    if (values.get(startIndex) >= value) {
      values.add(startIndex, value);
      return startIndex;
    }
    if (endIndex - startIndex == 1 && value > values.get(startIndex) && value < values
        .get(endIndex)) {
      values.add(startIndex + 1, value);
      return startIndex + 1;
    }

    int middleIndex = (endIndex + startIndex) / 2;
    if (value > values.get(middleIndex)) {
      return insertValueToArray(values, value, middleIndex, endIndex);
    } else {
      return insertValueToArray(values, value, startIndex, middleIndex);
    }

  }
  // ---------------------------------- 按照array的merge -----------------------------------------//

  // ---------------------------------- 按照listNode的merge -----------------------------------------//

  public ListNode mergeKLists(ListNode[] lists, int start, int end) {
    return null;
  }

  public ListNode mergeLists(ListNode list1, ListNode list2) {
    ListNode result = list1;
    ListNode currentNode1 = list1;
    if (list1 == null)
      return list2;
    if (list2 == null)
      return list1;
    if (list1.val < list2.val) {
      list1.next = mergeLists(list1.next, list2);
      return list1;
    } else {
      list2.next = mergeLists(list1, list2.next);
      return list2;
    }
  }

  private ListNode insertNode(ListNode list, ListNode targetNode) {
    ListNode currentNode = list;
    while (currentNode != null) {
      if (list.val > targetNode.val) {
        targetNode.next = currentNode;
        return targetNode;
      }
      currentNode = currentNode.next;
    }
    currentNode.next = targetNode;
    return list;
  }

  // ---------------------------------- 按照listNode的merge -----------------------------------------//

}
