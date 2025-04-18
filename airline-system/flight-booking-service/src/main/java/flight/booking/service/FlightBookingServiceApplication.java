package flight.booking.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FlightBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightBookingServiceApplication.class, args);
	}

}
