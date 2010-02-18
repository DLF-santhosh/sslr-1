/*
 * Sonar, open source software quality management tool.
 * Copyright (C) 2009 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 */

package com.sonarsource.parser.matcher;

import org.junit.Test;

import static com.sonarsource.parser.matcher.Matchers.and;
import static com.sonarsource.parser.matcher.Matchers.exclusiveTill;

import static org.junit.Assert.assertEquals;

public class ExclusiveTillMatcherTest extends MatcherCase {

  @Test
  public void ok() {
    assertMatch(and(exclusiveTill("four"), "four"), "one", "two", "three", "four");
    assertMatch(and(exclusiveTill("two", "three"), "two", "three", "four"), "one", "two", "three", "four");
  }

  @Test
  public void testToString() {
    assertEquals("(public | private)exclusiveTill", exclusiveTill("public", "private").toString());
  }
}
