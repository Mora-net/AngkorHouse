package com.mora.backendangkorliving.service;

//package com.mora.backendangkorliving.service;

import com.mora.backendangkorliving.DTOs.request.RentalRequest;
import com.mora.backendangkorliving.DTOs.response.RentalResponse;

import java.util.List;

public interface RentalService {
    RentalResponse createRental(RentalRequest request);
    RentalResponse getRental(Long id);
    List<RentalResponse> getAllRentals();
    void deleteRental(Long id);
}


