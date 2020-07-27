package com.iqiyi.demo.sonar;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Shawn
 * @date 2020/7/27
 */
public class AvoidEmptyFinallyBlockCheck {

  public void testWithEmptyFinallyBlock() {
    try{
      System.out.println("hello");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {

    }
  }

  public void test2() {
    try{
      System.out.println("hello");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      return;
    }
  }
}
