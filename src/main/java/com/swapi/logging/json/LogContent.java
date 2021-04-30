package com.swapi.logging.json;

import java.util.HashMap;
import java.util.Map;

public class LogContent {
	
	private final Map<String, Object> content;

	public LogContent() {
		this.content = new HashMap<>();
	}
	
	public LogContent type(String type) {
		this.content.putIfAbsent("type", type);
		return this;
	}
	
	public LogContent request(Object obj) {
		this.content.putIfAbsent("request", obj);
		return this;
	}
	
	public LogContent response(Object obj) {
		this.content.put("response", obj);
		return this;
	}
	public LogContent toInfo() {
		this.content.putIfAbsent("level", "INFO");
		return this;
	}
	
	public LogContent toWarn() {
		this.content.putIfAbsent("level", "WARN");
		return this;
	}
	
	public LogContent toError() {
		this.content.put("level", "ERROR");
		return this;
	}
	
	public String level() {
		return (String) this.content.get("level");
	}
	
	
	public LogContent add(String field, Object obj) {
		this.content.put(field, obj);
		return this;
	}
	
	public Map<String, Object> content() {
		return this.content;
	}
}