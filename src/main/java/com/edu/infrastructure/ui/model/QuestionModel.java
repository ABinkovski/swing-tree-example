package com.edu.infrastructure.ui.model;

import com.edu.domain.model.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

@Builder
@AllArgsConstructor
public class QuestionModel implements TreeModel {

    @Getter
    @Setter
    private Question root;

    private final EventListenerList listenerList = new EventListenerList();

    @Override
    public Object getChild(final Object parent, final int index) {
        final Question question = (Question) parent;
        return question.getQuestion(index);
    }

    @Override
    public int getChildCount(final Object parent) {
        return ((Question) parent).getNextQuestionSize();
    }

    @Override
    public boolean isLeaf(final Object node) {
        return ((Question) node).getNextQuestionSize() == 0;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

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
}
