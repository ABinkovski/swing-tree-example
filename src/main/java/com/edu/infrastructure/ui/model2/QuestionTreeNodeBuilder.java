package com.edu.infrastructure.ui.model2;

import com.edu.domain.model2.InputType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;

public final class QuestionTreeNodeBuilder {
    private String id;
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

    public QuestionTreeNodeBuilder id(final String id) {
        this.id = id;
        return this;
    }

    public QuestionTreeNodeBuilder rule(final String rule) {
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

    public QuestionTreeNodeBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public QuestionTreeNodeBuilder title(final String title) {
        this.title = title;
        return this;
    }

    public QuestionTreeNodeBuilder inputTypeMap(final Map<String, InputType> inputTypeMap) {
        this.inputTypeMap = inputTypeMap;
        return this;
    }

    public QuestionTreeNodeBuilder parent(final QuestionTreeNode parent) {
        this.parent = parent;
        return this;
    }

    public QuestionTreeNode build() {
        final QuestionTreeNode questionTreeNode = new QuestionTreeNode(name, title, inputTypeMap, children, parent);
        if (nonNull(id)) {
            questionTreeNode.setId(id);
        }
        questionTreeNode.setRule(rule);
        return questionTreeNode;
    }
}
