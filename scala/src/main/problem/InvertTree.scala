package main.problem

import main.problem.model.TreeNode

/**
  *
  * @author Eric Cheng
  */
object InvertTree {
  def invertTree(root: TreeNode): TreeNode = {
    if (root == null) return null
    invertTree(root.left)
    invertTree(root.right)
    if (root.left != null && root.right != null) {
      val sourceLeft = root.left
      root.left = root.right
      root.right = sourceLeft
    } else if (root.left != null) {
      root.right = root.left
      root.left = null
    } else {
      root.left = root.right
      root.right = null
    }
    root
  }

  def main(args: Array[String]): Unit = {
    val treeNode = TreeNode.create(5)
    println("source:")
    println(treeNode.description())
    val invert: TreeNode = invertTree(treeNode)
    println("inverted:")
    println(invert.description())
  }
}
