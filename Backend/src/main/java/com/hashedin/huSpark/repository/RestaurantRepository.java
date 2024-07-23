package com.hashedin.huSpark.repository;

import com.hashedin.huSpark.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // Additional query methods can be defined here as needed
}
