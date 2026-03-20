package com.mora.backendangkorliving.service;

import com.mora.backendangkorliving.DTOs.request.PaymentRequest;
import com.mora.backendangkorliving.DTOs.response.PaymentRespone;
import com.mora.backendangkorliving.DTOs.response.PaymentResponse;
import com.mora.backendangkorliving.Repository.PaymentRepository;
import com.mora.backendangkorliving.Repository.RentalRepository;
import com.mora.backendangkorliving.model.Payment;
import com.mora.backendangkorliving.model.Rental;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RentalRepository rentalRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, RentalRepository rentalRepository) {
        this.paymentRepository = paymentRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public PaymentResponse payMonthly(PaymentRequest request) {
        Rental rental = rentalRepository.findById(request.getRentalId())
                .orElseThrow(() -> new EntityNotFoundException("Rental not found"));

        // Find latest payment for this rental
        Payment latestPayment = paymentRepository.findTopByRentalOrderByDueDateDesc(rental)
                .orElseThrow(() -> new EntityNotFoundException("No payment record found"));

        // Update latest payment to PAID
        latestPayment.setPaymentDate(LocalDate.now());
        latestPayment.setMethod(request.getMethod());
        latestPayment.setStatus("PAID");
        paymentRepository.save(latestPayment);

        // Extend rental endDate by 30 days
        rental.setEndDate(rental.getEndDate().plusDays(30));
        rentalRepository.save(rental);

        // Generate next payment record
        Payment nextPayment = Payment.builder()
                .rental(rental)
                .dueDate(rental.getEndDate())
                .amount(rental.getMonthlyRent())
                .status("PENDING")
                .build();
        paymentRepository.save(nextPayment);

        return new PaymentResponse(latestPayment.getId(), latestPayment.getDueDate(),
                latestPayment.getPaymentDate(), latestPayment.getAmount(),
                latestPayment.getMethod(), latestPayment.getStatus());
    }
    @Override
    public List<PaymentRespone> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //Helper method to convert entity → DTO
    private PaymentRespone mapToResponse(Payment p) {
        return new PaymentRespone(
                p.getId(),
                p.getRental().getTenant().getUsername(),   // tenantName from Rental entity
                p.getRental().getRoom().getRoomNumber(),
                p.getAmount(),
                p.getStatus(),
                p.getPaymentDate() != null ? p.getPaymentDate().toString() : null
        );
    }
}

