package main.problem


/**
  *
  * @author Eric Cheng
  */
object FindMedianSortedArrays {

  import scala.collection.mutable.ArrayBuffer

  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    if (nums1 == null) {
      return findMedianSortedArray(nums2)
    }
    if (nums2 == null) {
      return findMedianSortedArray(nums1)
    }
    val merged = merge(nums1, nums2)
    return findMedianSortedArray(merged)
  }

  def findMedianSortedArray(nums: Array[Int]): Double = {
    if (nums == null || nums.isEmpty) {
      return -1
    }
    if (nums.size == 1) {
      return nums.head
    }
    val middleIndex = nums.size / 2
    if (nums.size % 2 == 0) {
      val left = nums(middleIndex - 1)
      val right = nums(middleIndex)
      return (left + right) / 2.0
    } else {
      return nums(middleIndex)
    }
  }

  def merge(l1: Array[Int], l2: Array[Int]): Array[Int] = {
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
    //    println("value:" + String.valueOf(value) + ", in array", array, " index is: " + String.valueOf(index))
    return index
  }

  def main(args: Array[String]): Unit = {
    val median = findMedianSortedArrays(Array(), Array(1))
    println("findMedianSortedArrays:", median)
  }
}
