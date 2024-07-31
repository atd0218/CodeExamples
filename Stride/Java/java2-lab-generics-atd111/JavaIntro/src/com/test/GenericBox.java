package com.test;
/**
 * Description: Fully generic class that can be used with any data type but indicates that you must
 * include one when you plan to use the class. 
 * 
 * Since it is a generic class there will only be one stored 
 * 
 * 
 */
public class GenericBox<T> {

  private T t;

  public T getT() {
    return t;
  }

  public void setT(T t) {
    this.t = t;
  }

}
