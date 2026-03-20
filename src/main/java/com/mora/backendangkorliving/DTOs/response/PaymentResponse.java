package com.mora.backendangkorliving.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PaymentResponse {
    private Long id;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private Double amount;
    private String method;
    private String status;
}

