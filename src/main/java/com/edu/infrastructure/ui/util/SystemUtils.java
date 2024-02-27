package com.edu.infrastructure.ui.util;

import lombok.experimental.UtilityClass;

import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class SystemUtils {

    public static List<String> getAllSystemInstalledFonts() {
        final String[] availableFontFamilyNames = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();

        return Collections.unmodifiableList(Arrays.asList(availableFontFamilyNames));
    }
}
