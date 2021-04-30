package com.swapi.vehicle.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.swapi.error.ErrorInfo;
import com.swapi.error.TreatebleErrorException;
import com.swapi.http.CallResponse;
import com.swapi.http.CallResponseMock;
import com.swapi.http.HttpCall;
import com.swapi.http.HttpCallMock;
import com.swapi.vehicle.Errors;
import com.swapi.vehicle.Vehicles;
import com.swapi.vehicle.VehiclesInfo;

public class VehiclesRestTest {
	
	@Test
	public void mustReturnNotFound() throws Exception {
		CallResponse response = new CallResponseMock("", false, 404); // Http Status 404
		HttpCall http = new HttpCallMock(response);
		Vehicles vehicles = new VehiclesRest(http);
		try {
			vehicles.get();
		} catch (TreatebleErrorException e) {
			ErrorInfo error = e.error();
			assertEquals(error.code(), Errors.NOT_FOUND);
			return;
		}
		throw new Exception("No error found. Test must not pass");
	}
	
	@Test
	public void mustReturnUnknownError() throws Exception {
		// Http Status 500. Other error than 404, must return unknown error
		CallResponse response = new CallResponseMock("{\"count\":\"0\"}", false, 500); 
		HttpCall http = new HttpCallMock(response);
		Vehicles vehicles = new VehiclesRest(http);
		try {
			vehicles.get();
		} catch (TreatebleErrorException e) {
			ErrorInfo error = e.error();
			assertEquals(error.code(), Errors.UNKNOWN_ERROR);
			return;
		}
		throw new Exception("No error found. Test must not pass");
	}
	
	@Test
	public void mustReturnSuccefully() throws Exception {
		// Http Status 500. Other error than 404, must return unknown error
		CallResponse response = new CallResponseMock("{\"count\":\"1\"}", true, 0); 
		HttpCall http = new HttpCallMock(response);
		Vehicles vehicles = new VehiclesRest(http);
		VehiclesInfo vehiclesinfo = vehicles.get();
		assertEquals(vehiclesinfo.count, 1);
	}
}
