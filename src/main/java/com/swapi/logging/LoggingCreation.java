package com.swapi.logging;

public class LoggingCreation {

	
	private static Logging logging;
	
	static {
		logging = new LoggingStdout();
	}
	
	public static Logging get() {
		return logging;
	}
}
