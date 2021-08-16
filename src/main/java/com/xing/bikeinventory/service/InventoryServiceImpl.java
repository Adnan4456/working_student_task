package com.xing.bikeinventory.service;

import com.xing.bikeinventory.model.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class InventoryServiceImpl implements InventoryService {

    //Note: implements with InventoryService interface because we want runtime polymorphism


    //inject InventoryRepository class variable
    @Autowired
    private InventoryRepository inventoryRepository;

    // TODO: add other methods to handle controller requests

    //fetching all bike records.
    @Override
    public List<Bike> getAllBikes() {
        List<Bike> lBikes = this.inventoryRepository.findAll();
        if (lBikes.size()>0)
        {
            return lBikes;
        }
        //if Record is not found throw exception to GlobalExceptionhandler class
        throw new RuntimeException("No record is present in database.");
    }


    //fetching single record.
      @Override
      public Bike getBike(long bikeId) {
          Optional<Bike>  bike = Optional.of(inventoryRepository.findById(bikeId).get());
          //if bike record exists, it returns object.
         if (bike.isPresent()){
             return bike.get();
         }
         //if Record is not found throw exception to GlobalExceptionhandler class
          throw new RuntimeException("Record is not present in database.");
      }

      //Add bike object.
    @Override
    public Bike addBike(Bike oBike) {
       return this.inventoryRepository.save(oBike);
    }

    @Override
    public Bike updateBike(Bike updateBike) {

        //first check that record is present in database.
         getBike(updateBike.getId());
         //if record is present then update it.
         return  this.inventoryRepository.save(updateBike);
    }

    @Override
    public boolean deleteBike(long bikeId) {
        //check record is present in database.
        //if no, then throw exception in this function.
         Bike oBike = getBike(bikeId);

         // if yes then delete and return true .
         this.inventoryRepository.delete(oBike);
         return true;
    }
}
