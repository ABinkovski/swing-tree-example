package com.edu.infrastructure.ui.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RuleOption {
    private String option;
    private String input;
    private int score;
    private RuleOptionType type;

}
