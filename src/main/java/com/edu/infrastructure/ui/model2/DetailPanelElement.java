package com.edu.infrastructure.ui.model2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;

@Getter
@RequiredArgsConstructor
public enum DetailPanelElement {
    ID(JTextField.class, "ID", false),
    NAME(JTextField.class, "Name", true),
    TITLE(JTextField.class, "Title", true),
    RULE(JTextField.class, "Rule", true);
    private final Class<?> aClass;
    private final String label;
    private final boolean editable;
}
