package main.problem

import main.problem.model.TreeNode

import scala.collection.mutable.ListBuffer

/**
  *
  * @author Eric Cheng
  */

object Google_ConvertTreeToList_257 {
  /*
   * @lc app=leetcode.cn id=257 lang=scala
   *
   * [257] 二叉树的所有路径
   */
  def binaryTreePaths(root: TreeNode): List[String] = {
    if (root == null) return List()
    val left = binaryTreePaths(root.left)
    val right = binaryTreePaths(root.right)
    val result = ListBuffer[String]()
    if (left.nonEmpty) {
      result.appendAll(left.map(root.value.toString + "->" + _))
    }
    if (right.nonEmpty) {
      result.appendAll(right.map(root.value.toString + "->" + _))
    }
    if (result.isEmpty) {
      result.append(root.value.toString)
    }
    result.toList
  }

  def main(args: Array[String]): Unit = {
    val test1 = TreeNode.create(4)
    binaryTreePaths(test1).foreach(println)
  }
}
