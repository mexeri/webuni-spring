package hu.webuni.airport;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hu.webuni.airport.service.AirportService;
import hu.webuni.airport.service.DefaultDiscountService;
import hu.webuni.airport.service.DiscountService;
import hu.webuni.airport.service.PriceService;


@SpringBootApplication
public class AirportApplication implements CommandLineRunner {

	@Autowired
	PriceService priceService;
	
	@Autowired
	AirportService airportService;
	
	public static void main(String[] args) {
		SpringApplication.run(AirportApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		airportService.createFlight("A1234", 2L, 3L, LocalDateTime.of(2021,4,10,10,0,0));
		System.out.println(priceService.getFinalPrice(200));
		System.out.println(priceService.getFinalPrice(20000));
		
	}


}
