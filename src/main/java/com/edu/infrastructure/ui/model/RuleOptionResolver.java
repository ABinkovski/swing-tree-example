package com.edu.infrastructure.ui.model;

public interface RuleOptionResolver {

    int getPriority();

    RuleOptionType getRuleOptionType();

    boolean isApplicable(String... input);

    Question getNextQuestion();

}
