package com.edu.infrastructure.ui.model;

public interface RuleOptionResolver {

    int getPriority();

    RuleOptionType getRuleOptionType();

    Question isApplicable(String... input);

}
