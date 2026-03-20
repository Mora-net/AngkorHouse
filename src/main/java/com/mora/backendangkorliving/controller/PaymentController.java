package com.mora.backendangkorliving.controller;

import com.mora.backendangkorliving.DTOs.request.PaymentRequest;
import com.mora.backendangkorliving.DTOs.response.PaymentRespone;
import com.mora.backendangkorliving.DTOs.response.PaymentResponse;
import com.mora.backendangkorliving.model.Payment;
import com.mora.backendangkorliving.service.PaymentService;
//import com.mora.backendangkorliving.service.QrPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponse payMonthly(@RequestBody PaymentRequest request) {
        return paymentService.payMonthly(request);
    }
    // ✅ Get all payments
    @GetMapping
    public List<PaymentRespone> getAllPayments() {
        return paymentService.getAllPayments();
    }
}
