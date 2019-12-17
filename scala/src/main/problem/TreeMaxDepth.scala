package main.problem

import main.problem.model.TreeNode

/**
  *
  * @author Eric Cheng
  */
object TreeMaxDepth {
  def maxDepth(root: TreeNode): Int = {
    root match {
      case null => 0
      case r if isEmpty(r) => 1
      case _ => Math.max(maxDepth(root.left), maxDepth(root.right)) + 1
    }
  }

  def isEmpty(treeNode: TreeNode): Boolean = {
    return (treeNode.left == null && treeNode.right == null)
  }

  val r = scala.util.Random

  def main(args: Array[String]): Unit = {
    for (_ <- 0 until 10) {
      test()
    }
  }

  def test(): Unit = {
    val depth = r.nextInt(10)
    println("source depth:", depth)
    val treeNode: TreeNode = TreeNode.create(depth)
    println("maxDepth:", maxDepth(treeNode))
    assert(depth == maxDepth(treeNode))
  }
}
