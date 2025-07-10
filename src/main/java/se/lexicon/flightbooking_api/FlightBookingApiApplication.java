package se.lexicon.flightbooking_api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightBookingApiApplication {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().systemProperties().ignoreIfMissing().directory("src/main/java/se/lexicon/flightbooking_api/config/").load();
        
        SpringApplication.run(FlightBookingApiApplication.class, args);
    }

}
