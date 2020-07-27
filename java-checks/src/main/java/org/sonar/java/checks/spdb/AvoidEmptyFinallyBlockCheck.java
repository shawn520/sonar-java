package org.sonar.java.checks.spdb;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

/**
 * @author Shawn
 * @date 2020/7/27
 */
@Rule(key = "S8004")
public class AvoidEmptyFinallyBlockCheck extends BaseTreeVisitor implements JavaFileScanner {
  private static final String MESSAGE = "Avoid use empty finally block.";
  private JavaFileScannerContext context;

  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;
    scan(context.getTree());
  }

  @Override
  public void visitTryStatement(TryStatementTree tree) {
    scan(tree.resourceList());
    scan(tree.block());
    scan(tree.catches());
    BlockTree finallyBlock = tree.finallyBlock();
    if (finallyBlock != null && finallyBlock.body().isEmpty()) {
      context.reportIssue(this, tree, MESSAGE);
    }
  }

}
