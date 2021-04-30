package com.swapi.http;

import java.io.IOException;

public class CallResponseMock implements CallResponse {

	private final String content;
	private final boolean issuccefully;
	private final int code;
	
	public CallResponseMock(String content, boolean issuccefully, int code) {
		this.content = content;
		this.issuccefully = issuccefully;
		this.code = code;
	}

	@Override
	public String content() throws IOException {
		return this.content;
	}

	@Override
	public boolean isSuccessful() {
		return this.issuccefully;
	}

	@Override
	public int code() {
		return this.code;
	}

}
