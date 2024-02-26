package com.edu.domain.model2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private UUID id;
    private String name;
    private String title;
    private Map<String, InputType> inputTypeMap = new LinkedHashMap<>();
    private String rule;

    public Question(final String name, final String title) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.title = title;
    }
}
