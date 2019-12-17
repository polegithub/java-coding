package main.problem

/**
  *
  * @author Eric Cheng
  */
object Baidu_IntegerReplaceSetpMin_397 {

  // 原始题目传入的是int，但是int.maxValue 会导致 +1 之后变成负数，所以改成long就解决了。
  def integerReplacement(n: Long): Int = {
    println(n)
    if (n == 1) {
      0
    } else if (n == 0 || n == 2) {
      1
    } else if (n == 3) {
      2
    } else if (n % 2 == 0) {
      integerReplacement(n / 2) + 1
    } else {
       if ((n + 1) % 4 == 0) {
        integerReplacement(n + 1) + 1
      } else {
        integerReplacement(n - 1) + 1
      }
    }
  }

  def main(args: Array[String]): Unit = {
    //    println(integerReplacement(3))
    //    println(integerReplacement(65535))
    println(integerReplacement(Int.MaxValue))
    println(integerReplacement(Int.MinValue))
    //    println(integerReplacement(7))
  }
}
