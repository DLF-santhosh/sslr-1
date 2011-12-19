/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.sslr.dsl.condition;

import static com.sonar.sslr.impl.matcher.GrammarFunctions.Standard.*;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Rule;
import com.sonar.sslr.dsl.calculator.CalculatorDsl;

public class ConditionDsl extends Grammar {

  public Rule condition;
  public Rule primaryCondition;
  public Rule logicalAnd;
  public Rule logicalOr;
  public Rule equal;
  public Rule notEqual;
  public Rule booleanValue;
  public Rule expression = new CalculatorDsl().expression;

  public ConditionDsl() {
    equal.is(expression, or("=", "equals"), expression).plug(Equal.class);
    notEqual.is(expression, or(and("!", "="), and("not", "equals")), expression).plug(NotEqual.class);
    primaryCondition.is(booleanValue).plug(PrimaryCondition.class);
    logicalAnd.is(or(primaryCondition, equal, notEqual), opt(or("and", and("&", "&")), logicalAnd)).skipIfOneChild();
    logicalAnd.plug(LogicalAnd.class);
    logicalOr.is(logicalAnd, opt(or("or", and("|", "|")), logicalOr)).skipIfOneChild();
    logicalOr.plug(LogicalOr.class);
    condition.is(logicalOr).plug(Condition.class);

    booleanValue.isOr("true", "false").plug(Boolean.class);
  }

  @Override
  public Rule getRootRule() {
    return condition;
  }
}
