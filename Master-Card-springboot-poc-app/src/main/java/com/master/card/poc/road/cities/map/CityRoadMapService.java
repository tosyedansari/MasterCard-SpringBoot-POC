package com.master.card.poc.road.cities.map;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


@Service
public class CityRoadMapService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CityRoadMapService.class);
	
	@Value("${cityPairsFileLocation}")
	private String connectedCityFilePath;
	
	private CityRoadMap cityRoadMap;
	
	@PostConstruct
	public void init() {

		List<String> connectedCityPairs = new ArrayList<>();

		try {
			connectedCityPairs = Files
					.readAllLines(ResourceUtils.getFile("classpath:" + connectedCityFilePath).toPath());
		} catch (IOException e) {
			LOGGER.error("File of Connected City Pairs Was not found: {} && an empty list is loaded",
					this.connectedCityFilePath);
			LOGGER.error(e.toString());
		}

		this.cityRoadMap = new CityRoadMap();
		for (String connectedCityPair : connectedCityPairs) {
			if (!connectedCityPair.isEmpty()) {
				String[] cities = connectedCityPair.split(", ");
				if (cities.length == 2) {
					this.cityRoadMap.addAdjacentNeighborCities(cities[0], cities[1]);
				} else {
					throw new IllegalArgumentException();
				}
			}

		}

	}
	
	public String areCitiesRoadsConnected(String cityA, String cityB) {
		try {
			if (this.cityRoadMap.areCitiesConnected(cityA, cityB)) {
				return "yes";
			}
		} catch (Exception e) {
			LOGGER.error("Either argument was not valid or unknown exception occured");
		}
		return "no";
	}

}
