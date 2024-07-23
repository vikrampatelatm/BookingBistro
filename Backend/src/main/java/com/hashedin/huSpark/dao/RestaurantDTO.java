package com.hashedin.huSpark.dao;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class RestaurantDTO {

    private String name;
    private String cuisines;
    private String location;
    private ArrayList<Boolean> workingDays;
    private LocalDateTime OpenTime;
    private LocalDateTime CloseTime;
    private int timeSlotInterval;
    private float Rating;
    private Integer numberOfTables;
    private HashMap<Integer,Integer> typeOfTables;

    // Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<Boolean> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(ArrayList<Boolean> workingDays) {
        this.workingDays = workingDays;
    }

    public LocalDateTime getOpenTime() {
        return OpenTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        OpenTime = openTime;
    }

    public LocalDateTime getCloseTime() {
        return CloseTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        CloseTime = closeTime;
    }

    public int getTimeSlotInterval() {
        return timeSlotInterval;
    }

    public void setTimeSlotInterval(int timeSlotInterval) {
        this.timeSlotInterval = timeSlotInterval;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }

    public Integer getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(Integer numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public HashMap<Integer, Integer> getTypeOfTables() {
        return typeOfTables;
    }

    public void setTypeOfTables(HashMap<Integer, Integer> typeOfTables) {
        this.typeOfTables = typeOfTables;
    }
}