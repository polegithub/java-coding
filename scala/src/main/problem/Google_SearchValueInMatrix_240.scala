package main.problem

/**
  *
  * @author Eric Cheng
  */
object Google_SearchValueInMatrix_240 {
  /**
    * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
    *
    * 每行的元素从左到右升序排列。
    * 每列的元素从上到下升序排列。
    *
    * @param matrix
    * @param target
    * @return
    */
  def searchMatrix(matrix: Array[Array[Int]], target: Int): Boolean = {
    if (matrix == null || matrix.isEmpty) return false
    matrix.flatMap(r => r).contains(target)
  }
}
