package com.edu.infrastructure.ui.model2.dnd;

import com.edu.infrastructure.ui.model2.QuestionTreeNode;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JTree;
import javax.swing.tree.TreePath;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

import static java.util.Objects.isNull;

@Slf4j
public class TreeDragSource implements DragSourceListener, DragGestureListener {

    final DragSource source;
    final DragGestureRecognizer recognizer;
    final JTree sourceTree;
    //    private DefaultMutableTreeNode oldNode;
    private QuestionTreeNode oldNode;

    private TransferableTreeNode transferableTreeNode;

    private final boolean clonableNodes;


    public TreeDragSource(final JTree sourceTree, final int actions) {
        this(sourceTree, actions, true);
    }

    public TreeDragSource(final JTree sourceTree, final int actions, final boolean clonableNodes) {
        this.sourceTree = sourceTree;
        this.clonableNodes = clonableNodes;
        source = new DragSource();
        recognizer = source.createDefaultDragGestureRecognizer(sourceTree, actions, this);
    }

    @Override
    public void dragGestureRecognized(final DragGestureEvent dragGestureEvent) {
        final TreePath path = sourceTree.getSelectionPath();
        if (isNull(path)) {
            log.debug("Empty selection");
            return;
        }
        if (path.getPathCount() < 2) {
            log.debug("We can't move the root node");
            return;
        }

        oldNode = (QuestionTreeNode) path.getLastPathComponent();
        transferableTreeNode = new TransferableTreeNode(path);
        source.startDrag(dragGestureEvent, DragSource.DefaultMoveDrop, transferableTreeNode, this);
    }

    @Override
    public void dragEnter(final DragSourceDragEvent dragSourceDragEvent) {
        log.debug("Skip for dragEnter");
    }

    @Override
    public void dragOver(final DragSourceDragEvent dragSourceDragEvent) {
        log.trace("Skip for dragOver");
    }

    @Override
    public void dropActionChanged(final DragSourceDragEvent dragSourceDragEvent) {
        log.debug("Skip for dropActionChanged");
        log.info("DropAction: {}", dragSourceDragEvent.getDropAction());
        log.info("TargetAction: {}", dragSourceDragEvent.getTargetActions());
        log.info("UserAction: {}", dragSourceDragEvent.getUserAction());
    }

    @Override
    public void dragExit(final DragSourceEvent dragSourceEvent) {
        log.debug("Skip for dragExit");
    }

    @Override
    public void dragDropEnd(final DragSourceDropEvent dragSourceDropEvent) {
        log.info("dragDropEnd: {}", dragSourceDropEvent.getDropAction());
        if (dragSourceDropEvent.getDropSuccess()) {
            // Remove old node if it is moving instead of cloning
            if (!clonableNodes) {
                ((QuestionTreeNode) sourceTree.getModel()).remove(oldNode);
            }
        }
    }
}
