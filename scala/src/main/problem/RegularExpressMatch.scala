package main.problem

/**
  *
  * @author Eric Cheng
  */
object RegularExpressMatch {

  /**
    * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
    *
    * '.' 匹配任意单个字符。
    * '*' 匹配零个或多个前面的元素。
    */
  var matchIndex: Int = 0
  var lastStartChar: Char = _

  def isMatch(s: String, p: String): Boolean = {
    if (isAllNull(s, p)) return true
    if (isNorNull(s, p)) {
      // 有点复杂，以后再写 2019-03-03 22:59:51
      //      if (isAllPowerful(p)) {
      //        return true
      //      }
      val sourceChars = s.toList
      val matchChars = p.toList
      for (soueceChar <- sourceChars) {
        if (!matchChar(soueceChar, matchChars)) {
          return false
        }
      }
    }
    // s or p is empty/null
    return false
  }

  def isAllPowerful(p: String): Boolean = {
    return p == ".*"
  }

  def matchChar(c: Char, matchList: List[Char]): Boolean = {
    for (i <- matchIndex to matchList.size) {
      val mChar = matchList(i)
      lastStartChar = mChar
      if (c.equals(mChar)){
        return true
      }

    }
    return false
  }

  def isNorNull(s: String, p: String): Boolean = {
    return s != null && s.nonEmpty && p != null && p.nonEmpty
  }

  def isAllNull(s: String, p: String): Boolean = {
    return (s == null || s.isEmpty) && (p == null || p.isEmpty)
  }
}
