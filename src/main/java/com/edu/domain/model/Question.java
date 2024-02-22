package com.edu.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

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

    public Question getQuestion(final int index) {
        if (isNull(options) || options.isEmpty()) {
            return nextQuestion;
        } else {
            return options.get(index).getOptionResolver().getNextQuestion();
        }
    }

    public int getNextQuestionSize() {
        return Optional.ofNullable(options).map(List::size).filter(size -> size > 1).orElse(1);
    }
}
