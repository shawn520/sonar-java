package org.sonar.java.checks.spdb;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * @author Shawn
 * @date 2020/7/24
 */
public class ClassMustHaveJavaDocCheckTest {
  @Test
  void test() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/checks/spdb/ClassMustHaveJavaDocCheck.java")
      .withCheck(new ClassMustHaveJavaDocCheck())
      .verifyIssues();
  }
}
