package hu.webuni.airport.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.airport.model.Airport;
import hu.webuni.airport.model.Flight;
import hu.webuni.airport.repository.AirportRepository;
import hu.webuni.airport.repository.FlightRepository;

@Service
public class AirportService {

	
	AirportRepository airportRepository;
	FlightRepository flightRepository;
	
	 public AirportService(AirportRepository airportRepository, FlightRepository flightRepository) {
		super();
		this.airportRepository = airportRepository;
		this.flightRepository = flightRepository;
	}


//	@PersistenceContext
//	EntityManager em;
	


	@Transactional
	 public Airport save(Airport airport) {
		 CheckUniqueIata(airport.getsIata(),null);
//		 em.persist(airport);
		 return airportRepository.save(airport);
	 }
	 
	 @Transactional
	 public Airport update(Airport airport) {
		 CheckUniqueIata(airport.getsIata(),airport.getLid());
		 if(airportRepository.existsById(airport.getLid()))
			 return airportRepository.save(airport) ;
		 return null;
	 }
	 
	 private void CheckUniqueIata(String sIata, Long lid) {
		 
		boolean forUpdate = lid != null; 
//		TypedQuery<Long> query = em.createNamedQuery(forUpdate ? "Airport.countByIataAndIdNotIn" : "Airport.countByIata",Long.class)
//			.setParameter("sIata", sIata);
//		if(forUpdate)
//			query.setParameter("id", id);
//		
//		Long count = query
//			.getSingleResult();
		
		Long count = forUpdate ?
			airportRepository.countByIataAndIdNot(sIata, lid)
			: airportRepository.countByIata(sIata);
			
		
		if(count > 0)
			throw new NonUniqueIataException(sIata);
	}
	 
	 public List<Airport> findAll() {
//		 return em.createQuery("SELECT a FROM Airport a",Airport.class).getResultList();
		 return airportRepository.findAll();
	 }
	 
	 public Optional<Airport> findById(long lid) {
//		 return em.find(Airport.class, id);
		 return airportRepository.findById(lid);
	 }
	 
	 @Transactional
	 public void delete(long lid) {
//		  	em.remove(findById(id));
		 airportRepository.deleteById(lid);
	 }
	 
	 @Transactional
	 public void createFlight() {
		 Flight flight = new Flight();
		 flight.setFlightNumber("A1234");
		 flight.setTakeoff(airportRepository.findById(2L).get());
		 flight.setLanding(airportRepository.findById(3L).get());
		 flight.setTakeoffTime(LocalDateTime.of(2021,4,10,10,0,0));
		 flightRepository.save(flight);
	 }
}
