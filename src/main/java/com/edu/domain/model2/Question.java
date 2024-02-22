package com.edu.domain.model2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Question {
    private UUID id = UUID.randomUUID();
    private String name;
    private Map<String, InputType> inputTypeMap = new LinkedHashMap<>();
    private Map<UUID, Question> children = new LinkedHashMap<>();
    private String rule;

    public Question(final String name) {
        this.name = name;
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
}
