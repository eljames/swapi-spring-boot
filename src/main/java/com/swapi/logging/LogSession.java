package com.swapi.logging;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.json.JSONObject;

import com.swapi.logging.json.LogContent;
import com.swapi.logging.json.LogEntry;

/**
 * Responsible for logging automatically a structured log after the given execution has been finished.
 * If the execution throws a exception, it will be log automatically as well.
 * @author Eli James Aguiar
 *
 */
public class LogSession {
	
	private final Logging logging;
	private final LogContent content;
	
	public LogSession(Logging logging, LogContent content) {
		this.logging = logging;
		this.content = content;
	}
	
	/**
	 * 
	 * @param execution
	 * @return
	 * @throws Exception
	 */
	public <T> T log(LogExecution<T> execution) throws Exception {
		
		LogEntry entry = new LogEntry(content);
		try {
			T obj = execution.act();
			this.content.toInfo();
			entry.level(this.content.level());
			JSONObject json = new JSONObject(entry.toMap());
			this.logging.info(json.toString());
			return obj;
		} catch (Exception e) {
			this.content.toError();
			entry.level(this.content.level());
			this.content.add("exception", ExceptionToString(e));
			JSONObject json = new JSONObject(entry.toMap());
			this.logging.error(json.toString());
			throw new Exception(e);
		}
	}
	
	private String ExceptionToString(Exception exception) {
		StringWriter sw = new StringWriter();
		exception.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	
}
