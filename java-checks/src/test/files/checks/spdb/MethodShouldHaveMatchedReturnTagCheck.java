public class MethodShouldHaveMatchedReturnTagCheckTest {
  /**
   *
   * @return result;
   */
  public String testWithReturnTag() {
    return "result";
  }

  // Noncompliant
  public String testWithOutReturnTag() {
    return null;
  }
}
