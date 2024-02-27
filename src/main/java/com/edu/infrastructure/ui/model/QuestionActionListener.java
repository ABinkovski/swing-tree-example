package com.edu.infrastructure.ui.model;

import com.edu.domain.exception.ChildNotFoundException;
import com.edu.domain.exception.ItemIsParent;
import com.edu.domain.exception.NoItemSelected;
import com.edu.infrastructure.ui.model2.QuestionModel;
import com.edu.infrastructure.ui.model2.QuestionTreeNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import static java.util.Objects.isNull;

@Slf4j
@RequiredArgsConstructor
public class QuestionActionListener implements ActionListener {

    private final QuestionActionListenerType listenerType;
    private final JTree tree;

    @Override
    public void actionPerformed(final ActionEvent e) {
        try {
            switch (listenerType) {
                case ADD_SIBLING:
                    addNewSibling();
                    break;
                case ADD_CHILD:
                    addNewChild();
                    break;

                case DELETE:
                    deleteItem();
                    break;

                default:
                    throw new UnsupportedOperationException(listenerType.name());
            }
        } catch (final NoItemSelected | ItemIsParent | ChildNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void addNewChild() throws NoItemSelected {
        log.debug("Adding new child");
        final QuestionTreeNode selected = getSelected();
        addNewItem(selected);
    }

    private void addNewSibling() throws NoItemSelected, ItemIsParent {
        log.debug("Adding new sibling");
        final QuestionTreeNode selected = getSelectedParent();
        addNewItem(selected);
    }

    private void deleteItem() throws NoItemSelected, ItemIsParent, ChildNotFoundException {
        log.debug("Deleting an item");
        final QuestionTreeNode selected = getSelected();
        final QuestionTreeNode parent = getSelectedParent();
        final QuestionModel model = (QuestionModel) tree.getModel();
        if (!model.removeChild(parent, selected)) {
            throw new ChildNotFoundException(parent, selected);
        }
        expandToChild(parent);
    }

    private void addNewItem(final QuestionTreeNode question) {
        final QuestionModel model = (QuestionModel) tree.getModel();
        final QuestionTreeNode aNew = QuestionTreeNode.createNew(question); // TODO Input Dialog
        model.addChild(question, aNew);

        expandToItem(aNew);
    }

    private QuestionTreeNode getSelectedParent() throws NoItemSelected, ItemIsParent {
        final TreePath selectionPath = tree.getSelectionModel().getSelectionPath();
        return getSelectedParent(selectionPath);
    }

    private QuestionTreeNode getSelectedParent(final TreePath selectionPath) throws NoItemSelected, ItemIsParent {
        if (isNull(selectionPath)) {
            throw new NoItemSelected();
        }
        final int parentIndex = selectionPath.getPathCount() - 2;
        if (parentIndex < 0) {
            throw new ItemIsParent();
        }

        return (QuestionTreeNode) selectionPath.getPath()[parentIndex];
    }

    private QuestionTreeNode getSelected() throws NoItemSelected {
        return (QuestionTreeNode) Optional.ofNullable(tree.getSelectionModel().getSelectionPath())
                .map(TreePath::getLastPathComponent)
                .orElseThrow(NoItemSelected::new);
    }

    private void expandToChild(final QuestionTreeNode parent) {
        final QuestionTreeNode question = parent.getChildCount() > 0 ? parent.getChild(0) : parent;
        expandToItem(question);
    }

    private void expandToItem(final QuestionTreeNode question) {
        final QuestionModel model = (QuestionModel) tree.getModel();
        final TreeNode[] pathToRoot = question.getPath();
        final TreePath treePath = new TreePath(pathToRoot);
        tree.scrollPathToVisible(treePath);
        tree.setSelectionPath(treePath);
    }
}
