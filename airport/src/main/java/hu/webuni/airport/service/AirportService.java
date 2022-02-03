package hu.webuni.airport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.airport.model.Airport;

@Service
public class AirportService {

	@PersistenceContext
	EntityManager em;
	
	 @Transactional
	 public Airport save(Airport airport) {
		 CheckUniqueIata(airport.getsIata());
		 em.persist(airport);
		 return airport;
	 }
	 
	 @Transactional
	 public Airport update(Airport airport) {
		 CheckUniqueIata(airport.getsIata());
		 return em.merge(airport);
	 }
	 
	 private void CheckUniqueIata(String sIata) {
		 
		Long count = em.createNamedQuery("Airport.countByIata",Long.class)
			.setParameter("sIata", sIata)
			.getSingleResult();
		
		if(count > 0)
			throw new NonUniqueIataException(sIata);
	}
	 
	 public List<Airport> findAll() {
		 return em.createQuery("SELECT a FROM Airport a",Airport.class).getResultList();
	 }
	 
	 public Airport findById(long id) {
		 return em.find(Airport.class, id);
	 }
	 
	 @Transactional
	 public void delete(long id) {
		  	em.remove(findById(id));	 
	 }
		 
		 
}
