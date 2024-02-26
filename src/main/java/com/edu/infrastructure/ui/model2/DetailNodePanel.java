package com.edu.infrastructure.ui.model2;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Slf4j
public class DetailNodePanel extends JPanel {

    private final Map<DetailPanelElement, Component> formElements;

    public DetailNodePanel() {
        formElements = new LinkedHashMap<>();
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
        formElements.put(element, textField);
    }

    private void refillFormElements() {
        removeAllElementsFromForm();
        setLayout(new GridLayout(formElements.size(), 2));
        formElements.forEach((element, component) -> {
            add(new JLabel(element.getLabel()));
            add(component);
        });
    }

    private void removeAllElementsFromForm() {
        Stream.of(getComponents())
                .forEach(this::remove);
    }

    public void setText(final DetailPanelElement element, final String value) {
        if (element.getAClass().isInstance(JTextComponent.class)) {
            final JTextComponent textComponent = (JTextComponent) formElements.get(element);
            textComponent.setText(value);
        }
    }
}
