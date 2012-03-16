/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.sslr.impl.matcher;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.impl.BacktrackingEvent;
import com.sonar.sslr.impl.ParsingState;
import com.sonar.sslr.impl.events.ParsingEventListener;

public abstract class Matcher {

  public Matcher[] children;

  protected Matcher(Matcher... children) {
    this.children = children;
  }

  public final boolean isMatching(ParsingState parsingState) {
    return matchToIndex(parsingState) >= 0;
  }

  public final int matchToIndex(ParsingState parsingState) {
    int indexBeforeStarting = parsingState.lexerIndex;

    try {
      match(parsingState);
      return parsingState.lexerIndex;
    } catch (BacktrackingEvent re) {
      return -1;
    } finally {
      parsingState.lexerIndex = indexBeforeStarting;
    }
  }

  public abstract AstNode match(ParsingState parsingState);

  protected final void enterEvent(ParsingState parsingState) {
    if (parsingState.parsingEventListeners != null) {
      if (this instanceof RuleMatcher) {
        /* Fire the enterRule event */
        for (ParsingEventListener listener : parsingState.parsingEventListeners) {
          listener.enterRule((RuleMatcher) this, parsingState);
        }
      } else {
        /* Fire the enterMatcher event */
        for (ParsingEventListener listener : parsingState.parsingEventListeners) {
          listener.enterMatcher(this, parsingState);
        }
      }
    }
  }

}
