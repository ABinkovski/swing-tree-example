package com.edu.domain.exception;

public class ItemIsParent extends Exception {
    public ItemIsParent() {
        super("This operation is not applicable for root element");
    }
}
