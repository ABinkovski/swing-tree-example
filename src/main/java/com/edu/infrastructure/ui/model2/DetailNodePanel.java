package com.edu.infrastructure.ui.model2;

import com.edu.domain.exception.EmptyTextException;
import com.edu.infrastructure.ui.exception.ExceptionPanes;
import com.edu.infrastructure.ui.util.FormUtils;
import com.edu.infrastructure.ui.util.JTreeUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
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

    @Setter
    private JTree tree;

    private JPanel elementsPanel;
    private JPanel buttonPanel;

    private JButton saveButton;

    private QuestionTreeNode lastSelectedNode;

    public DetailNodePanel() {
        formElements = new LinkedHashMap<>();
        elementsPanel = new JPanel();
        buttonPanel = new JPanel();
        saveButton = (JButton) buttonPanel.add(new JButton("Save"));
        saveButton.setEnabled(false);
        saveButton.setVisible(false);
        saveButton.addActionListener(getSaveButtonListener());

        setLayout(new GridLayout(2, 1));
        add(elementsPanel);
        add(buttonPanel);

        setPreferredSize(new Dimension(300, 0));
    }

    public void processNode(final QuestionTreeNode questionTreeNode) {
        if (isNull(questionTreeNode)) {
            log.debug("Skipping: value is null");
            cleanDetails();
        } else {
            fillDetails(questionTreeNode);
            refillFormElements();
        }
    }

    private void cleanDetails() {
        log.debug("Clear all elements from details view");
        lastSelectedNode = null;
        saveButton.setVisible(false);
        formElements.clear();
        removeAllElementsFromForm();
    }

    private void fillDetails(final QuestionTreeNode questionTreeNode) {
        lastSelectedNode = questionTreeNode;

        log.debug("Showing details for: {}", questionTreeNode.getPreviewTitle());

        saveButton.setVisible(true);
        saveButton.setEnabled(false);

        addTextField(DetailPanelElement.ID, questionTreeNode.getId());
        addTextField(DetailPanelElement.NAME, questionTreeNode.getName());
        addTextField(DetailPanelElement.TITLE, questionTreeNode.getTitle());
        addTextField(DetailPanelElement.RULE, questionTreeNode.getRule());
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
        revalidate();
    }

    private void removeAllElementsFromForm() {
        Stream.of(elementsPanel.getComponents())
                .forEach(elementsPanel::remove);
        repaint();
    }

    private DocumentListener getTextChangeListener() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent e) {
                saveButton.setEnabled(true);
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                saveButton.setEnabled(true);
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                saveButton.setEnabled(true);
            }
        };
    }

    private ActionListener getSaveButtonListener() {
        return event -> {
            try {
                validateInput();
                saveChanges();
            } catch (final EmptyTextException e) {
                log.error(e.getMessage(), e);
                ExceptionPanes.showValidationException(e, getRootPane());
            }
        };
    }

    private void validateInput() throws EmptyTextException {
        final List<String> invalidInputs = formElements.entrySet()
                .stream()
                .filter(FormUtils::isTextComponent)
                .filter(FormUtils::isEmptyNNTextComponent)
                .map(Entry::getKey)
                .map(DetailPanelElement::getLabel)
                .collect(Collectors.toList());

        if (!invalidInputs.isEmpty()) {
            throw new EmptyTextException(invalidInputs);
        }
    }

    private void saveChanges() {
        log.debug("Saving changes for {}", lastSelectedNode.getPreviewTitle());

        final QuestionModel model = (QuestionModel) tree.getModel();
        model.updateNode(lastSelectedNode, formElements);

        JTreeUtils.expand(tree);
    }
}
