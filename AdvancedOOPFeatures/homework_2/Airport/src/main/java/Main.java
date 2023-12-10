package main.java;

import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Flight> flights = findPlanesLeavingInTheNextTwoHours(airport);
        flights.forEach(System.out::println);
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime twoHoursFromNow = now.plusHours(2);

        return airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> {
                    LocalDateTime departureTime =
                            flight.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    return flight.getType() == Flight.Type.DEPARTURE &&
                            departureTime.isAfter(now) &&
                            departureTime.isBefore(twoHoursFromNow);
                })
                .collect(Collectors.toList());
    }
}