package com.mora.backendangkorliving.DTOs.request;

//package com.mora.backendangkorliving.dto.request;

import lombok.Data;
import java.time.LocalDate;


@Data
public class RentalRequest {
    private Long roomId;
    private Long tenantId;
    private LocalDate startDate;
    // endDate not required → system auto set = startDate + 30 days
}


