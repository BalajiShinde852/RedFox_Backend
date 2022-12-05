package com.recruitapp.redfoxfitnessclub.exception;

public class TrainerAlreadyExistsException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TrainerAlreadyExistsException() {
	super();
    }

    public TrainerAlreadyExistsException(String string) {
	super(string);
    }
}
