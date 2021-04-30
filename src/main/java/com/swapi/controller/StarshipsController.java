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
import com.swapi.starship.impl.HttpCallStarships;
import com.swapi.starship.impl.Starships;
import com.swapi.starship.impl.StarshipsInfo;
import com.swapi.starship.impl.StarshipsRest;
import com.swapi.vehicle.Errors;

@RestController
@RequestMapping("/starships")
public class StarshipsController {

	@RequestMapping("")
	public ResponseEntity<Object> get(@RequestParam(name = "page", defaultValue = "1") int page) {
		return new StartshipsDefault().get(page);
	}
	
	
	class StartshipsDefault {
		
		public ResponseEntity<Object> get(int page) {
			LogContent logcontent = new LogContent();
			try {
				return new LogSession(LoggingCreation.get(), logcontent).log(() -> {
					try {
						
						logcontent
						.type("startships")
						.request(
							new LogContent()
							.add("page", page)
							.content()
						);
						Starships starships = new StarshipsRest(
							new HttpCallStarships(page)
						);
						StarshipsInfo infos = starships.get();
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
				ErrorInfo error = new ErrorInfo(Errors.UNKNOWN_ERROR, "Unknown error");
				return ResponseEntity
						.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new JsonErrorInfo(error).toJson());
			}
		}
	}
}
