package com.edu.infrastructure.ui.model2;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Slf4j
public class DetailNodePanel extends JPanel {

    private final Map<DetailPanelElement, Component> formElements;

    private JPanel elementsPanel;
    private JPanel buttonPanel;

    private JButton saveButton;

    public DetailNodePanel() {
        formElements = new LinkedHashMap<>();
        elementsPanel = new JPanel();
        buttonPanel = new JPanel();
        saveButton = (JButton) buttonPanel.add(new JButton("Save"));
        saveButton.setEnabled(false);
        saveButton.addActionListener(event -> {
            // TODO implement save changes
        });

        setLayout(new GridLayout(2, 1));
        add(elementsPanel);
        add(buttonPanel);

        setPreferredSize(new Dimension(300, 0));
    }

    public void fillDetails(final QuestionTreeNode questionTreeNode) {
        if (isNull(questionTreeNode)) {
            log.debug("Skipping: value is null");
        } else {
            log.debug("Showing details for: {}", questionTreeNode.getPreviewTitle());
            addTextField(DetailPanelElement.ID, questionTreeNode.getId());
            addTextField(DetailPanelElement.NAME, questionTreeNode.getName());
            addTextField(DetailPanelElement.TITLE, questionTreeNode.getTitle());
            addTextField(DetailPanelElement.RULE, questionTreeNode.getRule());

            refillFormElements();
        }
    }

    private void addTextField(final DetailPanelElement element, final String value) {
        final JTextField textField = new JTextField(value);
        textField.setEditable(element.isEditable());
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                saveButton.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saveButton.setEnabled(true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                saveButton.setEnabled(true);
            }
        });

        formElements.put(element, textField);
    }

    private void refillFormElements() {
        removeAllElementsFromForm();
        elementsPanel.setLayout(new GridLayout(formElements.size(), 2));
        formElements.forEach((element, component) -> {
            elementsPanel.add(new JLabel(element.getLabel()));
            elementsPanel.add(component);
        });

        getRootPane().revalidate();
    }

    private void removeAllElementsFromForm() {
        Stream.of(elementsPanel.getComponents())
                .forEach(elementsPanel::remove);
    }

    public void setText(final DetailPanelElement element, final String value) {
        if (element.getAClass().isInstance(JTextComponent.class)) {
            final JTextComponent textComponent = (JTextComponent) formElements.get(element);
            textComponent.setText(value);
        }
    }
}
