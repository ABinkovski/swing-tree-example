package com.edu.infrastructure.ui.model2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

@Slf4j
@RequiredArgsConstructor
public class TreeQuestionSelectionListener implements TreeSelectionListener {

    private final DetailNodePanel detailPanel;

    @Override
    public void valueChanged(final TreeSelectionEvent e) {
        final QuestionTreeNode selected = (QuestionTreeNode) ((JTree) e.getSource()).getLastSelectedPathComponent();
        log.debug("Selected: {}", selected);

        detailPanel.fillDetails(selected);
    }
}
