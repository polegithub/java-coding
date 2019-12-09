package com.company.单例;

/**
 * @author Eric Cheng
 */
public class TestMain {

  static final int SHARED_SHIFT = 16;
  static final int SHARED_UNIT = (1 << SHARED_SHIFT);
  static final int MAX_COUNT = (1 << SHARED_SHIFT) - 1;
  static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

  public static void main(String[] args) {
    System.out.println(EXCLUSIVE_MASK);
    System.out.println(-100 & EXCLUSIVE_MASK);
    System.out.println(100 & EXCLUSIVE_MASK);
    System.out.println(-1 & EXCLUSIVE_MASK);
    int shareCount = 4 >>> SHARED_SHIFT;
    System.out.println(shareCount);
    for (int i = 0; i < 3; i++) {
      SingleInstanceByInternalStatic internalStaticIns = SingleInstanceByInternalStatic.share();
      SingleInstanceBySyncLock lockIns = SingleInstanceBySyncLock.share();
      SingleInstanceByStatic staticIns = SingleInstanceByStatic.share();
      SingleInstanceByEnum enumIns = SingleInstanceByEnum.share();
    }
  }

}
