package main.problem


/**
  *
  * @author Eric Cheng
  */
object FindDisappearedNumbers {

  import scala.collection.mutable.ListBuffer

  def findDisappearedNumbers(nums: Array[Int]): List[Int] = {
    val list = new ListBuffer[Int]()
    for (source <- 1 to nums.size) {
      if (!nums.contains(source)) list.append(source)
    }
    return list.toList
  }

  def main(args: Array[String]): Unit = {
    val result = findDisappearedNumbers(Array(1, 3, 5, 6, 1, 1))
    println(result)
  }
}
