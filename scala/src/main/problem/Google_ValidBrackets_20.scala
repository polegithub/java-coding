package main.problem

/**
  *
  * @author Eric Cheng
  */
object Google_ValidBrackets_20 {
  val matchedBrackets = List("()", "{}", "[]")

  def isValid(s: String): Boolean = {
    // 注意空字符串可被认为是有效字符串。
    if (s == null || s.isEmpty) return true
    var filteredStr = s
    while (matchedBrackets.count(filteredStr.contains(_)) > 0) {
      for (matchedBracket <- matchedBrackets) {
        filteredStr = filteredStr.replace(matchedBracket, "")
      }
    }
    filteredStr.isEmpty
  }


  //  private def isBracket(s: String): Boolean = {
  //    if (s == null || s.isEmpty) return false
  //    val defaultBrackets = List("(", ")", "{", "}", "[", "]")
  //    defaultBrackets.contains(s)
  //  }
  //
  //  private def rightBracket(left: String): String = {
  //    left match {
  //      case "(" => ")"
  //      case "{" => "}"
  //      case "[" => "]"
  //      case _ => ""
  //    }
  //  }

  def main(args: Array[String]): Unit = {
    println(isValid("{[]}"))
    println(isValid("([)]"))
    println(isValid(""))

  }
}
