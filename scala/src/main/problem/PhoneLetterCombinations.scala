package main.problem

/**
  *
  * @author Eric Cheng
  */
object PhoneLetterCombinations {
  /*
   * @lc app=leetcode.cn id=17 lang=scala
   *
   * [17] 电话号码的字母组合
   */
  def main(args: Array[String]): Unit = {
    val testStrings = List("3", "45", "190", "1", null)
    for (testString <- testStrings) {
      println(testString)
      println(letterCombinations(testString))
    }
  }

  def letterCombinations(digits: String): List[String] = {
    if (digits == null || digits.isEmpty) {
      return List()
    }
    var result = List[String]()
    for (char <- digits.toCharArray) {
      val chars = releationMap(char.toString)
      result = append(result, chars)
    }
    return result
  }

  def releationMap(digit: String): Array[Char] = {
    digit match {
      case "2" => "abc".toCharArray
      case "3" => "def".toCharArray
      case "4" => "ghi".toCharArray
      case "5" => "jkl".toCharArray
      case "6" => "mno".toCharArray
      case "7" => "pqrs".toCharArray
      case "8" => "tuv".toCharArray
      case "9" => "wxyz".toCharArray
      case _ => null
    }
  }

  def append(string: String, chars: Array[Char]): List[String] = {
    return chars.map(x => string + x.toString).toList
  }

  def append(list: List[String], chars: Array[Char]): List[String] = {
    if (chars == null) return list
    if (list.isEmpty) {
      return append("", chars)
    } else {
      return list.map(r => append(r, chars)).flatMap(r => r)
    }
  }
}
