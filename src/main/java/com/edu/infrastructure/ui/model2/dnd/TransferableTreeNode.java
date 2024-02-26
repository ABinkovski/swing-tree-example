package com.edu.infrastructure.ui.model2.dnd;

import lombok.RequiredArgsConstructor;

import javax.swing.tree.TreePath;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

@RequiredArgsConstructor
public class TransferableTreeNode implements Transferable {

    public static final DataFlavor TREE_PATH_FLAVOR = new DataFlavor(TreePath.class, "Tree Path");

    private static final DataFlavor[] flavors = {TREE_PATH_FLAVOR};

    private final TreePath path;

    @Override
    public synchronized DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }

    @Override
    public boolean isDataFlavorSupported(final DataFlavor flavor) {
        return flavor.getRepresentationClass() == TreePath.class;
    }

    @Override
    public synchronized Object getTransferData(final DataFlavor flavor) throws UnsupportedFlavorException {
        if (isDataFlavorSupported(flavor)) {
            return path;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
