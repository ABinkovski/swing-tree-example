package com.edu.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RuleOptionType {
    SIMPLE_RULE(false, false);

    private final boolean inputProcessing;
    private final boolean multipleChoice;
}
