package com.swapi.http;

import java.io.IOException;

public class HttpCallMock implements HttpCall {

	private final CallResponse response;
	
	public HttpCallMock(CallResponse response) {
		this.response = response;
	}
	
	@Override
	public CallResponse get() throws IOException {
		return this.response;
	}
}
