class MethodMustHaveJavaDocCommentCheck {
  // Noncompliant
  public class MethodMustHaveJavaDocCommentCheck {
    void test() {}
  }

  /**
   * Detail info
   * @author Shawn
   * @date 2020/7/23
   */
  public void test2() {
    // ...
  }

}

