package com.swapi.logging.json;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class LogEntry {
	private final Map<String, Object> entries = new HashMap<>();
	private final LogContent content;
	
	public LogEntry(LogContent content) {
		this.content = content;
		this.entries.put("entrydate", Instant.now().toString());
	}
	
	public Map<String, Object> toMap() {
		try {
			this.entries.put("machine", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			this.entries.put("machine", "Unknown");
		}
		this.entries.put("message", this.content.content());
		return this.entries;
	}
	
	public void level(String level) {
		this.entries.putIfAbsent("level", level);
	}
}
