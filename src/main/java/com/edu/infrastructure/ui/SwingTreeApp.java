package com.edu.infrastructure.ui;

import javax.swing.*;

public class SwingTreeApp {

    public static void main(String[] args) {
        final JFrame jFrame = new JFrame();

        jFrame.add(new JScrollPane(new JTree()));

        jFrame.pack();

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.setVisible(true);
    }

}
