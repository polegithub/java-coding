package main.problem

/**
  *
  * @author Eric Cheng
  */
class LRUCache(_capacity: Int) {

  import scala.collection.mutable.{ArrayBuffer, Map}

  val cacheKeys: ArrayBuffer[Int] = ArrayBuffer[Int]()
  val cacheMap: Map[Int, Int] = Map()
  var capacity: Int = _capacity

  def get(key: Int): Int = {
    var result = -1
    if (cacheMap.contains(key)) {
      // update cache priority
      updatePriority(key)
      result = cacheMap(key)

    }
    //    println("key:", key, " result:", result)
    return result
  }

  def put(key: Int, value: Int) {
    if (capacity <= 0) {
      return
    }
    if (cacheKeys.contains(key)) {
      // update cache priority
      updatePriority(key)
    } else {
      cacheKeys.insert(0, key)
    }
    cacheMap.put(key, value)
    if (cacheKeys.size > capacity) {
      val lastKey = cacheKeys(cacheKeys.size - 1)
      cacheKeys.remove(cacheKeys.size - 1)
      cacheMap.remove(lastKey)
    }

    //    println("put new value:", key, value)
    //    println("cachekeys", cacheKeys, " cacheMap", cacheMap)
  }

  def updatePriority(key: Int): Unit = {
    val index = cacheKeys.indexOf(key)
    if (index >= 0) {
      cacheKeys.remove(index)
      cacheKeys.insert(0, key)
    }
  }
}

object LRUCache {
  def main(args: Array[String]): Unit = {
    test2()
  }

  def test1(): Unit = {
    val cache: LRUCache = new LRUCache(2 /* 缓存容量 */)
    cache.put(1, 1)
    cache.put(2, 2)
    cache.get(1) // 返回  1
    cache.put(3, 3) // 该操作会使得密钥 2 作废
    cache.get(2) // 返回 -1 (未找到)
    cache.put(4, 4) // 该操作会使得密钥 1 作废
    cache.get(1) // 返回 -1 (未找到)
    cache.get(3) // 返回  3
    cache.get(4) // 返回  4
  }

  def test2(): Unit = {
    val cache: LRUCache = new LRUCache(2 /* 缓存容量 */)
    cache.put(2, 1)
    cache.put(1, 1)
    cache.put(2, 3)
    cache.put(4, 1)
    cache.get(1) // 返回  -1
    cache.get(2) // 返回 3
  }
}
