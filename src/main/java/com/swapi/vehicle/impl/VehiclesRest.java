package com.swapi.vehicle.impl;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapi.error.ErrorInfo;
import com.swapi.error.TreatebleErrorException;
import com.swapi.http.CallResponse;
import com.swapi.http.HttpCall;
import com.swapi.vehicle.Errors;
import com.swapi.vehicle.Vehicles;
import com.swapi.vehicle.VehiclesInfo;


public class VehiclesRest implements Vehicles {

	private final HttpCall http;
	
	public VehiclesRest(HttpCall http) {
		this.http = http;
	}
	
	public VehiclesInfo get() throws TreatebleErrorException, Exception {
		
		CallResponse response = this.http.get();
		return CheckHttpResponse(response);
	}

	private VehiclesInfo CheckHttpResponse(CallResponse response) throws TreatebleErrorException, Exception {
		if(response.isSuccessful()) {
			return MapResponse(response);
		}
		ErrorInfo error = ErrorByResponse(response);
		throw new TreatebleErrorException(error);
	}

	private VehiclesInfo MapResponse(CallResponse response) throws IOException {
		String body = response.content();
		ObjectMapper mapper = new ObjectMapper();
		VehiclesInfo vehicles = mapper.readValue(body, VehiclesInfo.class);
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
