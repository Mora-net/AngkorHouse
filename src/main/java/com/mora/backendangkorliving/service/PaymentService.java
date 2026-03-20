package com.mora.backendangkorliving.service;

import com.mora.backendangkorliving.DTOs.request.PaymentRequest;
import com.mora.backendangkorliving.DTOs.response.PaymentRespone;
import com.mora.backendangkorliving.DTOs.response.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse payMonthly(PaymentRequest request);
    // ✅ New method to get all payments
//    List<PaymentResponse> getAllPayments();
//    // ✅ Get payment by ID
//    PaymentResponse getPaymentById(Long id);
    List<PaymentRespone> getAllPayments();
}

