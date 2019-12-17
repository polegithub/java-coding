package main.problem

import scala.collection.mutable

/**
  *
  * @author Eric Cheng
  */
object Google_RemoveLineMakeGraphToTree_684 {

  /**
    * 这里借用的题目的潜规则： 每组都是从左到右，从小到大的
    * @param edges
    * @return
    */
  def findRedundantConnection(edges: Array[Array[Int]]): Array[Int] = {
    val treeMap: mutable.HashMap[Int, Int] = mutable.HashMap()
    for (edge <- edges) {
      val left = getRoot(edge(0), treeMap)
      val right = getRoot(edge(1), treeMap)
      if (left == right) {
        return edge
      }
      treeMap(left) = right
      println("start")
      treeMap foreach (x => println (x._1 + "-->" + x._2))
      println("end")
    }
    Array()
  }

  def getRoot(key: Int, treeMap: mutable.HashMap[Int, Int]): Int = {
    if (!treeMap.contains(key)) return key
    val root = getRoot(treeMap(key), treeMap)
    treeMap(key) = root
    println(key, "-->", root)
    root
  }


  /**
    * 失败结果，不对的
    *
    * @param edges
    * @return
    */
  def findRedundantConnection2(edges: Array[Array[Int]]): Array[Int] = {
    if (edges == null || edges.isEmpty) return Array()
    for (i <- (0 until edges.size).reverse) {
      println("start", edges(i)(0), edges(i)(1))
      val left = edges.zipWithIndex.filter(_._2 != i).map(_._1)
      val enable = enableBeTree(left)
      println("end: ", enable)
      if (enable) {
        return edges(i)
      }
    }
    return Array()
  }

  def enableBeTree(edges: Array[Array[Int]]): Boolean = {
    if (edges.size == 0) {
      false
    } else if (edges.size == 1) {
      true
    } else if (edges.size == 2) {
      edges(0).count(edges(1).contains(_)) == 1
    } else {
      val others = edges.tail
      val flattedOthers = others.flatMap(r => r)
      val enable = enableBeTree(others)
      println("result for others: ")
      others.foreach(_.foreach(r => print(r + " ")))
      println("enable: ", enable, "是否包含: ", edges(0)(0), edges(0)(1))
      val including1 = flattedOthers.contains(edges(0)(0))
      val including2 = flattedOthers.contains(edges(0)(1))
      enable && (including1 ^ including2) || (including1 && including2)
    }
  }

  def main(args: Array[String]): Unit = {
    val test1 = findRedundantConnection(Array(Array(1, 2), Array(2, 3), Array(3, 4), Array(1, 4), Array(1, 5)))
    test1.foreach(println)
  }
}
