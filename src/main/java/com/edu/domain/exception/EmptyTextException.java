package com.edu.domain.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static java.lang.String.format;

public class EmptyTextException extends Exception {
    public EmptyTextException(final List<String> labels) {
        super(format("The following values could not be empty: %s", StringUtils.join(labels, ",")));
    }

    public EmptyTextException(String message) {
        super(message);
    }
}
