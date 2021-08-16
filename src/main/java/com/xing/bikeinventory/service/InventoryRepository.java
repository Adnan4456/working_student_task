package com.xing.bikeinventory.service;

import com.xing.bikeinventory.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Bike, Long>{

}
