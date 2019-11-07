package com.company.单例;

/**
 * @author Eric Cheng
 */
public class SingleInstanceBySyncLock {

  /**
   * 由于指令重排优化的存在，导致初始化Singleton和将对象地址赋给instance字段的顺序是不确定的
   * volatile的一个语义是禁止指令重排序优化，也就保证了instance变量被赋值的时候对象已经是初始化过的，从而避免了上面说到的问题
   */
  private static volatile SingleInstanceBySyncLock me = null;

  private SingleInstanceBySyncLock() {
    System.out.println(SingleInstanceBySyncLock.class.getName() + " init");
  }

  /**
   * 双重校验锁即实现了延迟加载，又解决了线程并发问题，同时还解决了执行效率问题
   *
   * @return SingleInstanceBySyncLock
   */
  public static SingleInstanceBySyncLock share() {
    if (me == null) {
      synchronized (SingleInstanceBySyncLock.class) {
        // 这里再判断1次是为了：避免多线程同时满足 me == null,同时开始new
        if (me == null) {
          me = new SingleInstanceBySyncLock();
        }
      }
    }
    return me;
  }
}
