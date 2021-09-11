package com.day9exercise.demo.FlightsAvailable;
//The flights available package contains the logic regarding the flights which are available and the details of those flights, and how many tickets are available
import com.day9exercise.demo.Country.Country;

import java.time.LocalDateTime;
import java.util.Objects;

public class FlightsAvailable {
    private Country destination;
    private LocalDateTime dateTimeOfDeparture;
    private LocalDateTime dateTimeOfArrival;
    private double pricePerPerson;
    private int numOfTicketsAvailable;

    public FlightsAvailable(Country destination, LocalDateTime dateTimeOfDeparture, LocalDateTime dateTimeOfArrival, double pricePerPerson, int numOfTicketsAvailable) {
        this.destination = destination;
        this.dateTimeOfDeparture = dateTimeOfDeparture;
        this.dateTimeOfArrival = dateTimeOfArrival;
        this.pricePerPerson = pricePerPerson;
        this.numOfTicketsAvailable = numOfTicketsAvailable;
    }

    public FlightsAvailable() {
    }

    public FlightsAvailable(String spain, LocalDateTime of) {
    }

    public Country getDestination() {
        return destination;
    }

    public void setDestination(Country destination) {
        this.destination = destination;
    }

    public LocalDateTime getDateTimeOfDeparture() {
        return dateTimeOfDeparture;
    }

    public void setDateTimeOfDeparture(LocalDateTime dateTimeOfDeparture) {
        this.dateTimeOfDeparture = dateTimeOfDeparture;
    }

    public LocalDateTime getDateTimeOfArrival() {
        return dateTimeOfArrival;
    }

    public void setDateTimeOfArrival(LocalDateTime dateTimeOfArrival) {
        this.dateTimeOfArrival = dateTimeOfArrival;
    }

    public double getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public int getNumOfTicketsAvailable() {
        return numOfTicketsAvailable;
    }

    public void setNumOfTicketsAvailable(int numOfTicketsAvailable) {
        this.numOfTicketsAvailable = numOfTicketsAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightsAvailable that = (FlightsAvailable) o;
        return Double.compare(that.pricePerPerson, pricePerPerson) == 0 && numOfTicketsAvailable == that.numOfTicketsAvailable && destination.equals(that.destination) && dateTimeOfDeparture.equals(that.dateTimeOfDeparture) && dateTimeOfArrival.equals(that.dateTimeOfArrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, dateTimeOfDeparture, dateTimeOfArrival, pricePerPerson, numOfTicketsAvailable);
    }

    @Override
    public String toString() {
        return "FlightsAvailable{" +
                "destination=" + destination +
                ", dateTimeOfDeparture=" + dateTimeOfDeparture +
                ", dateTimeOfArrival=" + dateTimeOfArrival +
                ", pricePerPerson=" + pricePerPerson +
                ", numOfTicketsAvailable=" + numOfTicketsAvailable +
                '}';
    }
}


