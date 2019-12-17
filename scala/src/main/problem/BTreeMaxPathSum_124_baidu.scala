package main.problem

import main.problem.model.TreeNode

/**
  *
  * @author Eric Cheng
  */
/*
 * @lc app=leetcode.cn id=124 lang=scala
 *
 * [124] 二叉树中的最大路径和
 */


object BTreeMaxPathSum_124_baidu {

  import scala.math.max

  var result = Int.MinValue

  def maxPathSum(root: TreeNode): Int = {
    result = Int.MinValue
    calculateMax(root)
    result
  }

  /**
    * 计算指定teeNode的最大子节点+根节点的和（因为最终结果是连成一条线，所以这个必须包含根节点）
    *
    * @param root
    * @return
    */
  def calculateMax(root: TreeNode): Int = {
    if (root == null) return 0
    val left = max(0, calculateMax(root.left))
    val right = max(0, calculateMax(root.right))
    val currentMax = left + right + root.value
    //  result 在每一步都计算下，当场场景的最佳结果，默默地记下来
    result = max(result, currentMax)
    max(left, right) + root.value
  }


  def main(args: Array[String]): Unit = {
    test()
  }

  def test(): Unit = {
    val test1 = TreeNode.create(3)
    test1.description()
    val result1 = maxPathSum(test1)
    println(result1)
  }
}
