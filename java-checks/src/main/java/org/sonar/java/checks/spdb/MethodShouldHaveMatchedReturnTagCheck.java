package org.sonar.java.checks.spdb;

import org.sonar.check.Rule;
import org.sonar.java.checks.helpers.Javadoc;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.TypeTree;

/**
 * @author Shawn
 * @date 2020/7/24
 */
@Rule(key = "S8002")
public class MethodShouldHaveMatchedReturnTagCheck extends BaseTreeVisitor implements JavaFileScanner {
  private static final String MESSAGE = "Method should have matched @Return Tag Javadoc comment.";
  private JavaFileScannerContext context;
  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;
    scan(context.getTree());
  }

  @Override
  public void visitMethod(MethodTree tree) {
    Javadoc javadoc = new Javadoc(tree);
    TypeTree typeTree = tree.returnType();
    if(!"void".equals(typeTree.symbolType().name())
      && javadoc.noReturnDescription()) {
      context.reportIssue(this, tree, MESSAGE);
    }
    super.visitMethod(tree);
  }
}