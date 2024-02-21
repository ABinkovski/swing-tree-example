package com.edu.infrastructure.ui;

import com.edu.infrastructure.ui.frame.MainAppFrame;

import java.awt.*;

public class SwingTreeApp {

    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> new MainAppFrame("Simple Tree"));
    }

}
