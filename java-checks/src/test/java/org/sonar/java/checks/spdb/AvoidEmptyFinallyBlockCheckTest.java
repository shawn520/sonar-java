package org.sonar.java.checks.spdb;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * @author Shawn
 * @date 2020/7/27
 */
public class AvoidEmptyFinallyBlockCheckTest {
  @Test
  void test() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/checks/spdb/AvoidEmptyFinallyBlockCheck.java")
      .withCheck(new AvoidEmptyFinallyBlockCheck())
      .verifyIssues();
  }
}
