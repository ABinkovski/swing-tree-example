package com.edu.infrastructure.ui.model2;

import com.edu.domain.model2.Question;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

/**
 * Based on: https://coderanch.com/t/346509/java/JTree-drag-drop-tree-Java
 */
@Slf4j
public class QueryTransferHandler extends TransferHandler {

    private static final String MIME_TYPE =
            String.format("%s;class=\"%s\"", DataFlavor.javaJVMLocalObjectMimeType, Question.class.getName());

    DataFlavor nodesFlavor;
    DataFlavor[] flavors = new DataFlavor[1];
    Question[] nodesToRemove;

    public QueryTransferHandler() {
        try {

            nodesFlavor = new DataFlavor(MIME_TYPE);
            flavors[0] = nodesFlavor;
        } catch (final ClassNotFoundException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public boolean canImport(TransferSupport support) {
        log.info("canImport:~~~~~~~~~~~~~~~~~~~~~~~~");
        return super.canImport(support);
    }

    @Override
    protected Transferable createTransferable(JComponent component) {
        log.info("createTransferable:~~~~~~~~~~~~~~~~~~~~~~~~");
        return super.createTransferable(component);
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    @Override
    public boolean importData(TransferSupport support) {
        log.info("importData~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return super.importData(support);
    }
}
