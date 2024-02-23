package com.edu.infrastructure.ui.model;

import com.edu.domain.exception.ChildNotFoundException;
import com.edu.domain.exception.ItemIsParent;
import com.edu.domain.exception.NoItemSelected;
import com.edu.domain.model2.Question;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import static java.util.Objects.isNull;

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
        final Question selected = getSelected();
        addNewItem(selected);
    }

    private void addNewSibling() throws NoItemSelected, ItemIsParent {
        final Question selected = getSelectedParent();
        addNewItem(selected);
    }

    private void deleteItem() throws NoItemSelected, ItemIsParent, ChildNotFoundException {
        final Question selected = getSelected();
        final Question parent = getSelectedParent();
        if (!parent.delete(selected)) {
            throw new ChildNotFoundException(parent, selected);
        }
    }

    private void addNewItem(final Question question) {
        question.addQuestion(Question.createNew());
    }

    private Question getSelectedParent() throws NoItemSelected, ItemIsParent {
        final TreePath selectionPath = tree.getSelectionModel().getSelectionPath();
        if (isNull(selectionPath)) {
            throw new NoItemSelected();
        }
        final int parentIndex = selectionPath.getPathCount() - 2;
        if (parentIndex < 0) {
            throw new ItemIsParent();
        }

        return (Question) selectionPath.getPath()[parentIndex];
    }

    private Question getSelected() throws NoItemSelected {
        return (Question) Optional.ofNullable(tree.getSelectionModel().getSelectionPath())
                .map(TreePath::getLastPathComponent)
                .orElseThrow(NoItemSelected::new);
    }
}
