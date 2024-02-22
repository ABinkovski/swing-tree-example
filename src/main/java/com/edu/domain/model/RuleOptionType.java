package com.edu.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RuleOptionType {
    SINGLE_RULE(false, false),
    SIMPLE_RULE(false, false);

    private final boolean inputProcessing;
    private final boolean multipleChoice;
}
