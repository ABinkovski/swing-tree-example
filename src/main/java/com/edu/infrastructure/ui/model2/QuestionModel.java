package com.edu.infrastructure.ui.model2;

import com.edu.domain.model2.Question;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import static java.util.Optional.ofNullable;

@Slf4j
@NoArgsConstructor
public class QuestionModel implements TreeModel {

    private Question root;
    private final EventListenerList listenerList = new EventListenerList();

    public void setRoot(final Object root) {
        this.root = (Question) root;
        ofNullable(this.root)
                .ifPresent(this::fireTreeStructureChanged);
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(final Object parent, final int index) {
        return ((Question) parent).getChild(index);
    }

    @Override
    public int getChildCount(final Object parent) {
        return ((Question) parent).getChildCount();
    }

    @Override
    public boolean isLeaf(final Object node) {
        return ((Question) node).isLeaf();
    }

    @Override
    public void valueForPathChanged(final TreePath path, final Object newValue) {
        fireTreeStructureChanged((Question) newValue);
    }

    @Override
    public int getIndexOfChild(final Object parent, final Object child) {
        return ((Question) parent).getIndexOfChild((Question) child);
    }

    @Override
    public void addTreeModelListener(final TreeModelListener l) {
        listenerList.add(TreeModelListener.class, l);
    }

    @Override
    public void removeTreeModelListener(final TreeModelListener l) {
        listenerList.remove(TreeModelListener.class, l);
    }

    public void addChild(final Question parent, final Question children) {
        parent.addQuestion(children);
        fireTreeStructureChanged(parent);
    }

    public boolean removeChild(final Question parent, final Question children) {
        final boolean removed = parent.remove(children);
        if (removed) {
            fireTreeStructureChanged(parent);
        }
        return removed;
    }

    protected void fireTreeStructureChanged(final Question parent) {
        log.debug("fireTreeStructureChanged for [{}]", parent.previewTitle());

        final TreeModelEvent event = new TreeModelEvent(this, new Object[]{parent});
        for (final TreeModelListener listener : listenerList.getListeners(TreeModelListener.class)) {
            listener.treeStructureChanged(event);
        }
    }
}
