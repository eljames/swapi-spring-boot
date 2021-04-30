package com.swapi.starship.impl;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapi.error.ErrorInfo;
import com.swapi.error.TreatebleErrorException;
import com.swapi.http.CallResponse;
import com.swapi.http.HttpCall;
import com.swapi.vehicle.Errors;

public class StarshipsRest implements Starships {

	private final HttpCall http;
	
	public StarshipsRest(HttpCall http) {
		this.http = http;
	}
	
	@Override
	public StarshipsInfo get() throws TreatebleErrorException, Exception {
		CallResponse response = this.http.get();
		return CheckHttpResponse(response);
	}
	
	private StarshipsInfo CheckHttpResponse(CallResponse response) throws TreatebleErrorException, Exception {
		if(response.isSuccessful()) {
			return MapResponse(response);
		}
		ErrorInfo error = ErrorByResponse(response);
		throw new TreatebleErrorException(error);
	}

	private StarshipsInfo MapResponse(CallResponse response) throws IOException {
		String body = response.content();
		ObjectMapper mapper = new ObjectMapper();
		StarshipsInfo vehicles = mapper.readValue(body, StarshipsInfo.class);
		return vehicles;
	}

	private ErrorInfo ErrorByResponse(CallResponse response) throws IOException {
		int code = response.code();
		String errorbody = response.content();
		if(code == 404) {
			return new ErrorInfo(Errors.NOT_FOUND, "Resource not found: " + errorbody);
		}
		return new ErrorInfo(Errors.UNKNOWN_ERROR, "Unknown error occurred.");
	}

}
