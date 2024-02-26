package com.edu.domain.model2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {
    private String id;
    private String name;
    private String title;
    private Map<String, InputType> inputTypeMap = new LinkedHashMap<>();
    private String rule;

    public Question(final String name, final String title) {
        this.id = RandomStringUtils.random(6, true, true);
        this.name = name;
        this.title = title;
    }
}
