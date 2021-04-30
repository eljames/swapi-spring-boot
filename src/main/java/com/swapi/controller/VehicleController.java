package com.swapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapi.error.ErrorInfo;
import com.swapi.error.HttpStatusFromErroInfo;
import com.swapi.error.JsonErrorInfo;
import com.swapi.error.TreatebleErrorException;
import com.swapi.logging.LogSession;
import com.swapi.logging.LoggingCreation;
import com.swapi.logging.json.LogContent;
import com.swapi.vehicle.Errors;
import com.swapi.vehicle.Vehicles;
import com.swapi.vehicle.VehiclesInfo;
import com.swapi.vehicle.impl.HttpCallVehicles;
import com.swapi.vehicle.impl.VehiclesRest;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
	
	@RequestMapping("")
	public ResponseEntity<Object> get(@RequestParam(name = "page", defaultValue = "1") int page) {
		return new VehicleDefault().get(page);
	}
	
	
	class VehicleDefault {
		
		public ResponseEntity<Object> get(int page) {
			LogContent logcontent = new LogContent();
			try {
				return new LogSession(LoggingCreation.get(), logcontent).log(() -> {
					try {
						logcontent
						.type("vechicles")
						.request(
							new LogContent()
							.add("page", page)
							.content()
						);
						Vehicles vehicles = new VehiclesRest(
							new HttpCallVehicles(page)
						);
						VehiclesInfo infos = vehicles.get();
						logcontent
						.response(infos);
						return ResponseEntity.ok(infos);
					} catch (TreatebleErrorException e) {
						return ResponseEntity
							.status(new HttpStatusFromErroInfo(e.error()).status())
							.body(new JsonErrorInfo(e.error()).toJson());
					}
				});
			} catch (Exception e) {
				return ResponseEntity
						.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ErrorInfo(Errors.UNKNOWN_ERROR, "Erro desconhecido"));
			}
		}
	}
}