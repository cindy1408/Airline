package com.day9exercise.demo.Flight;

import com.day9exercise.demo.Country.Country;
import com.day9exercise.demo.Customer.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Flight {
    private int id;
    private Country destination;
    private List<Customer> customers;
    private double travelTimeHours;
    private LocalDate timeDeparture;
    private LocalDate timeArrival;
    private boolean withReturnTicket;
    private LocalDate returnTimeDeparture;
    private LocalDate returnTimeArrival;
    private int numberOfPassenger;
    private double totalPrice;

    public Flight(int id, Country destination, List<Customer> customers, double travelTimeHours, LocalDate timeDeparture, LocalDate timeArrival, boolean withReturnTicket, LocalDate returnTimeDeparture, LocalDate returnTimeArrival, int numberOfPassenger, double totalPrice) {
        this.id = id;
        this.destination = destination;
        this.customers = customers;
        this.travelTimeHours = travelTimeHours;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
        this.withReturnTicket = withReturnTicket;
        this.returnTimeDeparture = returnTimeDeparture;
        this.returnTimeArrival = returnTimeArrival;
        this.numberOfPassenger = numberOfPassenger;
        this.totalPrice = totalPrice;
    }

    public Flight(int id, Country destination, List<Customer> customers, double travelTimeHours, LocalDate timeDeparture, LocalDate timeArrival, boolean withReturnTicket, int numberOfPassenger, double totalPrice) {
        this.id = id;
        this.destination = destination;
        this.customers = customers;
        this.travelTimeHours = travelTimeHours;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
        this.withReturnTicket = withReturnTicket;
        this.numberOfPassenger = numberOfPassenger;
        this.totalPrice = totalPrice;
    }

    public Flight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(LocalDate timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public LocalDate getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(LocalDate timeArrival) {
        this.timeArrival = timeArrival;
    }

    public boolean isWithReturnTicket() {
        return withReturnTicket;
    }

    public void setWithReturnTicket(boolean withReturnTicket) {
        this.withReturnTicket = withReturnTicket;
    }

    public LocalDate getReturnTimeDeparture() {
        return returnTimeDeparture;
    }

    public void setReturnTimeDeparture(LocalDate returnTimeDeparture) {
        this.returnTimeDeparture = returnTimeDeparture;
    }

    public LocalDate getReturnTimeArrival() {
        return returnTimeArrival;
    }

    public void setReturnTimeArrival(LocalDate returnTimeArrival) {
        this.returnTimeArrival = returnTimeArrival;
    }

    public int getNumberOfPassenger() {
        return numberOfPassenger;
    }

    public void setNumberOfPassenger(int numberOfPassenger) {
        this.numberOfPassenger = numberOfPassenger;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && Double.compare(flight.travelTimeHours, travelTimeHours) == 0 && withReturnTicket == flight.withReturnTicket && numberOfPassenger == flight.numberOfPassenger && Double.compare(flight.totalPrice, totalPrice) == 0 && Objects.equals(destination, flight.destination) && Objects.equals(customers, flight.customers) && Objects.equals(timeDeparture, flight.timeDeparture) && Objects.equals(timeArrival, flight.timeArrival) && Objects.equals(returnTimeDeparture, flight.returnTimeDeparture) && Objects.equals(returnTimeArrival, flight.returnTimeArrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destination, customers, travelTimeHours, timeDeparture, timeArrival, withReturnTicket, returnTimeDeparture, returnTimeArrival, numberOfPassenger, totalPrice);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
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
                '}';
    }
}
