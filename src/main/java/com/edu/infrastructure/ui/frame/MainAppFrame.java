package com.edu.infrastructure.ui.frame;

import com.edu.infrastructure.ui.model2.QuestionModel;
import com.edu.infrastructure.ui.util.WindowUtils;
import com.edu.testdata.TestDataUtils;

import javax.swing.*;
import java.awt.*;

public class MainAppFrame extends JFrame {

    final JTree jTree;

    final JPanel actionPanel;

    public MainAppFrame(final String title) throws HeadlessException {
        super(title);

        add(new JScrollPane(jTree = initTree()), BorderLayout.CENTER);
        add(actionPanel = initActionPanel(), BorderLayout.EAST);

        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        WindowUtils.centerTheFrame(this);

        setVisible(true);
    }

    private JTree initTree() {
        final QuestionModel questionModel = new QuestionModel();
        questionModel.setRoot(TestDataUtils.getTestQuestionModel());
        final JTree tree = new JTree(questionModel);

//        JTreeUtils.expand(tree);

        return tree;
    }

    private JPanel initActionPanel() {
        return new JPanel();
    }
}
