package main.problem

/**
  *
  * @author Eric Cheng
  */
object SingleNumber {

  import scala.collection.mutable.Map

  def singleNumber(nums: Array[Int]): Int = {
    val numMap: Map[Int, Boolean] = Map()
    for (num <- nums) {
      updateCouple(num, numMap)
    }
    if (numMap.size == 1) {
      return numMap.keys.head
    }
    return -1
  }

  def updateCouple(num: Int, numMap: Map[Int, Boolean]): Unit = {
    if (numMap == null) {
      return
    }
    if (numMap.contains(num)) {
      numMap.remove(num)
    } else {
      numMap.put(num, true)
    }
  }

  def main(args: Array[String]): Unit = {
    val result = singleNumber(Array())
    println(result)
  }
}
