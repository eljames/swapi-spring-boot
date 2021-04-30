package com.swapi.logging;

public class LoggingStdout implements Logging {

	@Override
	public void info(String msg) {
		System.out.println(msg);
	}

	@Override
	public void warn(String msg) {
		System.out.println(msg);
	}

	@Override
	public void error(String msg) {
		System.err.println(msg);
		
	}
	
}
