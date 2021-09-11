package com.day9exercise.demo.Country;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Country")
@Table(name = "country", uniqueConstraints = {
        @UniqueConstraint(name = "country_name", columnNames = "country_name")
})
public class Country {
    @Id
    @SequenceGenerator(name="country_sequence",
            sequenceName = "country_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, generator = "country_sequence"
    )
    @Column(name = "id", updatable = false)
    private int id;
    @Column(name = "country_name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "estimated_travel", nullable = false)
    private int estimatedTravelMinutes;
    private double price;

    public Country(@JsonProperty("name") String name,
                   @JsonProperty("estimated_travel") int estimatedTravelMinutes, double price) {
        this.name = name;
        this.estimatedTravelMinutes = estimatedTravelMinutes;
        this.price = price;
    }

    public Country(String name) {
        this.name = name;
    }

    public Country() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEstimatedTravelMinutes() {
        return estimatedTravelMinutes;
    }

    public void setEstimatedTravelMinutes(int estimatedTravel) {
        this.estimatedTravelMinutes = estimatedTravelMinutes;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id && Double.compare(country.estimatedTravelMinutes, estimatedTravelMinutes) == 0 && Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, estimatedTravelMinutes);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", estimatedTravelMinutes=" + estimatedTravelMinutes +
                '}';
    }
}
