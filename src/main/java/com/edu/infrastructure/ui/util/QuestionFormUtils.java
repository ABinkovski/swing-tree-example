package com.edu.infrastructure.ui.util;

import com.edu.infrastructure.ui.model.QuestionActionListener;
import com.edu.infrastructure.ui.model.QuestionActionListenerType;
import lombok.experimental.UtilityClass;

import javax.swing.JButton;
import javax.swing.JTree;

@UtilityClass
public class QuestionFormUtils {

    public static JButton createButton(final QuestionActionListenerType type, final JTree tree) {
        final JButton button = new JButton(type.getTitle());
        button.addActionListener(new QuestionActionListener(type, tree));

        return button;
    }

}
