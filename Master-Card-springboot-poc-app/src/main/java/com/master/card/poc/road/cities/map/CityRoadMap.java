package com.master.card.poc.road.cities.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class CityRoadMap {
	
	private Map<String, Set<String>> cityRoads = new HashMap<>();
	
	public void addAdjacentNeighborCities( String cityA,  String cityB) {
		cityA = cityA.trim().toLowerCase();
		cityB = cityB.trim().toLowerCase();

		Set<String> directConnectionsA = this.cityRoads.getOrDefault(cityA, new HashSet<String>());
		directConnectionsA.add(cityB);
		this.cityRoads.put(cityA, directConnectionsA);

		Set<String> directConnectionsB = this.cityRoads.getOrDefault(cityB, new HashSet<String>());
		directConnectionsB.add(cityA);
		this.cityRoads.put(cityB, directConnectionsB);
	}
	
	
	public boolean areCitiesConnected(String cityA, String cityB) {
		cityA = cityA.trim().toLowerCase();
		cityB = cityB.trim().toLowerCase();

		if (this.cityRoads.size() < 1) {
			return false;
		}

		if (!this.cityRoads.containsKey(cityA) && !this.cityRoads.containsKey(cityB)) {
			return false;
		}

		Set<String> visitedNodes = new HashSet<>();
		Stack<String> stack = new Stack<>();
		stack.push(cityA);

		while (!stack.isEmpty()) {
			String currentCityNode = stack.pop();
			visitedNodes.add(currentCityNode);

			if (currentCityNode.equals(cityB)) {
				return true;
			}

			for (String neighbor : this.cityRoads.get(currentCityNode)) {
				if (!visitedNodes.contains(neighbor)) {
					stack.push(neighbor);
				}
			}
		}

		return false;
	}

}
