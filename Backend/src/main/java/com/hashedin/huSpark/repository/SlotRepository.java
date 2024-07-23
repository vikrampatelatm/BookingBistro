package com.hashedin.huSpark.repository;

import com.hashedin.huSpark.model.Restaurant;
import com.hashedin.huSpark.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
}
