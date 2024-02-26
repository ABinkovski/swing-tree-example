package com.edu.infrastructure.ui.model2;

import com.edu.domain.model2.InputType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Arrays.asList;

public final class QuestionTreeNodeBuilder {
    private UUID id;
    private String rule;
    private List<QuestionTreeNode> children;
    private String name;
    private String title;
    private Map<String, InputType> inputTypeMap;
    private QuestionTreeNode parent;

    private QuestionTreeNodeBuilder() {
    }

    public static QuestionTreeNodeBuilder aQuestionTreeNode() {
        return new QuestionTreeNodeBuilder();
    }

    public QuestionTreeNodeBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public QuestionTreeNodeBuilder rule(String rule) {
        this.rule = rule;
        return this;
    }

    public QuestionTreeNodeBuilder children(final List<QuestionTreeNode> children) {
        this.children = children;
        return this;
    }

    public QuestionTreeNodeBuilder children(final QuestionTreeNode... children) {
        this.children = new ArrayList<>(asList(children));
        return this;
    }

    public QuestionTreeNodeBuilder name(String name) {
        this.name = name;
        return this;
    }

    public QuestionTreeNodeBuilder title(String title) {
        this.title = title;
        return this;
    }

    public QuestionTreeNodeBuilder inputTypeMap(Map<String, InputType> inputTypeMap) {
        this.inputTypeMap = inputTypeMap;
        return this;
    }

    public QuestionTreeNodeBuilder parent(QuestionTreeNode parent) {
        this.parent = parent;
        return this;
    }

    public QuestionTreeNode build() {
        QuestionTreeNode questionTreeNode = new QuestionTreeNode(name, title, inputTypeMap, children, parent);
        questionTreeNode.setId(id);
        questionTreeNode.setRule(rule);
        return questionTreeNode;
    }
}