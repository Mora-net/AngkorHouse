//package com.mora.backendangkorliving.service;
//
//import com.mora.backendangkorliving.Repository.PaymentRepository;
//import com.mora.backendangkorliving.Repository.RentalRepository;
//import com.mora.backendangkorliving.model.Payment;
//import com.mora.backendangkorliving.model.Rental;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//
//import kh.gov.nbc.bakong_khqr.BakongKHQR;
//import kh.gov.nbc.bakong_khqr.model.IndividualInfo;
//import kh.gov.nbc.bakong_khqr.model.KHQRCurrency;
//import kh.gov.nbc.bakong_khqr.model.KHQRData;
//import kh.gov.nbc.bakong_khqr.model.KHQRResponse;
//
//@Service
//public class QrPaymentService {
//
//    private final PaymentRepository paymentRepository;
//    private final RentalRepository rentalRepository;
//
//    public QrPaymentService(PaymentRepository paymentRepository, RentalRepository rentalRepository) {
//        this.paymentRepository = paymentRepository;
//        this.rentalRepository = rentalRepository;
//    }
//
//
//    public Payment generatePayment(Long rentalId) {
//        Rental rental = rentalRepository.findById(rentalId)
//                .orElseThrow(() -> new EntityNotFoundException("Rental not found"));
//
//        IndividualInfo info = new IndividualInfo();
//
//        info.setBakongAccountId("somora_march@bkrt");
//        info.setCurrency(KHQRCurrency.USD);
//        info.setAmount(0.01);
//        info.setMerchantName("AngkorLiving");
//        info.setMerchantCity("PhnomPenh");
//        info.setBillNumber("#RENTAL-" + rental.getId());
//        info.setPurposeOfTransaction("Monthly rental payment");
//
//        // ✅ Add expiration timestamp (e.g. 15 minutes from now)
//        info.setExpirationTimestamp(System.currentTimeMillis() + (15 * 60 * 1000));
//
//        KHQRResponse<KHQRData> response = BakongKHQR.generateIndividual(info);
//
//        if (response.getKHQRStatus().getCode() != 0) {
//            throw new RuntimeException("KHQR generation failed: " + response.getKHQRStatus().getMessage());
//        }
//
//        Payment payment = Payment.builder()
//                .rental(rental)
//                .amount(rental.getMonthlyRent())
//                .method("QR")
//                .status("PENDING")
//                .dueDate(LocalDate.now())
//                .qrCode(response.getData().getQr())
//                .qrMd5(response.getData().getMd5())
//                .build();
//
//        return paymentRepository.save(payment);
//    }
//
//    // Confirm payment by md5
//    public Payment confirmPayment(String md5) {
//        Payment payment = paymentRepository.findByQrMd5(md5);
//        if (payment == null) {
//            throw new EntityNotFoundException("Payment not found");
//        }
//        // TODO: integrate NBC API to verify transaction
//        payment.setStatus("PAID");
//        payment.setPaymentDate(LocalDate.now());
//        return paymentRepository.save(payment);
//    }
//}
