package com.edu.testdata;

import com.edu.domain.model2.Question;
import lombok.experimental.UtilityClass;

import static java.util.Collections.singletonList;

@UtilityClass
public class TestDataUtils {

    public static Question getTestQuestionModel() {
        final Question root = Question.builder()
                .name("person")
                .title("Please, enter Your Name")
                .questions(singletonList(Question.builder()
                        .name("sex")
                        .title("Please enter Your sex")
                        .questions(singletonList(Question.builder()
                                .name("age")
                                .title("Please enter Your age")
                                .build()))
                        .build()))
                .build();

        return root;
    }

}
