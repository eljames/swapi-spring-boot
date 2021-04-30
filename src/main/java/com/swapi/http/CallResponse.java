package com.swapi.http;

import java.io.IOException;

public interface CallResponse {
	public String content() throws IOException;
	public boolean isSuccessful();
	public int code();
}
