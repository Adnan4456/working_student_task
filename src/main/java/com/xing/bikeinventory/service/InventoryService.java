package com.xing.bikeinventory.service;


import com.xing.bikeinventory.model.Bike;
import java.util.List;

public interface InventoryService {

    public List<Bike> getAllBikes();
    public Bike getBike(long bikeId);
    public Bike addBike(Bike bikeObject);
    public Bike updateBike(Bike updateBike);
    public boolean deleteBike(long bikeId);
}
