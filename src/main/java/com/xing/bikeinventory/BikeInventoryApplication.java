package com.xing.bikeinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
// for documentation of API using  swagger2 library
@EnableSwagger2
public class BikeInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikeInventoryApplication.class, args);
    }
}
