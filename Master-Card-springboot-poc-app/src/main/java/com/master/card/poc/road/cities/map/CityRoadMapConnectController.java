package com.master.card.poc.road.cities.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/connected")
public class CityRoadMapConnectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CityRoadMapConnectController.class);
	
	@Autowired
	CityRoadMapService cityRoadMapService;
	
	@GetMapping
	public ResponseEntity<String> isConnected(
				@RequestParam(required = true, defaultValue = "")  String origin,
				@RequestParam(required = true, defaultValue = "")  String destination) {
			LOGGER.debug("isConnected is called with origin city = {} and destination city = {}", origin, destination);
			return new ResponseEntity<String>(cityRoadMapService.areCitiesRoadsConnected(origin, destination), HttpStatus.OK);
	}
}
