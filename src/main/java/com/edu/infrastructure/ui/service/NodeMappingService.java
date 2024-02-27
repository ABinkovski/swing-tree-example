package com.edu.infrastructure.ui.service;

import com.edu.infrastructure.ui.model2.DetailPanelElement;
import com.edu.infrastructure.ui.model2.QuestionTreeNode;
import com.edu.infrastructure.ui.util.FormUtils;
import lombok.experimental.UtilityClass;

import javax.swing.text.JTextComponent;
import java.awt.Component;
import java.util.Map;

@UtilityClass
public class NodeMappingService {

    public static void updateNode(final QuestionTreeNode oldValue, final Map<DetailPanelElement, Component> formElements) {
        formElements.entrySet().forEach(entry -> {
            if (FormUtils.isTextComponent(entry)) {
                final JTextComponent textComponent = (JTextComponent) entry.getValue();
                setValue(entry.getKey(), oldValue, textComponent.getText());
            }
        });
    }


    public static String getValue(final DetailPanelElement element, final QuestionTreeNode node) {
        switch (element) {
            case ID:
                return node.getId();
            case NAME:
                return node.getName();
            case TITLE:
                return node.getTitle();
            case RULE:
                return node.getRule();
            default:
                throw new UnsupportedOperationException(element.name());
        }
    }

    public static void setValue(final DetailPanelElement element, final QuestionTreeNode node, final String value) {
        final String processedValue = value.trim();
        switch (element) {
            case ID:
                node.setId(processedValue);
                break;
            case NAME:
                node.setName(processedValue);
                break;
            case TITLE:
                node.setTitle(processedValue);
                break;
            case RULE:
                node.setRule(processedValue);
                break;
            default:
                throw new UnsupportedOperationException(element.name());
        }
    }
}
