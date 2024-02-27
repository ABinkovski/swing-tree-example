package com.edu.infrastructure.ui;

import lombok.experimental.UtilityClass;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

@UtilityClass
public class ExceptionPanes {

    public static void showValidationException(final Exception exception, final Component root) {
        JOptionPane.showMessageDialog(root, exception.getMessage(), "Validation exception", ERROR_MESSAGE);
    }

}
