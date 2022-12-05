package com.recruitapp.redfoxfitnessclub.exception;

public class UsernameAlreadyExistsException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public UsernameAlreadyExistsException() {
	super();
    }

    public UsernameAlreadyExistsException(String string) {
	super(string);
    }
}
