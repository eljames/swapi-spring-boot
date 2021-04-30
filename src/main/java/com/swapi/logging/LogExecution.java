package com.swapi.logging;

public interface LogExecution<T> {
	public T act() throws Exception;
}
