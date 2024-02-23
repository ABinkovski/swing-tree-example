package com.edu.domain.model2;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class Question {
    private UUID id = UUID.randomUUID();
    private String name;
    private String title;
    private Map<String, InputType> inputTypeMap = new LinkedHashMap<>();
    private Map<UUID, Question> children = new LinkedHashMap<>();
    private String rule;

    public Question(final String name,
                    final String title,
                    final Map<String, InputType> inputTypeMap,
                    final List<Question> questions) {
        this.name = name;
        this.title = title;
        populateInputTypeMap(inputTypeMap);
        populateQuestions(questions);
    }

    public void populateQuestions(final List<Question> questions) {
        if (Objects.nonNull(questions)) {
            questions.forEach(this::addQuestion);
        }
    }

    public void populateInputTypeMap(final Map<String, InputType> inputTypeMap) {
        if (Objects.nonNull(inputTypeMap)) {
            this.inputTypeMap.putAll(inputTypeMap);
        }
    }

    public void addQuestion(final Question question) {
        children.put(question.getId(), question);
    }

    public Question getChild(final int index) {
        return getChildrenList().get(index);
    }

    public int getChildCount() {
        return children.size();
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public int getIndexOfChild(final Question child) {
        final List<Question> childrenList = getChildrenList();
        for (int i = 0; i < childrenList.size(); i++) {
            if (childrenList.get(i).equals(child)) {
                return i;
            }
        }
        return -1;
    }

    private List<Question> getChildrenList() {
        return new ArrayList<>(children.values()); // TODO double check if order is kept properly
    }

    public static QuestionBuilder builder() {
        return QuestionBuilder.aQuestion();
    }

    public String previewTitle() {
        return String.format("%s: %s", name, title);
    }

    @Deprecated
    public boolean delete(final Question question) {
        return children.values().remove(question);
    }

    @Override
    public String toString() {
        return previewTitle();
    }

    public static Question createNew() {
        return Question.builder().build();
    }
}
