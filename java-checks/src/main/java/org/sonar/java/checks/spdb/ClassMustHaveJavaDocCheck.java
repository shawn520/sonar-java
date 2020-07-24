package org.sonar.java.checks.spdb;

import org.sonar.check.Rule;
import org.sonar.java.checks.helpers.Javadoc;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;

/**
 * @author Shawn
 * @date 2020/7/23
 */
@Rule(key = "S8000")
public class ClassMustHaveJavaDocCheck extends BaseTreeVisitor implements JavaFileScanner {

  private JavaFileScannerContext context;
  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;
    scan(context.getTree());
  }

  @Override
  public void visitClass(ClassTree tree) {
    Javadoc javadoc = new Javadoc(tree);
    if(javadoc.noMainDescription()) {
      context.reportIssue(this, tree, "Class and Interface must have Javadoc.");
    }
    super.visitClass(tree);
  }
}
