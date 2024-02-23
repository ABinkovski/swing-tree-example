package com.edu.domain.model2;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Slf4j
@Getter
@Setter
public class Question implements TreeNode {
    private UUID id = UUID.randomUUID();
    private String name;
    private String title;
    private Map<String, InputType> inputTypeMap = new LinkedHashMap<>();
    private Map<UUID, Question> children = new LinkedHashMap<>();
    private Question parent;
    private String rule;

    public Question(final String name,
                    final String title,
                    final Map<String, InputType> inputTypeMap,
                    final List<Question> questions,
                    final Question parent
    ) {
        this.name = name;
        this.title = title;
        this.parent = parent;
        populateInputTypeMap(inputTypeMap);
        populateQuestions(questions);
    }

    public void populateQuestions(final List<Question> questions) {
        if (nonNull(questions)) {
            questions.forEach(this::addQuestion);
        }
    }

    public void populateInputTypeMap(final Map<String, InputType> inputTypeMap) {
        if (nonNull(inputTypeMap)) {
            this.inputTypeMap.putAll(inputTypeMap);
        }
    }

    public void addQuestion(final Question question) {
        question.setParent(this); // TODO deep clone
        children.put(question.getId(), question);
    }

    public Question getChild(final int index) {
        return getChildrenList().get(index);
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return getChild(childIndex);
    }

    public int getChildCount() {
        return children.size();
    }

    @Override
    public int getIndex(final TreeNode node) {
        return getIndexOfChild((Question) node);
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return Collections.enumeration(getChildrenList());
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

    public boolean remove(final Question question) {
        return children.values().remove(question);
    }

    public TreeNode[] getPath() {
        final ArrayList<Question> objectPath = new ArrayList<>();

        Question node = this;

        while (nonNull(node)) {
            objectPath.add(node);
            node = node.getParent();
        }

        Collections.reverse(objectPath);

        log.debug("getPathToRoot for {}, found: {}", this, StringUtils.join(objectPath, " -> "));

        return objectPath.toArray(TreeNode[]::new);
    }

    @Override
    public String toString() {
        return previewTitle();
    }

    public static Question createNew(final Question parent) {
        return Question.builder().parent(parent).build();
    }
}
