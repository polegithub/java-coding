package main.problem

import main.problem.model.TreeNode

/**
  *
  * @author Eric Cheng
  */
object LongestUnivalueTreeNodePath {
  def longestUnivaluePath(root: TreeNode): Int = {
    if (root == null) return 0
    val leftPath = longestUnivaluePath(root.left)
    val rightPath = longestUnivaluePath(root.right)
    if (isBalanceTreeNode(root)) {
      return Math.max(leftPath, rightPath) + 1
    } else {
      return Math.max(leftPath, rightPath)
    }
  }

  def isBalanceTreeNode(treeNode: TreeNode): Boolean = {
    return treeNode != null && treeNode.left != null && treeNode.right != null && treeNode.left.value == treeNode.right.value
  }

  def main(args: Array[String]): Unit = {
    val treeNode = TreeNode.create(6)
    println(treeNode.description())
    val path = longestUnivaluePath(treeNode)
    println(path)
  }
}
