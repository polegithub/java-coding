package main.problem

/**
  *
  * @author Eric Cheng
  */
object Baidu_LongestSubstringWithRepeatKChar_395 {
  def longestSubstring(s: String, k: Int): Int = {
    val strLengths = separateAndFind(s, k).map(_.length)
    if (strLengths.isEmpty) 0 else strLengths.max
  }

  def separateAndFind(s: String, k: Int): Array[String] = {
    if (s == null || s.isEmpty) Array("")
    val invalidKeys = s.foldLeft(Map[Char, Int]() withDefaultValue 0) { (acc, c) => acc updated(c, acc(c) + 1) }
      .filter(_._2 < k)
      .keys
      .toArray
    if (invalidKeys.isEmpty) {
      Array(s)
    } else {
      s.split(invalidKeys)
        .filter(_.length > 0)
        .map(separateAndFind(_, k))
        .flatMap(r => r)
    }
  }

  def main(args: Array[String]): Unit = {
    val test1 = longestSubstring("中国中国中国我我我我abeddf", 2)
    println("test1 max length： ", test1)
  }
}
