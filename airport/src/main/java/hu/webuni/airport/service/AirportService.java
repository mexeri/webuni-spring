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

import org.springframework.data.jpa.domain.Specification;
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
	 public void createFlight(String flightNumber, long takeoffAirportId, long landingAirportId, LocalDateTime takeoffDateTime) {
		 Flight flight = new Flight();
		 flight.setFlightNumber(flightNumber);
		 flight.setTakeoff(airportRepository.findById(takeoffAirportId).get());
		 flight.setLanding(airportRepository.findById(landingAirportId).get());
		 flight.setTakeoffTime(takeoffDateTime);
		 flightRepository.save(flight);
	 }
	 
	 public List<Flight> findFlightsByExample(Flight example){
		 
		 long id = example.getId();
		 String flightNumber = example.getFlightNumber();
		 Airport landing = example.getLanding();
		 String takeoffIata = null;
		 Airport takeoff = example.getTakeoff();
		 if(takeoff != null)
			 takeoffIata = takeoff.getsIata();
		 LocalDateTime takeoffTime = example.getTakeoffTime();
		 
		 Specification<Flight> spec = Specification.where(null);
		 
		 
		 if(id > 0) {			 
			spec = spec.and(FlightSpecifications.hasId(id));
		 }
	 
		 return null;
	 }
	 
}
