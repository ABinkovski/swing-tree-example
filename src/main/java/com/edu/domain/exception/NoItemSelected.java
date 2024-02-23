package com.edu.domain.exception;

public class NoItemSelected extends Exception {
    public NoItemSelected() {
        super("You have to select an Item to continue");
    }
}
