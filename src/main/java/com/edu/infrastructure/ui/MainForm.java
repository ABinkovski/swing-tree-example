package com.edu.infrastructure.ui;

import com.edu.infrastructure.ui.utils.SimpleJFrame;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
@Deprecated
public class MainForm {
    public static void main(final String[] args) {
        log.info("Starting app...");

        SimpleJFrame.createAndShowInstance(getFormComponents());
    }

    private static Component[] getFormComponents() {
        final JPanel panel = new JPanel();
        final JTree jTree = new JTree();
        panel.add(new JScrollPane(jTree), BorderLayout.CENTER);

        return new Component[]{panel};
    }
}
