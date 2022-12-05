package com.recruitapp.redfoxfitnessclub.exception;

public class PlanAlreadyExistsException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public PlanAlreadyExistsException() {
	super();
    }

    public PlanAlreadyExistsException(String string) {
	super(string);
    }
}
