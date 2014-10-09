/*
 * SonarQube Java
 * Copyright (C) 2012 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.java;

import com.google.common.collect.Lists;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.ProjectFileSystem;
import org.sonar.java.model.VisitorsBridge;

import java.io.File;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultJavaResourceLocatorTest {


  private static DefaultJavaResourceLocator javaResourceLocator;

  @BeforeClass
  public static void setup() {
    Project project = mock(Project.class);
    ProjectFileSystem pfs = mock(ProjectFileSystem.class);

    JavaClasspath javaClasspath = mock(JavaClasspath.class);
    when(javaClasspath.getBinaryDirs()).thenReturn(Lists.newArrayList(new File("target/test-classes")));
    when(javaClasspath.getElements()).thenReturn(Lists.newArrayList(new File("target/test-classes")));
    File baseDir = new File("src/test/java");
    when(project.getFileSystem()).thenReturn(pfs);
    when(pfs.getBasedir()).thenReturn(baseDir);
    DefaultJavaResourceLocator jrl = new DefaultJavaResourceLocator(project, javaClasspath);
    JavaAstScanner.scanSingleFile(new File("src/test/java/org/sonar/java/DefaultJavaResourceLocatorTest.java"), new VisitorsBridge(jrl));
    javaResourceLocator = jrl;
  }

  @Test
  public void resource_by_class() throws Exception {
    assertThat(javaResourceLocator.resourcesByClass.keySet()).hasSize(5);
    assertThat(javaResourceLocator.resourcesByClass.keySet()).contains("org/sonar/java/DefaultJavaResourceLocatorTest");
    assertThat(javaResourceLocator.resourcesByClass.keySet()).contains("org/sonar/java/DefaultJavaResourceLocatorTest$A");
    assertThat(javaResourceLocator.resourcesByClass.keySet()).contains("org/sonar/java/DefaultJavaResourceLocatorTest$A$I");
    assertThat(javaResourceLocator.resourcesByClass.keySet()).contains("org/sonar/java/DefaultJavaResourceLocatorTest$A$1B");
    assertThat(javaResourceLocator.resourcesByClass.keySet()).contains("org/sonar/java/DefaultJavaResourceLocatorTest$A$1B$1");
  }

  @Test
  public void class_keys() throws Exception {
    assertThat(javaResourceLocator.classKeys()).hasSize(5);
  }

  @Test
  public void source_file_key_by_class_name() throws Exception {
    assertThat(javaResourceLocator.findSourceFileKeyByClassName("org/sonar/java/DefaultJavaResourceLocatorTest")).endsWith("DefaultJavaResourceLocatorTest.java");
    assertThat(javaResourceLocator.findSourceFileKeyByClassName("org.sonar.java.DefaultJavaResourceLocatorTest")).endsWith("DefaultJavaResourceLocatorTest.java");
  }

  @Test
  public void resource_by_class_name() throws Exception {
    assertThat(javaResourceLocator.findResourceByClassName("org.sonar.java.DefaultJavaResourceLocatorTest")).isNotNull();
    assertThat(javaResourceLocator.findResourceByClassName("org.sonar.java.DumbClassName")).isNull();
  }

  @Test
  public void classpath() throws Exception {
    assertThat(javaResourceLocator.classpath()).hasSize(1);
  }

  @Test
  public void classFilesToAnalyze() throws Exception {
    assertThat(javaResourceLocator.classFilesToAnalyze()).hasSize(5);
  }

  static class A { //NOSONAR

    interface I {
      void foo();
    }

    private void method() {
      class B {
        Object obj = new I() {
          @Override
          public void foo() {

          }
        };
      }
    }
  }
}