package com.edu.domain.model;

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
    private int score;
    private RuleOptionResolver optionResolver;

}
