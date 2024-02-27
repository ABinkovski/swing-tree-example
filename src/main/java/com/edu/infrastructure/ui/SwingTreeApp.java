package com.edu.infrastructure.ui;

import com.edu.infrastructure.ui.frame.MainAppFrame;

import java.awt.EventQueue;

public class SwingTreeApp {

    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> new MainAppFrame("Question Builder App"));
    }

}
