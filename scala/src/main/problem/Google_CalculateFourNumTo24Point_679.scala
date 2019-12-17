package main.problem


/**
  *
  * @author Eric Cheng
  */
object Google_CalculateFourNumTo24Point_679 {
  /*
 * @lc app=leetcode.cn id=679 lang=scala
 *
 * [679] 24点游戏
 */

  /** 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
    *
    * @param nums
    * @return
    */

  import math.abs
  import scala.collection.mutable.ListBuffer

  def judgePoint24(nums: Array[Int]): Boolean = {
    enableToValue(nums.map(_.toDouble), 24)
  }

  /**
    * 几个易错点：
    * 1. 思路是先取2个，然后求和，和剩下的递归
    * 2. 函数处理的时候，只有1个或者2个的情况，直接处理掉，效果最好，不要放到大一统的函数里
    * 3. double的判断不能 == ,而应该用 求差 < 1e-4,(或者类似0.00001)
    *
    * @param nums
    * @param target
    * @return
    */
  def enableToValue(nums: Array[Double], target: Int): Boolean = {
    if (nums == null || nums.isEmpty) return false
    if (nums.length == 1) {
      abs(nums(0) - target) < 0.0001
    } else if (nums.length == 2) {
      addSubMultiplicationValue(nums(0), nums(1)).count(p => abs(p - target) < 0.0001) > 0
    } else {
      for (i <- 0 until nums.size) {
        for (j <- 0 until nums.size) {
          if (i != j) {
            val enable = addSubMultiplicationValue(nums(i), nums(j)).count({ p =>
              val leftVlues: Array[Double] = nums.zipWithIndex.filterNot({ r => r._2 == i || r._2 == j }).map(_._1) :+ p
              enableToValue(leftVlues, target)
            }) > 0
            if (enable) {
              return true
            }
          }
        }
      }
      false
    }
  }

  def addSubMultiplicationValue(firstValue: Double, secondValue: Double): List[Double] = {
    val list: ListBuffer[Double] = ListBuffer[Double](secondValue + firstValue, secondValue - firstValue, firstValue - secondValue, secondValue * firstValue)
    if (secondValue > Double.MinPositiveValue) {
      list += (firstValue / secondValue)
    }
    if (firstValue > Double.MinPositiveValue) {
      list += (secondValue / firstValue)
    }
    list.toList
  }

  def main(args: Array[String]): Unit = {
    println(judgePoint24(Array(3, 8, 3, 8)))
  }

}
