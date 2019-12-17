package main.problem

import main.problem.model.ListNode

/**
  *
  * @author Eric Cheng
  */
object AddTwoNumbers {

  def main(args: Array[String]): Unit = {
    test()
  }

  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    if (isZero(l1)) {
      return l2
    } else if (isZero(l2)) {
      return l1
    }
    val resultNode = new ListNode()
    val xSum = l1.x + l2.x
    resultNode.x = xSum % 10
    resultNode.next = addTwoNumbers(l1.next, l2.next)
    if (xSum / 10 > 0) {
      var default = 0
      if (resultNode.next != null) {
        default = resultNode.next.x
      }
      val carry = new ListNode(xSum / 10)
      resultNode.next = addTwoNumbers(resultNode.next, carry)
    }
    return resultNode
  }

  def isZero(l1: ListNode): Boolean = {
    return l1 == null || (l1.next == null && l1.x == 0)
  }

  def test(): Unit = {
    //    val r = scala.util.Random

    val l1 = ListNode.convertIntToListNode(Array(1))
    val l2 = ListNode.convertIntToListNode(Array(9, 9))
    //    val l1 = new ListNode(r.nextInt(9))
    //    l1.next = createNext(1)
    //    val l2 = new ListNode(r.nextInt(9))
    //    l2.next = createNext(1)
    //    println("l1:")
    //    println(descListNode(l1))
    //    println("l2:")
    //    println(descListNode(l2))
    val l3 = addTwoNumbers(l1, l2)
    println("l3:")
    println(l3.descListNode())
  }
}
