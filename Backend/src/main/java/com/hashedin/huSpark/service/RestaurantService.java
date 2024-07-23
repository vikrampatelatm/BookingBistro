package com.hashedin.huSpark.service;

import com.hashedin.huSpark.dao.CapacityDTO;
import com.hashedin.huSpark.dao.RestaurantDTO;
import com.hashedin.huSpark.model.Restaurant;
import com.hashedin.huSpark.model.RestaurantTable;
import com.hashedin.huSpark.repository.RestaurantRepository;
import com.hashedin.huSpark.repository.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class RestaurantService{

    @Autowired
    RestaurantRepository restaurantRepository;


    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        System.out.println("in service");
        return restaurants;
    }


    public Restaurant getRestaurantById(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }



    public List<String> generateTimeSlots(Long id) {
        List<LocalTime> slots = new ArrayList<>();
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(id);

            Restaurant restaurant = restaurantOpt.get();
            LocalTime now = LocalTime.now();
            LocalTime startTime = restaurant.getOpenTime().toLocalTime();
            LocalTime endTime = restaurant.getCloseTime().toLocalTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

            while (startTime.isBefore(endTime)) {
                    slots.add(startTime);
                    startTime = startTime.plusMinutes(restaurant.getTimeSlotInterval());
            }

           List<String> slots2 = new ArrayList<>();

            for(int i=0;i<slots.size();i++){
                if(slots.get(i).isAfter(now)){
                    slots2.add(slots.get(i).toString());
                }
            }
        return slots2;
    }

    public Long getCapcity(HashMap<Integer,Integer> typeOfTables){
        long capacity = 0;
        for(Map.Entry<Integer, Integer> entry : typeOfTables.entrySet()){
            capacity += entry.getKey() * entry.getValue();
        }
        return capacity;
    }

    public Restaurant create(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setRating(restaurantDTO.getRating());
        restaurant.setCuisines(restaurantDTO.getCuisines());
        restaurant.setLocation(restaurantDTO.getLocation());
        restaurant.setOpenTime(restaurantDTO.getOpenTime());
        restaurant.setCloseTime(restaurantDTO.getCloseTime());
        restaurant.setTimeSlotInterval(restaurantDTO.getTimeSlotInterval());
        Long c =  getCapcity(restaurantDTO.getTypeOfTables());
        restaurant.setCapacity(c);
        restaurant.setWorkDays((restaurantDTO.getWorkingDays()));

        List<RestaurantTable> tables = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : restaurantDTO.getTypeOfTables().entrySet()) {
            RestaurantTable table = new RestaurantTable();
            table.setTableType(entry.getKey());
            table.setNumTables(entry.getValue());
            table.setRestaurant(restaurant);
            tables.add(table);
        }
        restaurant.setTables(tables);
        return restaurantRepository.save(restaurant);
    }
}


