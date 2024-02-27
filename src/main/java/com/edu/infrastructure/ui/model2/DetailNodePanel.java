package com.edu.infrastructure.ui.model2;

import com.edu.domain.exception.EmptyTextException;
import com.edu.infrastructure.ui.ExceptionPanes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
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
        saveButton.addActionListener(getSaveButtonListener());

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
        textField.getDocument().addDocumentListener(getTextChangeListener());

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
        if (isTextComponent(element)) {
            final JTextComponent textComponent = (JTextComponent) formElements.get(element);
            textComponent.setText(value);
        }
    }

    private DocumentListener getTextChangeListener() {
        return new DocumentListener() {
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
        };
    }

    private ActionListener getSaveButtonListener() {
        return event -> {
            try {
                validateInput();

                // TODO save changes
            } catch (final EmptyTextException e) {
                log.error(e.getMessage(), e);
                ExceptionPanes.showValidationException(e, getRootPane());
            }
        };
    }

    private void validateInput() throws EmptyTextException {
        final List<String> invalidInputs = formElements.entrySet()
                .stream()
                .filter(this::isTextComponent)
                .filter(this::isEmptyNNTextComponent)
                .map(Entry::getKey)
                .map(DetailPanelElement::getLabel)
                .collect(Collectors.toList());

        if (!invalidInputs.isEmpty()) {
            throw new EmptyTextException(invalidInputs);
        }
    }


    private boolean isEmptyNNTextComponent(final Entry<DetailPanelElement, Component> entry) {
        return !entry.getKey().isNullable() && StringUtils.isBlank(((JTextComponent) entry.getValue()).getText());
    }

    private boolean isTextComponent(final Entry<DetailPanelElement, Component> entry) {
        return isTextComponent(entry.getKey());
    }

    private boolean isTextComponent(final DetailPanelElement element) {
        // TODO find more elegant solution if possible
        return JTextComponent.class.isAssignableFrom(element.getAClass());
    }
}
