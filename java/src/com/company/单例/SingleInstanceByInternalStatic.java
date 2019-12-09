package com.company.单例;

/**
 * @author Eric Cheng
 */
public class SingleInstanceByInternalStatic {

  private static class InternalOwner {

    static SingleInstanceByInternalStatic instance = new SingleInstanceByInternalStatic();
  }

  private SingleInstanceByInternalStatic() {
    System.out.println(SingleInstanceByInternalStatic.class.getName() + " init");
  }

  public static SingleInstanceByInternalStatic share() {
    SingleInstanceByInternalStatic me = new InternalOwner().instance;
    return me;
  }
}
