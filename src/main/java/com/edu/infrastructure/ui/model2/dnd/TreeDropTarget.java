package com.edu.infrastructure.ui.model2.dnd;

import com.edu.infrastructure.ui.model2.QuestionModel;
import com.edu.infrastructure.ui.model2.QuestionTreeNode;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

@Slf4j
public class TreeDropTarget implements DropTargetListener {

    private final DropTarget target;
    private final JTree targetTree;

    public TreeDropTarget(final JTree targetTree) {
        this.targetTree = targetTree;
        target = new DropTarget(targetTree, this);
    }

    @Override
    public void dragEnter(final DropTargetDragEvent dropTargetDragEvent) {
        dragEvent(dropTargetDragEvent);
    }

    @Override
    public void dragOver(final DropTargetDragEvent dropTargetDragEvent) {
        dragEvent(dropTargetDragEvent);
    }

    @Override
    public void dropActionChanged(final DropTargetDragEvent dropTargetDragEvent) {
        log.debug("Skip for dropActionChanged");
    }

    @Override
    public void dragExit(final DropTargetEvent dropTargetEvent) {
        log.debug("Skip for dragExit");
    }

    @Override
    public void drop(final DropTargetDropEvent dropTargetDropEvent) {
        final QuestionTreeNode parent = getNodeForEvent(dropTargetDropEvent);
        final JTree tree = (JTree) dropTargetDropEvent.getDropTargetContext().getComponent();

        if (parent.isLeaf()) {
            dropTargetDropEvent.rejectDrop();
            return;
        }

        try {
            final Transferable transferable = dropTargetDropEvent.getTransferable();
            final DataFlavor[] flavors = transferable.getTransferDataFlavors();
            for (final DataFlavor flavor : flavors) {
                if (transferable.isDataFlavorSupported(flavor)) {
                    dropTargetDropEvent.acceptDrop(dropTargetDropEvent.getDropAction());
                    final TreePath treePath = (TreePath) transferable.getTransferData(flavor);
                    final QuestionTreeNode treeNode = (QuestionTreeNode) treePath.getLastPathComponent();
                    final QuestionModel model = (QuestionModel) tree.getModel();
                    model.insertNodeInto(treeNode.clone(), parent, 0);
                    dropTargetDropEvent.dropComplete(true);
                }
            }

        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            dropTargetDropEvent.rejectDrop();
        }
    }

    private void dragEvent(final DropTargetDragEvent dropTargetDragEvent) {
        final TreeNode treeNode = getNodeForEvent(dropTargetDragEvent);
        if (treeNode.isLeaf()) {
            dropTargetDragEvent.rejectDrag();
        } else {
            dropTargetDragEvent.acceptDrag(dropTargetDragEvent.getDropAction());
        }
    }

    private QuestionTreeNode getNodeForEvent(final DropTargetDropEvent dropTargetDropEvent) {
        return (QuestionTreeNode) getNodeForEvent(dropTargetDropEvent.getLocation(), dropTargetDropEvent.getDropTargetContext());
    }

    private TreeNode getNodeForEvent(final DropTargetDragEvent dropTargetDragEvent) {
        return getNodeForEvent(dropTargetDragEvent.getLocation(), dropTargetDragEvent.getDropTargetContext());
    }

    private TreeNode getNodeForEvent(final Point point, final DropTargetContext dropTargetContext) {
        final JTree tree = (JTree) dropTargetContext.getComponent();
        final TreePath closestPathForLocation = tree.getClosestPathForLocation(point.x, point.y);

        return (TreeNode) closestPathForLocation.getLastPathComponent();
    }
}
