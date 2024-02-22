package com.edu.infrastructure.ui.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static com.edu.infrastructure.ui.model.RuleOptionType.SIMPLE_RULE;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class SimpleRuleOptionResolver implements RuleOptionResolver {

    private int priority;

    private Question nextQuestion;

    @Override
    public RuleOptionType getRuleOptionType() {
        return SIMPLE_RULE;
    }

    @Override
    public boolean isApplicable(final String... input) {
        return Boolean.parseBoolean(input[0]);
    }
}
