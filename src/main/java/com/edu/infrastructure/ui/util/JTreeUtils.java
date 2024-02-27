package com.edu.infrastructure.ui.util;

import com.edu.infrastructure.ui.model2.QuestionTreeNode;
import lombok.experimental.UtilityClass;

import javax.swing.JTree;
import javax.swing.tree.TreePath;
import java.util.Collections;
import java.util.stream.IntStream;

@UtilityClass
public class JTreeUtils {

    public static void expand(final JTree jTree) {
        IntStream.rangeClosed(0, jTree.getRowCount())
                .forEach(jTree::expandRow);
        final QuestionTreeNode root = (QuestionTreeNode) jTree.getModel().getRoot();
        expand(root, jTree);
    }

    private static void expand(final QuestionTreeNode node, final JTree jTree) {
        jTree.expandPath(new TreePath(node.getPath()));

        Collections.list(node.children())
                .forEach(children -> expand((QuestionTreeNode) children, jTree));
    }

}
