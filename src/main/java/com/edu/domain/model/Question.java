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
import static java.util.Objects.nonNull;

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
    @Deprecated(forRemoval = true)
    private Question nextQuestion;

    public Question getQuestion(final int index) {
        if (isNull(options) || options.isEmpty()) {
            return nextQuestion;
        } else {
            return options.get(index).getOptionResolver().getNextQuestion();
        }
    }

    public int getNextQuestionSize() {
        return Optional.ofNullable(options).map(List::size).filter(size -> size > 0)
                .orElseGet(() -> nonNull(nextQuestion) ? 1 : 0);
    }

    public int getIndexOfChild(final Question child) {
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).getOptionResolver().getNextQuestion().equals(child)) {
                return i;
            }
        }

        return -1;
    }
}
