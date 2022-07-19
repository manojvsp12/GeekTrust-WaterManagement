package com.example.geektrust.exception;

/**
 * This class is a custom exception class that extends the Exception class
 *
 * @author Manoj SP
 */
public class WaterBillAppException extends Exception {

	private static final long serialVersionUID = 1L;

	public WaterBillAppException(String msg) {
		super(msg);
	}

}
