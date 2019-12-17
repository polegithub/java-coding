package main.problem.model

import java.util.ArrayList

/**
  *
  * Definition for a binary tree node.
  *
  * @author Eric Cheng
  */
import scala.collection.JavaConversions._

class TreeNode(var _value: Int) {
  var value: Int = _value
  var left: TreeNode = null
  var right: TreeNode = null

  def description(includingSelf: Boolean = true): String = {
    //    var desc = ""
    //    if (includingSelf) {
    //      desc = String.valueOf(value)
    //    }
    //    if (left != null && right != null) {
    //      desc = desc + "\n left: " + left.value + " right: " + right.value
    //      desc = desc + "\n left: " + left.description(false) + " right: " + right.description(false)
    //    } else {
    //      if (left != null) {
    //        desc = desc + "\n left: " + left.description()
    //      } else if (right != null) {
    //        desc = desc + "\n right: " + right.description()
    //      }
    //    }
    //    return desc
    val trees: ArrayList[ArrayList[Integer]] = TreeNode.convertToArray(this)
//    trees.forEach(println)
    for (tree <- trees.toList){
      println(tree)
    }
    return ""
  }

  //  def toArray(): Array[Array[Int]] = {
  //    val arrayBuffer: ArrayBuffer[Array[Int]] = ArrayBuffer()
  //    arrayBuffer.append(Array(value))
  //    if (left != null && right != null) {
  //      var leftArray: Array[Array[Int]] = left.toArray()
  //      var rightArray: Array[Array[Int]] = right.toArray()
  //    } else {
  //      if (left != null) {
  //        desc = desc + "\n left: " + left.description()
  //      } else if (right != null) {
  //        desc = desc + "\n right: " + right.description()
  //      }
  //    }
  //  }
}

object TreeNode {
  val r = scala.util.Random

  def create(depth: Int): TreeNode = {
    depth match {
      case d if (d <= 0) => null
      case 1 => new TreeNode(r.nextInt(10) - r.nextInt(10))
      case _ => {
        val treeNode: TreeNode = new TreeNode(r.nextInt(10) - r.nextInt(10))
        r.nextInt(3) match {
          //          case 0 => treeNode.left = create(depth - 1)
          //          case 1 => treeNode.right = create(depth - 1)
          case _ => {
            treeNode.left = create(depth - 1)
            treeNode.right = create(depth - 1)
          }
        }
        treeNode
      }
    }
  }

  import java.util

  def convertToArray(pRoot: TreeNode): util.ArrayList[util.ArrayList[Integer]] = {
    val res = new util.ArrayList[util.ArrayList[Integer]]
    if (pRoot == null) return res
    var temp = new util.ArrayList[Integer]
    val layer = new util.LinkedList[TreeNode]()
    layer.offer(pRoot)
    var start = 0
    var end = 1
    while ( {
      !layer.isEmpty
    }) {
      val node = layer.poll
      temp.add(node.value)
      start += 1
      if (node.left != null) layer.add(node.left)
      if (node.right != null) layer.add(node.right)
      if (start == end) {
        start = 0
        res.add(temp)
        temp = new util.ArrayList[Integer]
        end = layer.size
      }
    }
    res
  }
}
