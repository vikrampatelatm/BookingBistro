package com.hashedin.huSpark.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Entity(name = "bookings")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

    @OneToOne
    Slot slots;

    private Integer Number_of_Guest;

    private String BookingDate;

    private String timeSlot;

    private String status;

    //getter and setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Slot getSlots() {
        return slots;
    }

    public void setSlots(Slot slots) {
        this.slots = slots;
    }

    public Integer getNumber_of_Guest() {
        return Number_of_Guest;
    }

    public void setNumber_of_Guest(Integer number_of_Guest) {
        Number_of_Guest = number_of_Guest;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
