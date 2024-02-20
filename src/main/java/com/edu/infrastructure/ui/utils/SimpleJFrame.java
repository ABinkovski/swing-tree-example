package com.edu.infrastructure.ui.utils;

import lombok.Builder;

import javax.swing.*;
import java.awt.*;

import static com.edu.infrastructure.ui.utils.WindowUtils.fillInstance;
import static java.util.Objects.nonNull;

@Builder
public class SimpleJFrame extends JFrame {

    private String title = "Simple JFrame";
    private int width = 400;
    private int height = 350;
    private boolean exitOnClose;

    private SimpleJFrame() {
        setTitle(title);

        formInit();
    }

    public SimpleJFrame(final String title, final int width, final int height, boolean exitOnClose) {
        super(title);
        this.title = title;
        this.width = width;
        this.height = height;
        this.exitOnClose = exitOnClose;

        formInit();
    }

    private void formInit() {
        setPreferredSize(new Dimension(width, height));

        setDefaultCloseOperation(exitOnClose ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE);
    }

    public static SimpleJFrame createInstance(final Component... components) {
        final SimpleJFrame jFrame = new SimpleJFrame();

        if (nonNull(components)) {
            fillInstance(jFrame, components);
        }

        return jFrame;
    }

    public static void createAndShowInstance(final Component... components) {
        EventQueue.invokeLater(() -> {
            final SimpleJFrame frame = createInstance(components);
            frame.setVisible(true);
        });
    }
}
