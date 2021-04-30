package com.swapi.error;

public class TreatebleErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ErrorInfo error;
	
	public TreatebleErrorException(ErrorInfo error) {
		this.error = error;
	}
	
	/**
	 * Returns the error information from this exception instance.
	 * @return
	 */
	public ErrorInfo error() {
		return this.error;
	}

}
