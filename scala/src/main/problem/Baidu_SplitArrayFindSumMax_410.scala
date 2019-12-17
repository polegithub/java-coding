package main.problem


/**
  *
  * @author Eric Cheng
  */
object Baidu_SplitArrayFindSumMax_410 {
  /**
    * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
    *
    * @param nums
    * @param m
    * @return
    */
  def splitArray(nums: Array[Int], m: Int): Int = {
    if (nums.isEmpty || m == 0 || m > nums.size) {
      0
    } else if (m == 1) {
      nums.sum
    } else if (m == nums.size) {
      nums.max
    } else {
      var right = nums.sum
      var left = nums.max

      while (left < right) {
        val middle = (left + right) / 2
        if (findAvailableSeparatedArray(nums, m, middle)) {
          right = middle
        } else {
          left = middle + 1
        }
      }
      left
    }
  }

  def findAvailableSeparatedArray(nums: Array[Int], m: Int, sum: Int): Boolean = {
    var packageIndex = 0
    var packageSum = 0
    // 因为要求分成连续的子数组，所以直接按顺序加就好了，如果不要求连续，那复杂度就大很多了
    for (num <- nums) {
      packageSum += num
      if (packageSum > sum) {
        packageIndex += 1
        packageSum = num
      }
      if (packageIndex > (m - 1)) {
        return false
      }
    }
    true
  }


  def main(args: Array[String]): Unit = {
    val result = splitArray(Array(2, 9, 3, 7, 8, 1), 3)
    println(result)
  }
}
