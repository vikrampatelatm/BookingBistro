package com.hashedin.huSpark.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private float Rating;

    private String cuisines;

    private String location;

    private LocalDateTime openTime;

    private LocalDateTime closeTime;

    private int timeSlotInterval;

    private Long capacity;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RestaurantTable> tables = new ArrayList<>();

    @OneToOne
    Slot slots;


    private LocalDateTime registrationDate;

   private ArrayList<Boolean> workDays = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        registrationDate = LocalDateTime.now();
    }

    public List<LocalTime> generateTimeSlots() {
        List<LocalTime> slots = new ArrayList<>();
        LocalTime now = LocalTime.now();
        LocalTime startTime = openTime.toLocalTime();
        LocalTime endTime = closeTime.toLocalTime();


        if (now.isAfter(startTime)) {
            startTime = now.truncatedTo(ChronoUnit.MINUTES);
        }

        while (startTime.isBefore(endTime)) {
            slots.add(startTime);
            startTime = startTime.plusMinutes(timeSlotInterval);
        }

        return slots;
    }

    // Getters and Setters


    public ArrayList<Boolean> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(ArrayList<Boolean> workDays) {
        this.workDays = workDays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public int getTimeSlotInterval() {
        return timeSlotInterval;
    }

    public void setTimeSlotInterval(int timeSlotInterval) {
        this.timeSlotInterval = timeSlotInterval;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public List<RestaurantTable> getTables() {
        return tables;
    }

    public void setTables(List<RestaurantTable> tables) {
        this.tables = tables;
    }

    public Slot getSlots() {
        return slots;
    }

    public void setSlots(Slot slots) {
        this.slots = slots;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }


}
