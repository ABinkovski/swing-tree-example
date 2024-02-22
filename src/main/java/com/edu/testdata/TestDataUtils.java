package com.edu.testdata;

import com.edu.domain.model2.Question;
import lombok.experimental.UtilityClass;

import static java.util.Collections.singletonList;

@UtilityClass
public class TestDataUtils {

    public static Question getTestQuestionModel() {
        final Question nextQuestionPart = Question.builder()
                .name("driving_license")
                .title("Do you have Driving license?")
                .questions()
                .build();

        final Question root = Question.builder()
                .name("person")
                .title("Please, enter Your Name")
                .questions(singletonList(Question.builder()
                        .name("sex")
                        .title("Please enter Your sex")
                        .questions(singletonList(Question.builder()
                                .name("age")
                                .title("Please enter Your age")
                                .questions(Question.builder()
                                        .name("pets")
                                        .title("Do You have Pets?")
                                        .questions(Question.builder()
                                                        .name("pet_kind")
                                                        .title("What kind of pet do you have?")
                                                        .questions(Question.builder()
                                                                .name("pet_name")
                                                                .title("What is his/her name?")
                                                                .build())
                                                        .build(),
                                                nextQuestionPart
                                        )
                                        .build())
                                .build()))
                        .build()))
                .build();


        return root;
    }

}
