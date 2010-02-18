/*
 * Sonar, open source software quality management tool.
 * Copyright (C) 2009 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 */

package com.sonarsource.parser.matcher;

import com.sonarsource.lexer.Token;

public class TokenValueMatcher extends TokenMatcher {

  private String tokenValue;

  public TokenValueMatcher(String tokenValue) {
    this(tokenValue, false);
  }

  public TokenValueMatcher(String tokenValue, boolean hasToBeSkippedFromAst) {
    super(hasToBeSkippedFromAst);
    this.tokenValue = tokenValue;
  }

  public String toString() {
    return tokenValue;
  }

  @Override
  protected boolean isExpectedToken(Token token) {
    return tokenValue.equals(token.getValue());
  }

}
