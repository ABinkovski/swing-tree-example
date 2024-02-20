package com.edu.infrastructure.ui;

import com.edu.infrastructure.ui.utils.SimpleJFrame;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainForm {
    public static void main(final String[] args) {
        log.info("Starting app...");

        SimpleJFrame.createAndShowInstance();
    }
}
