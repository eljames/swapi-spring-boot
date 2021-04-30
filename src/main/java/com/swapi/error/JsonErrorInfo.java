package com.swapi.error;

import org.json.JSONObject;

public class JsonErrorInfo {
	private final ErrorInfo error;

	public JsonErrorInfo(ErrorInfo error) {
		this.error = error;
	}
	
	public String toJson() {
		JSONObject json = new JSONObject()
			.put("code", this.error.code())
			.put("description", this.error.description());
		return json.toString();
	}
}
