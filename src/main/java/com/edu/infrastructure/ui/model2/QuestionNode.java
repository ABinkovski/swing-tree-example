package com.edu.infrastructure.ui.model2;

import com.edu.domain.model2.InputType;
import com.edu.domain.model2.Question;

import javax.swing.tree.TreeNode;
import java.util.List;
import java.util.Map;


public class QuestionNode extends Question implements TreeNode {

    public QuestionNode(String name, String title, Map<String, InputType> inputTypeMap, List<Question> questions, Question parent) {
        super(name, title, inputTypeMap, questions, parent);
    }

    // TODO split Question's business logic and infrastructure
}
