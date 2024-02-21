package com.edu.infrastructure.ui.frame;

import com.edu.infrastructure.ui.util.WindowUtils;

import javax.swing.*;
import java.awt.*;

public class MainAppFrame extends JFrame {

    final JTree jTree;

    public MainAppFrame(final String title) throws HeadlessException {
        super(title);

        add(new JScrollPane(jTree = new JTree()), BorderLayout.CENTER);

        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        WindowUtils.centerTheFrame(this);

        setVisible(true);
    }
}
