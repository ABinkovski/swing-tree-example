package com.edu.infrastructure.ui.util;

import com.edu.domain.model2.Question;
import lombok.experimental.UtilityClass;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.util.Collections;
import java.util.stream.IntStream;

@UtilityClass
public class JTreeUtils {

    public static void expand(final JTree jTree) {
        IntStream.rangeClosed(0, jTree.getRowCount())
                .forEach(jTree::expandRow);
        final Question root = (Question) jTree.getModel().getRoot();
        expand(root, jTree);
    }

    private static void expand(final Question node, final JTree jTree) {
        jTree.expandPath(new TreePath(node.getPath()));

        Collections.list(node.children())
                .forEach(children -> expand((Question) children, jTree));
    }

}
