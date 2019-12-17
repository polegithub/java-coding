package main.problem


/**
  *
  * @author Eric Cheng
  */
class MinStack {

  import scala.collection.mutable.ArrayBuffer

  /** initialize your data structure here. */
  var minX: Int = -1
  var stacks: ArrayBuffer[Int] = ArrayBuffer[Int]()

  def push(x: Int) {
    stacks.insert(0, x)
    setMin()
  }

  def pop() {
    if (stacks.size > 0) {
      stacks.remove(0)
      setMin()
    }
  }

  def top(): Int = {
    if (stacks.size > 0) return stacks(0)
    return -1
  }

  def setMin(): Unit = {
    if (stacks.size == 0) return
    var min = stacks(0)
    for (s <- stacks) {
      min = Math.min(min, s)
    }
    minX = min
  }

  def getMin(): Int = {
    if (stacks.size == 0) return -1
    return minX
  }
}

object MinStack {

  def main(args: Array[String]): Unit = {
    test()
  }

  def test(): Unit = {
    val minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    println(minStack.getMin()); //--> 返回 -3.
    minStack.pop();
    println(minStack.top()); // --> 返回 0.
    println(minStack.getMin()); // --> 返回 -2.
  }
}
