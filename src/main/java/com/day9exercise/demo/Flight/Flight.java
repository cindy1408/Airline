package com.day9exercise.demo.Flight;

import com.day9exercise.demo.Country.Country;
import com.day9exercise.demo.Customer.Customer;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Flight {
    private Country origin;
    private Country destination;
    private List<Customer> customers;
    private double travelTimeHours;
    private LocalDateTime timeDeparture;
    private LocalDateTime timeArrival;
    private boolean withReturnTicket;
    private LocalTime returnTimeDeparture;
    private LocalTime returnTimeArrival;
    private int numberOfPassenger;
    private double totalPrice;
    private String flightNumber;

    public Flight(Country destination, List<Customer> customers, double travelTimeHours, LocalDateTime timeDeparture, LocalDateTime timeArrival, boolean withReturnTicket, LocalTime returnTimeDeparture, LocalTime returnTimeArrival, int numberOfPassenger, double totalPrice, String flightNumber) {
        this.destination = destination;
        this.customers = customers;
        this.travelTimeHours = travelTimeHours;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
        this.withReturnTicket = withReturnTicket;
        this.returnTimeDeparture = returnTimeDeparture;
        this.returnTimeArrival = returnTimeArrival;
        this.numberOfPassenger = this.customers.size();
        this.totalPrice = totalPrice;
        this.flightNumber = flightNumber;

    }

//    public Flight(Country destination, List<Customer> customers, double travelTimeHours, LocalDateTime timeDeparture, LocalDateTime timeArrival, boolean withReturnTicket, LocalTime returnTimeDeparture, LocalTime returnTimeArrival, double totalPrice, String flightNumber) {
//        this.numberOfPassenger = numberOfPassenger;
//        this.totalPrice = totalPrice;
//    }

//    public Flight(Country destination, List<Customer> customers, double travelTimeHours, LocalDateTime timeDeparture, LocalDateTime timeArrival, boolean withReturnTicket, int numberOfPassenger, double totalPrice) {
//        this.destination = destination;
//        this.customers = customers;
//        this.travelTimeHours = travelTimeHours;
//        this.timeDeparture = timeDeparture;
//        this.timeArrival = timeArrival;
//
//        this.withReturnTicket = withReturnTicket;
//        this.returnTimeDeparture = returnTimeDeparture;
//        this.returnTimeArrival = returnTimeArrival;
//
//        this.numberOfPassenger = this.customers.size();
//        this.totalPrice = totalPrice;
//        this.flightNumber = flightNumber;
//    }

    public Flight() {
    }

    public Country getOrigin() {
        return origin;
    }

    public void setOrigin(Country origin) {
        this.origin = origin;
    }

    public Country getDestination() {
        return destination;
    }

    public void setDestination(Country destination) {
        this.destination = destination;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public double getTravelTimeHours() {
        return travelTimeHours;
    }

    public void setTravelTimeHours(double travelTimeHours) {
        this.travelTimeHours = travelTimeHours;
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

    public void setTimeArrival(LocalDateTime timeArrival) {
        this.timeArrival = timeArrival;
    }

    public boolean isWithReturnTicket() {
        return withReturnTicket;
    }

    public void setWithReturnTicket(boolean withReturnTicket) {
        this.withReturnTicket = withReturnTicket;
    }

    public LocalTime getReturnTimeDeparture() {
        return returnTimeDeparture;
    }

    public void setReturnTimeDeparture(LocalTime returnTimeDeparture) {
        this.returnTimeDeparture = returnTimeDeparture;
    }

    public LocalTime getReturnTimeArrival() {
        return returnTimeArrival;
    }

    public void setReturnTimeArrival(LocalTime returnTimeArrival) {
        this.returnTimeArrival = returnTimeArrival;
    }

    public int getNumberOfPassenger() {
        return numberOfPassenger;
    }

    public void setNumberOfPassenger(int numberOfPassenger) {
        this.numberOfPassenger = this.customers.size();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
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
        return Double.compare(flight.getTravelTimeHours(), getTravelTimeHours()) == 0 && isWithReturnTicket() == flight.isWithReturnTicket() && getNumberOfPassenger() == flight.getNumberOfPassenger() && Double.compare(flight.getTotalPrice(), getTotalPrice()) == 0 && Objects.equals(getOrigin(), flight.getOrigin()) && Objects.equals(getDestination(), flight.getDestination()) && Objects.equals(getCustomers(), flight.getCustomers()) && Objects.equals(getTimeDeparture(), flight.getTimeDeparture()) && Objects.equals(getTimeArrival(), flight.getTimeArrival()) && Objects.equals(getReturnTimeDeparture(), flight.getReturnTimeDeparture()) && Objects.equals(getReturnTimeArrival(), flight.getReturnTimeArrival()) && Objects.equals(getFlightNumber(), flight.getFlightNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrigin(), getDestination(), getCustomers(), getTravelTimeHours(), getTimeDeparture(), getTimeArrival(), isWithReturnTicket(), getReturnTimeDeparture(), getReturnTimeArrival(), getNumberOfPassenger(), getTotalPrice(), getFlightNumber());
    }

    @Override
    public String toString() {
        return "Flight{" +
                "origin=" + origin +
                ", destination=" + destination +
                ", customers=" + customers +
                ", travelTimeHours=" + travelTimeHours +
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
}