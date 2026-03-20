package com.mora.backendangkorliving.service;

import com.mora.backendangkorliving.DTOs.request.FloorRequest;
import com.mora.backendangkorliving.DTOs.response.FloorResponse;

import java.util.List;

public interface FloorService {
    FloorResponse createFloor(FloorRequest request);
    List<FloorResponse> getAllFloors();
    FloorResponse getFloorById(Long id);
    FloorResponse updateFloor(Long id, FloorRequest request);
    void deleteFloor(Long id);
}

