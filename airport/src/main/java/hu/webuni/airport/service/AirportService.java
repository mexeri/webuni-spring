package hu.webuni.airport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hu.webuni.airport.model.Airport;

@Service
public class AirportService {

	 private Map<Long, Airport> airports = new HashMap<>();
	 {
		 airports.put(1L, new Airport(1,"abc","XYZ"));
		 airports.put(2L, new Airport(2,"def","UVW"));
	 }
	
	 public Airport save(Airport airport) {
		 CheckUniqueIata(airport.getsIata());
		 airports.put(airport.getLid(), airport);
		 return airport;
	 }
	 
	 private void CheckUniqueIata(String sIata) {
		Optional<Airport> airportWithSameIata = airports.values().stream().filter(a -> a.getsIata().equals(sIata)).findAny();
		if(airportWithSameIata.isPresent())
			throw new NonUniqueIataException(sIata);
	}
	 
	 public List<Airport> findAll() {
		 return new ArrayList<>(airports.values());
	 }
	 
	 public Airport findById(long id) {
		 return airports.get(id);
	 }
	 
	 public void delete(long id) {
		 airports.remove(id);		 
	 }
		 
		 
}
