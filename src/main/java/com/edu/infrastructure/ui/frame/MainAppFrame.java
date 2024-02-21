package com.edu.infrastructure.ui.frame;

import com.edu.infrastructure.ui.util.JTreeUtils;
import com.edu.infrastructure.ui.util.WindowUtils;

import javax.swing.*;
import java.awt.*;

public class MainAppFrame extends JFrame {

    final JTree jTree;

    final JPanel actionPanel;

    public MainAppFrame(final String title) throws HeadlessException {
        super(title);

        add(new JScrollPane(jTree = initTree()), BorderLayout.CENTER);
        add(actionPanel = initActionPanel(), BorderLayout.EAST);

        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        WindowUtils.centerTheFrame(this);

        setVisible(true);
    }

    private JTree initTree() {
        final JTree tree = new JTree();

        JTreeUtils.expand(tree);

        return tree;
    }

    private JPanel initActionPanel() {
        return new JPanel();
    }
}
