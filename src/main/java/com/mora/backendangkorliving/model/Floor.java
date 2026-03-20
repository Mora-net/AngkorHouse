package com.mora.backendangkorliving.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "floors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Floor {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private String description;
//
//    // Floor price (base price for rooms on this floor)
//    private Double price;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    // Base price for rooms on this floor
    private Double price;

    // ✅ New field: number of rooms
    @Column(name = "number_of_rooms")
    private Integer numberOfRooms;
}

