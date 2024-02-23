package com.edu.infrastructure.ui.model2;

import com.edu.domain.model2.Question;
import lombok.NoArgsConstructor;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

@NoArgsConstructor
public class QuestionModel implements TreeModel {

    private Question root;
    private final EventListenerList listenerList = new EventListenerList();

    public void setRoot(final Object root) {
        this.root = (Question) root;
        // TODO make tree updates
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
        // TODO
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


    protected void fireTreeStructureChanged(final Question parent) {
        final TreeModelEvent event = new TreeModelEvent(this, new Object[]{parent});
        for (TreeModelListener listener : listenerList.getListeners(TreeModelListener.class)) {
            listener.treeStructureChanged(event);
        }
    }
}
