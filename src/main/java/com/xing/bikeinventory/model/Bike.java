package com.xing.bikeinventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
 import javax.validation.constraints.NotNull;

// @Data is a Lombok annotation that generates Getter, Setter, toString, equals and a constructor to a class
// More information here: https://projectlombok.org/features/Data


// @Builder lets you automatically produce the code required to have your class be instantiable with code such as:
// Person.builder()
//       .name("Adam Savage")
//       .city("San Francisco")
//       .job("Mythbusters")
//       .job("Unchained Reaction")
//       .build();
// More information here: https://projectlombok.org/features/Builder

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bike {

    //Id will auto increment in H2 database.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Brand")//Column name in database
    @NotNull(message = "Brand of Bike is mandatory")
    private String brand;

    @Column(name = "Color")//Column name in database
    @NotNull(message = "Bike color is mandatory")
    private String color;

    //TODO: add other fields

    @Column(name = "Size")//Column name in database
    @NotNull(message = "Size of Bike is mandatory")
    private String size;

    @Column(name = "Gears")//Column name in database
    @NotNull(message = "Number of gears is mandatory")
    private int numberOfGears;


}
