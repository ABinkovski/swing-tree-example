package com.edu.infrastructure.ui.util;

import com.edu.infrastructure.ui.model2.DetailPanelElement;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import javax.swing.text.JTextComponent;
import java.awt.Component;
import java.util.Map.Entry;

@UtilityClass
public class FormUtils {
    public static boolean isTextComponent(final Entry<DetailPanelElement, Component> entry) {
        return isTextComponent(entry.getKey());
    }

    public static boolean isTextComponent(final DetailPanelElement element) {
        // TODO find more elegant solution if possible
        return JTextComponent.class.isAssignableFrom(element.getAClass());
    }

    public static boolean isEmptyNNTextComponent(final Entry<DetailPanelElement, Component> entry) {
        return !entry.getKey().isNullable() && StringUtils.isBlank(((JTextComponent) entry.getValue()).getText());
    }
}
