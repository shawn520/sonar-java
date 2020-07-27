package com.iqiyi.demo.sonar;

import java.io.IOException;

/**
 * @author Shawn
 * @date 2020/7/27
 */
public class MethodShouldHaveMatchedExceptionTagCheck {

  /**
   *
   * @throws IOException throw IOException
   */
  public void testWithTag() throws IOException {
    System.out.println("hello");
  }

  /**
   * 测试没有throws标签
   */
  public void testWithOutTag() throws IOException {
    System.out.println("hello");
  }

}
