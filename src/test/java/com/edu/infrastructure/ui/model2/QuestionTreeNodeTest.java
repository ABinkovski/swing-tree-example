package com.edu.infrastructure.ui.model2;


import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTreeNodeTest {

    @Test
    void shouldBuildTree() {
        final QuestionTreeNode root = QuestionTreeNode.builder()
                .name("root_name")
                .children(singletonList(QuestionTreeNode.builder()
                        .name("singleton_list")
                        .children(
                                QuestionTreeNode.builder()
                                        .name("level_2_1")
                                        .build(),
                                QuestionTreeNode.builder()
                                        .name("level_2_2")
                                        .build()
                        )
                        .build()))
                .build();

        assertThat(root.getName()).isEqualTo("root_name");
        assertThat(root.getChildCount()).isEqualTo(1);

        final QuestionTreeNode second = root.getChild(0);
        assertThat(second.getName()).isEqualTo("singleton_list");
        assertThat(second.getChildCount()).isEqualTo(2);

        assertThat(second.getChildrenList())
                .extracting(QuestionTreeNode::getName)
                .containsExactly("level_2_1", "level_2_2");
    }
}