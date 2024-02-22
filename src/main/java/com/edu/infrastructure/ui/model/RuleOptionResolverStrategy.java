package com.edu.infrastructure.ui.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RuleOptionResolverStrategy {

    private final Map<RuleOptionType, RuleOptionResolver> resolvers;

    public RuleOptionResolverStrategy(final List<RuleOptionResolver> resolvers) {
        this.resolvers = resolvers.stream()
                .collect(Collectors.toMap(RuleOptionResolver::getRuleOptionType, ruleOptionResolver -> ruleOptionResolver));
    }

    public RuleOptionResolver getRuleOptionResolver(final RuleOptionType optionType) {
        return resolvers.get(optionType);
    }
}
