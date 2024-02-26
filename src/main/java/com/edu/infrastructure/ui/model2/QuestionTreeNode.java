package com.edu.infrastructure.ui.model2;

import com.edu.domain.model2.InputType;
import com.edu.domain.model2.Question;
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
@Setter
public class QuestionTreeNode extends Question implements TreeNode {

    @Getter
    private QuestionTreeNode parent;
    private Map<UUID, QuestionTreeNode> children = new LinkedHashMap<>();

    public QuestionTreeNode(final String name,
                            final String title,
                            final Map<String, InputType> inputTypeMap,
                            final List<QuestionTreeNode> children,
                            final QuestionTreeNode parent
    ) {
        super(name, title);
        this.parent = parent;
        populateInputTypeMap(inputTypeMap);
        populateChildren(children);
    }


    public void populateChildren(final List<QuestionTreeNode> children) {
        if (nonNull(children)) {
            children.forEach(this::addQuestion);
        }
    }

    public void populateInputTypeMap(final Map<String, InputType> inputTypeMap) {
        if (nonNull(inputTypeMap)) {
            getInputTypeMap().putAll(inputTypeMap);
        }
    }

    public void addQuestion(final QuestionTreeNode question) {
        question.setParent(this); // TODO possibly should be cloned
        children.put(question.getId(), question);
    }

    public QuestionTreeNode getChild(final int index) {
        return getChildrenList().get(index);
    }

    public int getChildCount() {
        return children.size();
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }


    public int getIndexOfChild(final Question child) {
        final List<QuestionTreeNode> childrenList = getChildrenList();
        for (int i = 0; i < childrenList.size(); i++) {
            if (childrenList.get(i).equals(child)) {
                return i;
            }
        }
        return -1;
    }

    public List<QuestionTreeNode> getChildrenList() {
        return new ArrayList<>(children.values()); // TODO double check if order is kept properly
    }

    public String previewTitle() {
        return String.format("%s: %s: %s", getId(), getName(), getTitle());
    }

    public boolean remove(final QuestionTreeNode question) {
        return children.values().remove(question);
    }

    @Override
    public String toString() {
        return previewTitle();
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return getChild(childIndex);
    }

    @Override
    public int getIndex(final TreeNode node) {
        return getIndexOfChild((Question) node);
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return Collections.enumeration(getChildrenList());
    }


    public TreeNode[] getPath() {
        final ArrayList<QuestionTreeNode> objectPath = new ArrayList<>();

        QuestionTreeNode node = this;

        while (nonNull(node)) {
            objectPath.add(node);
            node = node.getParent();
        }

        Collections.reverse(objectPath);

        log.debug("getPathToRoot for [{}], found: [{}]", this, StringUtils.join(objectPath, " -> "));

        return objectPath.toArray(TreeNode[]::new);
    }

    public static QuestionTreeNodeBuilder builder() {
        return QuestionTreeNodeBuilder.aQuestionTreeNode();
    }


    public static QuestionTreeNode createNew(final QuestionTreeNode parent) {
        return QuestionTreeNode.builder().parent(parent).build();
    }
}
