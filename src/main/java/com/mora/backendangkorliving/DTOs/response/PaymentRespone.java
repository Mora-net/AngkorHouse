package com.mora.backendangkorliving.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRespone {
    private Long id;
    private String tenantName;
    private String roomNumber;
    private Double amount;
    private String status;   // PAID / PENDING
    private String date;
}
