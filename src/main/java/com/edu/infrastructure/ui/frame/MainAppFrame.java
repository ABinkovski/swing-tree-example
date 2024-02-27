package com.edu.infrastructure.ui.frame;

import com.edu.infrastructure.ui.model.QuestionActionListenerType;
import com.edu.infrastructure.ui.model2.DetailNodePanel;
import com.edu.infrastructure.ui.model2.QuestionModel;
import com.edu.infrastructure.ui.model2.TreeQuestionSelectionListener;
import com.edu.infrastructure.ui.model2.dnd.TreeDragSource;
import com.edu.infrastructure.ui.model2.dnd.TreeDropTarget;
import com.edu.infrastructure.ui.util.JTreeUtils;
import com.edu.infrastructure.ui.util.QuestionFormUtils;
import com.edu.infrastructure.ui.util.WindowUtils;
import com.edu.testdata.TestDataUtils;
import lombok.NonNull;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.dnd.DnDConstants;

import static com.edu.infrastructure.ui.model.QuestionActionListenerType.ADD_CHILD;
import static com.edu.infrastructure.ui.model.QuestionActionListenerType.ADD_SIBLING;
import static com.edu.infrastructure.ui.model.QuestionActionListenerType.DELETE;
import static com.edu.infrastructure.ui.model.QuestionActionListenerType.EXPAND_ALL;

public class MainAppFrame extends JFrame {

    private final JTree jTree;

    private final DetailNodePanel detailPanel;

    private JButton addChildButton;
    private JButton addSiblingButton;
    private JButton removeButton;
    private JButton expandAll;

    private TreeDragSource treeDragSource;
    private TreeDropTarget treeDropTarget;

    public MainAppFrame(final String title) throws HeadlessException {
        super(title);

        add(new JScrollPane(detailPanel = detailPanel()), BorderLayout.EAST);
        add(new JScrollPane(jTree = initTree(detailPanel)), BorderLayout.CENTER);
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
        panel.add(expandAll = createButton(EXPAND_ALL));

        return panel;
    }

    private JTree initTree(@NonNull final DetailNodePanel detailPanel) {
        final QuestionModel questionModel = new QuestionModel();
        questionModel.setRoot(TestDataUtils.getTestQuestionModel());
        final JTree tree = new JTree(questionModel);

        tree.addTreeSelectionListener(new TreeQuestionSelectionListener(detailPanel));

        initDragAndDropCloner(tree);

        JTreeUtils.expand(tree);

        return tree;
    }

    private DetailNodePanel detailPanel() {
        return new DetailNodePanel();
    }

    private void initDragAndDropCloner(final JTree tree) {
//        tree.setDragEnabled(true);
//        tree.setDropMode(DropMode.ON_OR_INSERT);
//        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
        treeDragSource = new TreeDragSource(tree, DnDConstants.ACTION_COPY_OR_MOVE);
        treeDropTarget = new TreeDropTarget(tree);
    }
}
