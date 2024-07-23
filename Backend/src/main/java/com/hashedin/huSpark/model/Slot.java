package com.hashedin.huSpark.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;



@Getter
@Setter
@Entity(name = "slots")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Slot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "id")
    private Long id;


    HashMap<LocalDateTime, Integer> remainingBookings = new HashMap<>();


    HashMap<LocalDateTime, List<RemainingSeats>> mpRem = new HashMap<>();

    //getter and setter


}
