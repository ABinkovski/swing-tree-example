package com.edu.infrastructure.ui.util;

import lombok.experimental.UtilityClass;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.Collections;
import java.util.stream.IntStream;

@UtilityClass
public class JTreeUtils {

    public static void expand(final JTree jTree) {
        IntStream.rangeClosed(0, jTree.getRowCount())
                .forEach(jTree::expandRow);
        final DefaultMutableTreeNode root = (DefaultMutableTreeNode) jTree.getModel().getRoot();
        expand(root, jTree);
    }

    private static void expand(final DefaultMutableTreeNode node, final JTree jTree) {
        jTree.expandPath(new TreePath(node.getPath()));

        Collections.list(node.children())
                .forEach(children -> expand((DefaultMutableTreeNode) children, jTree));
    }

}
