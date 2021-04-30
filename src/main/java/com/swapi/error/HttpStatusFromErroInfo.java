package com.swapi.error;

import com.swapi.vehicle.Errors;

public class HttpStatusFromErroInfo {
	private final ErrorInfo error;

	public HttpStatusFromErroInfo(ErrorInfo error) {
		this.error = error;
	}
	
	/**
	 * 
	 * @return Returns http status according to {@link ErrorInfo}.
	 */
	public int status() {
		String code = this.error.code();
		if(code.equals(Errors.NOT_FOUND)) {
			return 404;
		}
		return 500;
	}
}