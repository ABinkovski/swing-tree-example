package com.edu.infrastructure.ui.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Question {
    private String question;
    private Map<String, String> answers;
    private int score = 1;
    private List<RuleOption> options;
    private Question nextQuestion;
}
