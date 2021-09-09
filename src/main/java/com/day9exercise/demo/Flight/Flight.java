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
    private int numberOfPassenger;
    private double totalPrice;

    public Flight(Country destination, List<Customer> customers, double travelTimeHours, LocalDate timeDeparture, LocalDate timeArrival, double totalPrice) {
        this.destination = destination;
        this.customers = customers;
        this.travelTimeHours = travelTimeHours;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
        this.numberOfPassenger = this.customers.size();
        this.totalPrice = totalPrice;
    }

    public Flight() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Double.compare(flight.travelTimeHours, travelTimeHours) == 0 && numberOfPassenger == flight.numberOfPassenger && Double.compare(flight.totalPrice, totalPrice) == 0 && Objects.equals(destination, flight.destination) && Objects.equals(customers, flight.customers) && Objects.equals(timeDeparture, flight.timeDeparture) && Objects.equals(timeArrival, flight.timeArrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, customers, travelTimeHours, timeDeparture, timeArrival, numberOfPassenger, totalPrice);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "destination=" + destination +
                ", customers=" + customers +
                ", travelTimeHours=" + travelTimeHours +
                ", timeDeparture=" + timeDeparture +
                ", timeArrival=" + timeArrival +
                ", numberOfPassenger=" + numberOfPassenger +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
