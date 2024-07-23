package com.hashedin.huSpark.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;


@Getter
@Setter
@Entity(name = "remaining_seats")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class RemainingSeats implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Long id;


    int seatType;
    int remSeats;


    public RemainingSeats(int seatType, int remSeats) {
        this.seatType = seatType;
        this.remSeats = remSeats;
    }


    public int getSeatType() {
        return seatType;
    }


    public int getRemSeats() {
        return remSeats;
    }


}
