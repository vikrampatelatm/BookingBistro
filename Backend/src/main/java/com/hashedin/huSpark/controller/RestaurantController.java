package com.hashedin.huSpark.controller;

import com.hashedin.huSpark.dao.RestaurantDTO;
import com.hashedin.huSpark.model.Restaurant;
import com.hashedin.huSpark.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        //System.out.println(id);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping("/time/{id}")
    public  List<String> getSlotById(@PathVariable Long id) {
        System.out.println(id);
        List<String>  slots = restaurantService.generateTimeSlots(id);
        //System.out.println(id);
        return slots;
    }

    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        Restaurant savedRestaurant = restaurantService.create(restaurantDTO);
        return ResponseEntity.ok(savedRestaurant);
    }

    @GetMapping("/All")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }
}
