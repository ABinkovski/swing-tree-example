package com.edu.domain.model2;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable, Cloneable {
    private String id;
    private String name;
    private String title;
    private Map<String, InputType> inputTypeMap = new LinkedHashMap<>();
    private String rule;

    public Question(final String name, final String title) {
        this.id = generateId();
        this.name = name;
        this.title = title;
    }

    protected String generateId() {
        return RandomStringUtils.random(6, true, true);
    }


    @Override
    @Deprecated
    protected Object clone() {
        return Question.builder()
                .id(generateId())
                .name(getName())
                .title(getTitle())
                .inputTypeMap(new LinkedHashMap<>(getInputTypeMap()))
                .rule(getRule())
                .build();
    }
}
