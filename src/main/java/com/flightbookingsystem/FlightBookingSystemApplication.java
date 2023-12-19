package com.flightbookingsystem;

import com.flightbookingsystem.dto.FlightDTO;
import com.flightbookingsystem.service.FlightService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlightBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightBookingSystemApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(FlightService flightService) {
//		return runner -> {
//
//			createFlight
//		}
//	}
}
