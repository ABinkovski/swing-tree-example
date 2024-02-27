package com.edu.infrastructure.ui.model2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.JTextField;

@Getter
@RequiredArgsConstructor
public enum DetailPanelElement {
    ID(JTextField.class, "ID", false, false),
    NAME(JTextField.class, "Name", true, false),
    TITLE(JTextField.class, "Title", true, true),
    RULE(JTextField.class, "Rule", true, true);
    private final Class<?> aClass;
    private final String label;
    private final boolean editable;
    private final boolean nullable;
}
