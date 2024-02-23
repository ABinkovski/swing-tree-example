package com.edu.infrastructure.ui.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuestionActionListenerType {
    ADD_CHILD("Add child"),
    ADD_SIBLING("Add Sibling"),
    DELETE("Delete");

    private final String title;
}
