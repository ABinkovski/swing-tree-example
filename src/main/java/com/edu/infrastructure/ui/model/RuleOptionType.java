package com.edu.infrastructure.ui.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RuleOptionType {
    SIMPLE_RULE(false, false);

    private final boolean inputProcessing;
    private final boolean multipleChoice;
}
