package com.edu.infrastructure.ui.frame;

import com.edu.infrastructure.ui.model.QuestionActionListenerType;
import com.edu.infrastructure.ui.model2.QuestionModel;
import com.edu.infrastructure.ui.model2.dnd.TreeDragSource;
import com.edu.infrastructure.ui.model2.dnd.TreeDropTarget;
import com.edu.infrastructure.ui.util.JTreeUtils;
import com.edu.infrastructure.ui.util.QuestionFormUtils;
import com.edu.infrastructure.ui.util.WindowUtils;
import com.edu.testdata.TestDataUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;

import static com.edu.infrastructure.ui.model.QuestionActionListenerType.ADD_CHILD;
import static com.edu.infrastructure.ui.model.QuestionActionListenerType.ADD_SIBLING;
import static com.edu.infrastructure.ui.model.QuestionActionListenerType.DELETE;

public class MainAppFrame extends JFrame {

    private final JTree jTree;

    private final JPanel actionPanel;

    private JButton addChildButton;
    private JButton addSiblingButton;
    private JButton removeButton;

    private TreeDragSource treeDragSource;
    private TreeDropTarget treeDropTarget;

    public MainAppFrame(final String title) throws HeadlessException {
        super(title);

        add(new JScrollPane(jTree = initTree()), BorderLayout.CENTER);
        add(actionPanel = initActionPanel(), BorderLayout.EAST);
        add(initButtons(), BorderLayout.SOUTH);

        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        WindowUtils.centerTheFrame(this);

        setVisible(true);
    }

    public JButton createButton(final QuestionActionListenerType type) {
        return QuestionFormUtils.createButton(type, jTree);
    }

    private JPanel initButtons() {
        final JPanel panel = new JPanel();

        panel.add(addChildButton = createButton(ADD_CHILD));
        panel.add(addSiblingButton = createButton(ADD_SIBLING));
        panel.add(removeButton = createButton(DELETE));

        return panel;
    }

    private JTree initTree() {
        final QuestionModel questionModel = new QuestionModel();
        questionModel.setRoot(TestDataUtils.getTestQuestionModel());
        final JTree tree = new JTree(questionModel);

        initDragAndDropCloner(tree);

        JTreeUtils.expand(tree);

        return tree;
    }

    private JPanel initActionPanel() {
        return new JPanel();
    }

    private void initDragAndDropCloner(final JTree tree) {
//        tree.setDragEnabled(true);
//        tree.setDropMode(DropMode.ON_OR_INSERT);
//        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
        treeDragSource = new TreeDragSource(tree, DnDConstants.ACTION_COPY_OR_MOVE);
        treeDropTarget = new TreeDropTarget(tree);
    }
}
