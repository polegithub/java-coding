package com.company.单例;

/**
 * @author Eric Cheng
 */
public class SingleInstanceByEnum {

  private static enum InternalType {
    DEFAULT;
    private SingleInstanceByEnum instance;

    private InternalType() {
      instance = new SingleInstanceByEnum();
      System.out.println(SingleInstanceByEnum.class.getName() + " init");
    }
  }

  public static SingleInstanceByEnum share() {
    return InternalType.DEFAULT.instance;
  }

}
