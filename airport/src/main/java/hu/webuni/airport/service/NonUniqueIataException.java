package hu.webuni.airport.service;

public class NonUniqueIataException extends RuntimeException {

	public NonUniqueIataException(String sIata) {
		super("Existing Iata " + sIata);
		
	}
}
