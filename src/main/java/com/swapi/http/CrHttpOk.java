package com.swapi.http;

import java.io.IOException;

import com.squareup.okhttp.Response;

public class CrHttpOk implements CallResponse {

	private final Response response;
	
	public CrHttpOk(Response response) {
		this.response = response;
	}

	@Override
	public String content() throws IOException {
		return response.body().string();
	}

	@Override
	public boolean isSuccessful() {
		return this.response.isSuccessful();
	}

	@Override
	public int code() {
		return this.response.code();
	}
	
}