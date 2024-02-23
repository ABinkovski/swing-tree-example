package com.edu.infrastructure.ui.model;

import com.edu.domain.exception.NoItemSelected;
import com.edu.domain.model2.Question;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

@RequiredArgsConstructor
public class QuestionActionListener implements ActionListener {

    private final QuestionActionListenerType listenerType;
    private final JTree tree;

    @Override
    public void actionPerformed(final ActionEvent e) {
        try {
            switch (listenerType) {
                case ADD_SIBLING:

                    break;
                case ADD_CHILD:
                    addNewChild();
                    break;

                case DELETE:

                    break;
                default:
                    throw new UnsupportedOperationException(listenerType.name());
            }
        } catch (final NoItemSelected ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void addNewChild() throws NoItemSelected {
        final Question selected = getSelected();
    }

    private void addNewItem(final Question question) {
        question.addQuestion(Question.createNew());
    }

    private Question getSelected() throws NoItemSelected {
        return (Question) Optional.ofNullable(tree.getSelectionModel().getSelectionPath())
                .map(TreePath::getLastPathComponent)
                .orElseThrow(() -> new NoItemSelected("You have to select an Item to continue"));
    }
}
