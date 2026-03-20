package com.mora.backendangkorliving.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FloorResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer numberOfRooms;
}

