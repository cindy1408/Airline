package com.day9exercise.demo.Flight;

import com.day9exercise.demo.Country.Country;
import com.day9exercise.demo.Country.CountryRepositoryPostgres;
import com.day9exercise.demo.Customer.Customer;
import jdk.jfr.Enabled;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity(name = "Flight")
@Table(name = "flight")
public class Flight {
    @Id
    @SequenceGenerator(name="flight_sequence",
    sequenceName = "flight_sequence",
    allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, generator = "flight_sequence"
    )
    @Column(name = "id", updatable = false)
    private int flightId;
    @Column(name = "country_id")
    private int countryId;
    @Column(name = "customer_id")
    private int customersId;
    @Column(name = "travel_time_minutes")
    private int travelTimeMinutes;
    @Column(name = "time_departure")
    private LocalDateTime timeDeparture;
    @Column(name = "time_arrival")
    private LocalDateTime timeArrival;
    @Column(name = "return_ticket")
    private boolean withReturnTicket;
    @Column(name = "return_time_departure")
    private LocalDateTime returnTimeDeparture;
    @Column(name = "return_time_arrival")
    private LocalDateTime returnTimeArrival;
    @Column(name = "number_of_passengers")
    private int numberOfPassenger;
    @Column(name="total_price")
    private double totalPrice;
    @Column(name = "flight_number")
    private String flightNumber;

    public Flight(int countryId, int customersId, LocalDateTime timeDeparture, boolean withReturnTicket, LocalDateTime returnTimeDeparture, LocalDateTime returnTimeArrival, double totalPrice, String flightNumber) {
        this.countryId = countryId;
        this.customersId = customersId;
        this.timeDeparture = timeDeparture;
        this.withReturnTicket = withReturnTicket;
        this.returnTimeDeparture = returnTimeDeparture;
        this.returnTimeArrival = returnTimeArrival;
        this.totalPrice = totalPrice;
        this.flightNumber = flightNumber;
    }

    public Flight(int countryId, int customersId, LocalDateTime timeDeparture, boolean withReturnTicket, LocalDateTime returnTimeDeparture, String flightNumber) {
        this.countryId = countryId;
        this.customersId = customersId;
        this.timeDeparture = timeDeparture;
        this.withReturnTicket = withReturnTicket;
        this.returnTimeDeparture = returnTimeDeparture;
        this.flightNumber = flightNumber;
    }

    public Flight(int countryId, int customersId, LocalDateTime timeDeparture, boolean withReturnTicket, String flightNumber) {
        this.countryId = countryId;
        this.customersId = customersId;
        this.timeDeparture = timeDeparture;
        this.withReturnTicket = withReturnTicket;
        this.flightNumber = flightNumber;
    }

    public Flight(int countryId, LocalDateTime timeDeparture, String flightNumber) {
        this.countryId = countryId;
        this.timeDeparture = timeDeparture;
        this.flightNumber = flightNumber;
    }

    public Flight() {
    }

    public int getFlightId(){
        return flightId;
    }
    public void setFlightId(int flightId){
        this.flightId = flightId;
    }
    public int getDestination() {
        return countryId;
    }

    public void setDestination(Country destination) {
        this.countryId = countryId;
    }

    public int getCustomers() {
        return customersId;
    }

    public void setCustomers(List<Customer> customers) {
        this.customersId = customersId;
    }

    public double getTravelTimeMinutes() {
        return travelTimeMinutes;
    }

    public void setTravelTimeMinutes(int travelTimeMinutes) {
        this.travelTimeMinutes = travelTimeMinutes;
    }

    public LocalDateTime getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(LocalDateTime timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public LocalDateTime getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(LocalDateTime timeDeparture) {
        Duration addTravelTime = Duration.ofMinutes(travelTimeMinutes);
        timeArrival = timeDeparture.plus(addTravelTime);
        this.timeArrival = timeArrival;
    }

    public boolean isWithReturnTicket() {
        return withReturnTicket;
    }

    public void setWithReturnTicket(boolean withReturnTicket) {
        this.withReturnTicket = withReturnTicket;
    }

    public LocalDateTime getReturnTimeDeparture() {
        return returnTimeDeparture;
    }

    public void setReturnTimeDeparture(LocalDateTime returnTimeDeparture) {
        this.returnTimeDeparture = returnTimeDeparture;
    }

    public LocalDateTime getReturnTimeArrival() {
        return returnTimeArrival;
    }

    public void setReturnTimeArrival(LocalDateTime returnTimeArrival) {
        Duration addTravelTime = Duration.ofMinutes(travelTimeMinutes);
        returnTimeArrival = timeDeparture.plus(addTravelTime);
        this.returnTimeArrival = returnTimeArrival;
    }

    public int getNumberOfPassenger() {
        return numberOfPassenger;
    }

    public void setNumberOfPassenger(String listOfCustomerId) {
        String[] inputNumber = listOfCustomerId.split(",");
        int customerIds [] = new int[inputNumber.length];
        for(int i=0; i< inputNumber.length; i++){
            customerIds[i] = Integer.parseInt(inputNumber[i]);
        }
        this.numberOfPassenger = customerIds.length;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
//        totalPrice = country.getPrice()*customersId.size();
        this.totalPrice = totalPrice;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return flightId == flight.flightId && countryId == flight.countryId && travelTimeMinutes == flight.travelTimeMinutes && withReturnTicket == flight.withReturnTicket && numberOfPassenger == flight.numberOfPassenger && Double.compare(flight.totalPrice, totalPrice) == 0 && Objects.equals(customersId, flight.customersId) && Objects.equals(timeDeparture, flight.timeDeparture) && Objects.equals(timeArrival, flight.timeArrival) && Objects.equals(returnTimeDeparture, flight.returnTimeDeparture) && Objects.equals(returnTimeArrival, flight.returnTimeArrival) && Objects.equals(flightNumber, flight.flightNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, countryId, customersId, travelTimeMinutes, timeDeparture, timeArrival, withReturnTicket, returnTimeDeparture, returnTimeArrival, numberOfPassenger, totalPrice, flightNumber);
    }

    @Override
    public String toString() {
        return "Flight{" +
                ", countryId=" + countryId +
                ", customersId=" + customersId +
                ", travelTimeMinutes=" + travelTimeMinutes +
                ", timeDeparture=" + timeDeparture +
                ", timeArrival=" + timeArrival +
                ", withReturnTicket=" + withReturnTicket +
                ", returnTimeDeparture=" + returnTimeDeparture +
                ", returnTimeArrival=" + returnTimeArrival +
                ", numberOfPassenger=" + numberOfPassenger +
                ", totalPrice=" + totalPrice +
                ", flightNumber='" + flightNumber + '\'' +
                '}';
    }

//    public static void main(String[] args) {
//        Country country = new Country();
//        Flight flight = new Flight(3, 2, LocalDateTime.of(2021, 02,14,19, 40), false, "HKS698");
//        flight.setTimeArrival(flight.timeDeparture);
//        System.out.println(flight.getTimeArrival());
//        System.out.println(flight.getNumberOfPassenger());
//        System.out.println(flight.getTotalPrice());
//    }
}