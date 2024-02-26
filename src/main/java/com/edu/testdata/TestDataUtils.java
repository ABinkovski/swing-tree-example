package com.edu.testdata;

import com.edu.infrastructure.ui.model2.QuestionTreeNode;
import lombok.experimental.UtilityClass;

import static java.util.Collections.singletonList;

@UtilityClass
public class TestDataUtils {

    public static QuestionTreeNode getTestQuestionModel() {
        final QuestionTreeNode nextQuestionPart = QuestionTreeNode.builder()
                .name("driving_license")
                .title("Do you have Driving license?")
                .children()
                .build();

        final QuestionTreeNode root = QuestionTreeNode.builder()
                .name("person")
                .title("Please, enter Your Name")
                .children(singletonList(QuestionTreeNode.builder()
                        .name("sex")
                        .title("Please enter Your sex")
                        .children(singletonList(QuestionTreeNode.builder()
                                .name("age")
                                .title("Please enter Your age")
                                .children(QuestionTreeNode.builder()
                                        .name("pets")
                                        .title("Do You have Pets?")
                                        .children(QuestionTreeNode.builder()
                                                        .name("pet_kind")
                                                        .title("What kind of pet do you have?")
                                                        .children(QuestionTreeNode.builder()
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
