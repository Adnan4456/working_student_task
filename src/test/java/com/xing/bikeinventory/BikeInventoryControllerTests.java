package com.xing.bikeinventory;

import com.xing.bikeinventory.model.Bike;
import com.xing.bikeinventory.service.InventoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;


@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = BikeInventoryApplication.class)
public class BikeInventoryControllerTests {

    //Inject InventoryService class.
    @Autowired
    InventoryService inventoryService;

    @Test
    @Order(1)
    @DisplayName("Test to add bike record")  //Tag on test result
    public void testAddRecord()
    {
        // Using builder method to create object.
        Bike oBike =  Bike.builder()
                .id(1L)
                .brand("BMW")
                .color("Black")
                .size("Large")
                .numberOfGears(6)
                .build();

        //Getting the saved in object return  from  save function
        Bike saved = inventoryService.addBike(oBike);

        //checking that object is  not null
        System.out.println(saved.getId());
        Assert.assertNotNull(saved);
    }

    @Test
    @Order(2)
    @DisplayName("Test  null values are not added")  //Tag on test result
    public void testNullObjectAdd()
    {
     //it should throw exception because i am validating api.
        // Using builder method to create object.
        Bike oBike =  Bike.builder()
                .id(1L)
                .brand("BMW")
                .size("Large")
                .numberOfGears(6)
                .build();

        try {
            //Getting the saved in object return  from  save function
            Bike saved = inventoryService.addBike(oBike);

            //checking that object is  not null
            System.out.println(saved.getId());
            Assert.assertNotNull(saved);
        }
        catch (Exception e)
        {
            System.out.println("Value is null.Therefore bike object is not added into database.");
        }

    }
    @Test
    @Order(3)
    @DisplayName("Test to get Single bike record ")  //Tag on test result
    public void testSingleRecordFound()
    {
        // Using builder method to create object.
        Bike oBike =  Bike.builder()
                .id(1L)
                .brand("BMW")
                .color("Black")
                .size("Large")
                .numberOfGears(6)
                .build();

        //Mock the service layer.This is dummy data for testing.
        //Not save in actual database.
        inventoryService.addBike(oBike);

         //Get that record
         Bike object = inventoryService.getBike(1L);
         Assert.assertNotNull(object);
    }


    @Test
    @Order(4)
    @DisplayName("Test Record is not present")  //Tag on test result
    public void testSingleRecordNotFound()
    {
        //This test should be failed , because id 2 is not present.
        //Throw exception record not found.

        // Using builder method to create object.
        Bike oBike =  Bike.builder()
                .id(1L)
                .brand("BMW")
                .color("Black")
                .size("Large")
                .numberOfGears(6)
                .build();

        //Mock the service layer.This is dummy data for testing.
        //Not save in actual database.
        inventoryService.addBike(oBike);

        //NullPointerException handled.
        try {
            //No record found. Id is different.
            Bike object = inventoryService.getBike(2L);
            //this object should be null  and throw exception.
            Assert.assertNull(object);
        }catch (Exception e)
        {
            System.out.println("Record not found.");
        }
    }

    @Test
    @Order(5)
    @DisplayName("Test  to get All bike records ")  //Tag on test result
    public void testGetAllRecord()
    {
        // Using builder method to create object.
       Bike oBike =  Bike.builder()
                .id(1L)
                .brand("BMW")
                        .color("Black")
                                .size("Large")
                                        .numberOfGears(6)
                                                .build();
        //Mock the service layer.This is dummy data for testing.
        //Not save in actual database.
        inventoryService.addBike(oBike);

        List<Bike> bikeList = inventoryService.getAllBikes();

        //printing the list of bikes.
        for (Bike list: bikeList)
        {
            System.out.println(oBike);
        }
        //Method to check list size.
        assertThat(bikeList).size().isGreaterThan(0);
    }

    @Test
    @Order(6)
    @DisplayName("Test Record is not present. ")  //Tag on test result
    public void testNotGetAllRecord()
    {
        //Handling NullpointerException.
        try {
            List<Bike> bikeList = inventoryService.getAllBikes();
            //Not find any record.Test will be failed.
            assertThat(bikeList).size().isGreaterThan(0);
        }
        catch (Exception e)
        {
            //Test will failed
            // throw exception and print this message
            System.out.println("Records are not found.");
        }
    }

    @Test
    @Order(7)
    @DisplayName("Test update bike record ")  //Tag on test result
    public void testUpdateRecord()
    {
        // Using builder method to create object.
        Bike oBike =  Bike.builder()
                .id(1L)
                    .brand("BMW")
                        .color("Black")
                    .       size("Large")
                    .       numberOfGears(6)
                    .           build();
        //Mock the service layer.This is dummy data for testing.
        //Not save in actual database.
        inventoryService.addBike(oBike);
        Bike saveObject = inventoryService.getBike(1L);
        saveObject.setId(2l);

        //save updated object
        inventoryService.addBike(saveObject);

        //comparing ID of bike.
        assertThat(saveObject.getId()).isEqualTo(2L);
    }

    @Test
    @Order(8)
    @DisplayName("Test not update bike record ")  //Tag on test result
    public void testNotUpdateRecord()
    {
        //This test should fail. becuase i am not updating Id of bike

        // Using builder method to create object.
        Bike oBike =  Bike.builder()
                .id(1L)
                .brand("BMW")
                .color("Black")
                .size("Large")
                .numberOfGears(6)
                .build();

        //Mock the service layer.This is dummy data for testing.
        //Not save in actual database.
        inventoryService.addBike(oBike);

        try {
            //getting object to update their color.
            Bike saveObject = inventoryService.getBike(1L);
            saveObject.setColor("White");
            //save updated object
            inventoryService.addBike(saveObject);

           //comparing ID of bike.
            assertThat(saveObject.getId()).isEqualTo(2L);
        }
        catch (Exception e)
        {
            System.out.println("AssertionFailedError.Because updating color and checking ID ");
        }
    }

    @Test
    @Order(9)
    @DisplayName("Test  delete bike record ")  //Tag on test result
    public void testDeleteRecord()
    {
        // Using builder method to create object.
        Bike oBike =  Bike.builder()
                .id(1L)
                .brand("BMW")
                .color("Black")
                .size("Large")
                .numberOfGears(6)
                .build();

        //Mock the service layer.This is dummy data for testing.
        //Not save in actual database.
        inventoryService.addBike(oBike);

        //getting object to delete.
        Bike object = inventoryService.getBike(1L);
        inventoryService.deleteBike(object.getId());

        //Again getting object to check it is deleted or not
        //it should throw NoSuchElementException if record id deleted.
        //Handling exceptions.
        try {
            this. inventoryService.getBike(1L);
        }catch (Exception e)
        {
            System.out.println("Record is deleted. Therefore NoSuchElementException.");
        }

    }
}
