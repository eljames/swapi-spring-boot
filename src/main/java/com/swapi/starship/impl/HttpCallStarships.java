package com.swapi.starship.impl;

import java.io.IOException;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.swapi.http.CallResponse;
import com.swapi.http.CrHttpOk;
import com.swapi.http.HttpCall;

public class HttpCallStarships implements HttpCall {

	private final int page;
	
	public HttpCallStarships(int page) {
		this.page = page;
	}

	@Override
	public CallResponse get() throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = GetRequest(this.page);
		Response response = client.newCall(request).execute();
		return new CrHttpOk(response);
	}
	
	private Request GetRequest(int page) {
		HttpUrl url = new HttpUrl.Builder()
			.scheme("http")
			.host("swapi.dev")
			.addPathSegment("api")
			.addPathSegment("starships")
			.addQueryParameter("page", String.valueOf(page))
			.build();
		Request request = new Request.Builder()
			.url(url)
			.build();
		return request;
	}
}
