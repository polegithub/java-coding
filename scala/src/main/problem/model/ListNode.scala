package main.problem.model

/**
  *
  * @author Eric Cheng
  */
class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x

  def descListNode(): String = {
    val current = String.valueOf(this.x)
    if (this.next == null) {
      return current
    }
    return current + "->" + this.next.descListNode()
  }
}

object ListNode {
  def convertIntToListNode(values: Array[Int]): ListNode = {
    val l = new ListNode()
    var temp: ListNode = l
    for (i <- 0 until values.size) {
      val value = values(i)
      temp.x = value
      if (i != values.size - 1) {
        temp.next = new ListNode()
        temp = temp.next
      }
    }
    return l
  }

  def createNext(depth: Int): ListNode = {
    val r = scala.util.Random
    val l = new ListNode(r.nextInt(9))
    if (depth <= 1) {
      return l
    }
    l.next = createNext(depth - 1)
    return l
  }
}