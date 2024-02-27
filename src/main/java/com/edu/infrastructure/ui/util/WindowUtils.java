package com.edu.infrastructure.ui.util;

import lombok.experimental.UtilityClass;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.Arrays;

import static java.util.Objects.nonNull;

@UtilityClass
public final class WindowUtils {

    public static void centerTheFrame(final Window frame) {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int screenWidth = screenSize.width;
        final int screenHeight = screenSize.height;

        final Dimension frameSize = frame.getSize();
        final int frameHeight = frameSize.height;
        final int frameWidth = frameSize.width;

        frame.setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);
    }


    public static void setIcon(final String iconFileName, final JFrame frame) {
        final Image image = getImage(iconFileName);
        frame.setIconImage(image);
    }

    public static Image getImage(final String imageResourceName) {
        final URL resource = WindowUtils.class.getClassLoader().getResource(imageResourceName);
        assert resource != null;
        return new ImageIcon(resource).getImage();
    }

    public static void fillRectangle(final Graphics2D g,
                                     final Color drawColor,
                                     final double x,
                                     final double y,
                                     final double width,
                                     final double height) {
        final Color color = g.getColor();
        g.setColor(drawColor);

        final Rectangle2D.Double aDouble = new Rectangle2D.Double(x - 1, y - 1, width + 2, height + 2);

        g.fill(aDouble);

        g.setColor(color);
    }

    public static void cloneFormPartAsBackground(final JFrame frame, final Graphics2D g,
                                                 final int areaWidth, final int areaHeight) {
        final Dimension frameSize = frame.getSize();
        final int width = frameSize.width;
        final int height = frameSize.height;

        for (int i = 0; i * areaWidth <= width; i++) {
            for (int j = 0; j * areaHeight <= height; j++) {
                if (i + j > 0) {
                    g.copyArea(0, 0, areaWidth, areaHeight, i * areaWidth, j * areaHeight);
                }
            }
        }
    }

    public static void fillInstance(final JFrame jFrame, final Component... components) {
        if (nonNull(components)) {
            Arrays.stream(components)
                    .forEach(jFrame::add);

            jFrame.pack();

            WindowUtils.centerTheFrame(jFrame);
        }
    }

    @Deprecated
    public static void showInstance(final JFrame frame, final Component... components) {
        EventQueue.invokeLater(() -> {
            fillInstance(frame, components);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
