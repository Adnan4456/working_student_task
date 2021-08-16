package com.xing.bikeinventory.controller;


import com.xing.bikeinventory.model.Bike;
import com.xing.bikeinventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
public class InventoryController {

    //Inject InventoryService object.
    @Autowired
    private InventoryService inventoryService;

    // TODO add other REST endpoints

    //Add bike object to database H2 using POST method.
    @PostMapping(value = "/bike/new")
    public Bike addBike(@Valid @RequestBody  Bike oBike)
    {
        return this.inventoryService.addBike(oBike);
    }

    //fetching all bike records from database using GET method.
    @GetMapping(value = "/bike/all")
    public List<Bike> getAllBikes() {
        return this.inventoryService.getAllBikes();
    }

    //fetching a single bike record using GET method.
    @GetMapping(value = "/bike/{bikeId}")
    public   Bike  getBike(@PathVariable long bikeId) {
        return this.inventoryService.getBike(bikeId);
    }

    //update bike record
    @PutMapping("/bike/update")
    public Bike updateBike(@Valid @RequestBody Bike oBike)
    {
        return this.inventoryService.updateBike(oBike);
    }

    //Delete bike record from database
    @DeleteMapping("/bike/{bikeId}")
    public String delete(@PathVariable long bikeId)
    {
        if(this.inventoryService.deleteBike(bikeId));
        {
            return "Record is deleted";
        }
    }
}
