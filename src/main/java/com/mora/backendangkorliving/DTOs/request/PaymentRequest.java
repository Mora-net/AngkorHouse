package com.mora.backendangkorliving.DTOs.request;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long rentalId;
    private Double amount;
    private String method; // CASH or QR
}
