package com.edu.domain.exception;

import com.edu.domain.model2.Question;

import static java.lang.String.format;

public class ChildNotFoundException extends Exception {
    public ChildNotFoundException(final Question parent, final Question child) {
        super(format("[%s] could not be found in [%s]", child.getTitle(), parent.getTitle()));
    }
}
