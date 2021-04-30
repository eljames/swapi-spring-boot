package com.swapi.error;

public class ErrorInfo {
	
	private final String code;
	private final String description;
	
	public ErrorInfo(String code, String description) {
		this.code = code;
		this.description = description;
	}
	/**
	 * 
	 * @return Error code from specific domain.
	 */
	public String code() {
		return this.code;
	}
	
	/**
	 * 
	 * @return Error description from specific domain.
	 */
	public String description() {
		return this.description;
	}
	
	
}
