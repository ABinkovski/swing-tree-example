package com.edu.infrastructure.ui.model2;

import com.edu.domain.model2.Question;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.Serializable;

import static java.util.Optional.ofNullable;

@Slf4j
@NoArgsConstructor
public class QuestionModel implements TreeModel, Serializable {

    private QuestionTreeNode root;
    private final EventListenerList listenerList = new EventListenerList();

    public void setRoot(final Object root) {
        this.root = (QuestionTreeNode) root;
        ofNullable(this.root)
                .ifPresent(this::fireTreeStructureChanged);
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(final Object parent, final int index) {
        return ((QuestionTreeNode) parent).getChild(index);
    }

    @Override
    public int getChildCount(final Object parent) {
        return ((QuestionTreeNode) parent).getChildCount();
    }

    @Override
    public boolean isLeaf(final Object node) {
        return ((QuestionTreeNode) node).isLeaf();
    }

    @Override
    public void valueForPathChanged(final TreePath path, final Object newValue) {
        fireTreeStructureChanged((QuestionTreeNode) newValue);
    }

    @Override
    public int getIndexOfChild(final Object parent, final Object child) {
        return ((QuestionTreeNode) parent).getIndexOfChild((Question) child);
    }

    @Override
    public void addTreeModelListener(final TreeModelListener l) {
        listenerList.add(TreeModelListener.class, l);
    }

    @Override
    public void removeTreeModelListener(final TreeModelListener l) {
        listenerList.remove(TreeModelListener.class, l);
    }

    public void insertNodeInto(final QuestionTreeNode newChild, final QuestionTreeNode parent, final int index) {
        parent.insertChildren(newChild, index);
    }

    public void addChild(final QuestionTreeNode parent, final QuestionTreeNode children) {
        parent.addQuestion(children);
        fireTreeStructureChanged(parent);
    }

    public boolean removeChild(final QuestionTreeNode parent, final QuestionTreeNode children) {
        final boolean removed = parent.remove(children);
        if (removed) {
            fireTreeStructureChanged(parent);
        }
        return removed;
    }

    protected void fireTreeStructureChanged(final QuestionTreeNode parent) {
        log.debug("fireTreeStructureChanged for [{}]", parent.getPreviewTitle());

        final TreeModelEvent event = new TreeModelEvent(this, new Object[]{parent});
        for (final TreeModelListener listener : listenerList.getListeners(TreeModelListener.class)) {
            listener.treeStructureChanged(event);
        }
    }
}
