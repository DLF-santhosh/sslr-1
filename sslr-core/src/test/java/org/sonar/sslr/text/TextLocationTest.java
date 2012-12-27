/*
 * SonarSource Language Recognizer
 * Copyright (C) 2010 SonarSource
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
package org.sonar.sslr.text;

import org.junit.Test;

import java.io.File;

import static org.fest.assertions.Assertions.assertThat;

public class TextLocationTest {

  @Test
  public void test() {
    File file = new File("foo");
    TextLocation location = new TextLocation(file, 1, 1);
    assertThat(location.getFile()).isSameAs(file);
    assertThat(location.getLine()).isEqualTo(1);
    assertThat(location.getColumn()).isEqualTo(1);
    assertThat(location.equals(location)).isTrue();
    assertThat(location.equals(new Object())).isFalse();
    assertThat(location.equals(new TextLocation(file, 1, 1))).isTrue();
    assertThat(location.equals(new TextLocation(file, 1, 2))).isFalse();
    assertThat(location.equals(new TextLocation(file, 2, 1))).isFalse();
    assertThat(location.equals(new TextLocation(new File("bar"), 1, 1))).isFalse();
    assertThat(location.toString()).isEqualTo("TextLocation{file=foo, line=1, column=1}");
  }

}
