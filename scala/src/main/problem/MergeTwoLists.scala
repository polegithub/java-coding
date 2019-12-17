package main.problem

import main.problem.model.ListNode


/**
  *
  * @author Eric Cheng
  */
/**
  * Definition for singly-linked list.
  * class ListNode(var _x: Int = 0) {
  * var next: ListNode = null
  * var x: Int = _x
  * }
  */
object Solution {

  def main(args: Array[String]): Unit = {
    val l1 = ListNode.convertIntToListNode(Array(1, 180))
    val l2 = ListNode.convertIntToListNode(Array(6, 8, 9, 89))
    //    val l2 = null
    //    val l1 = null
    val result = mergeTwoLists(l1, l2)
    if (result != null) {
      println(result.descListNode())
    }
  }

  import scala.collection.mutable.ArrayBuffer

  def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {
    val list1 = convertNodeToList(l1)
    val list2 = convertNodeToList(l2)
    val sortList = sort(list1, list2)
    return convertListToNode(sortList)
  }

  def convertNodeToList(l: ListNode): Array[Int] = {
    if (l == null) {
      return null
    }
    val list: ArrayBuffer[Int] = ArrayBuffer.empty[Int]
    list.append(l.x)
    if (l.next != null) {
      return (list ++ convertNodeToList(l.next)).toArray
    }
    return list.toArray
  }

  def convertListToNode(list: Array[Int]): ListNode = {
    if (list == null || list.isEmpty) {
      return null
    }
    val ln = new ListNode(list(0))
    if (list.size >= 2) {
      val leftList = ArrayBuffer[Int](list: _*)
      leftList.remove(0)
      ln.next = convertListToNode(leftList.toArray)
    }
    return ln
  }

  def sort(l1: Array[Int], l2: Array[Int]): Array[Int] = {
    if (l1 == null || l1.isEmpty) {
      return l2
    }
    if (l2 == null || l2.isEmpty) {
      return l1
    }
    var maxArray: Array[Int] = null
    var minArray: Array[Int] = null
    if (l1.size > l2.size) {
      maxArray = l1
      minArray = l2
    } else {
      maxArray = l2
      minArray = l1
    }
    val sortedArray: ArrayBuffer[Int] = ArrayBuffer(minArray: _*)
    for (valueI <- maxArray) {
      val index = minIndexOf(valueI, sortedArray)
      if (index >= 0 && index <= sortedArray.size) {
        sortedArray.insert(index, valueI)
      }
    }
    return sortedArray.toArray
  }

  def minIndexOf(value: Int, array: ArrayBuffer[Int]): Int = {
    if (array == null || array.isEmpty) {
      return -1
    }
    val index: Int = array match {
      case null => -1
      case a if a.isEmpty => -1
      case a if a.size == 1 => {
        if (a(0) < value) return 1
        0
      }
      case _ => {
        val middleIndex = array.size / 2
        val leftArray = array.slice(0, middleIndex)
        val rightArray = array.slice(middleIndex, array.size)
        value match {
          case v if v > rightArray(0) => minIndexOf(value, rightArray) + leftArray.size
          case v if v < leftArray.last => minIndexOf(value, leftArray)
          case _ => middleIndex
        }
      }
    }
    println("value:" + String.valueOf(value) + ", in array", array, " index is: " + String.valueOf(index))
    return index
  }
}
