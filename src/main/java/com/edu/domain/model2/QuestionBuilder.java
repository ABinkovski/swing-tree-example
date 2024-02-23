package com.edu.domain.model2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public final class QuestionBuilder {
    private List<Question> questions;
    private String name;
    private String title;
    private Map<String, InputType> inputTypeMap;

    private Question parent;

    private QuestionBuilder() {
    }

    public static QuestionBuilder aQuestion() {
        return new QuestionBuilder();
    }

    public QuestionBuilder questions(List<Question> questions) {
        this.questions = questions;
        return this;
    }

    public QuestionBuilder questions(Question... questions) {
        this.questions = new ArrayList<>(asList(questions));
        return this;
    }

    public QuestionBuilder name(String name) {
        this.name = name;
        return this;
    }

    public QuestionBuilder title(String title) {
        this.title = title;
        return this;
    }


    public QuestionBuilder parent(final Question parent) {
        this.parent = parent;
        return this;
    }

    public QuestionBuilder inputTypeMap(Map<String, InputType> inputTypeMap) {
        this.inputTypeMap = inputTypeMap;
        return this;
    }

    public Question build() {
        return new Question(name, title, inputTypeMap, questions, parent);
    }
}
