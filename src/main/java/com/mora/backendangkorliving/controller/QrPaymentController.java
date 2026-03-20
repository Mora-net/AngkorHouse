//package com.mora.backendangkorliving.controller;
//
//import com.mora.backendangkorliving.model.Payment;
//import com.mora.backendangkorliving.service.PaymentService;
//import com.mora.backendangkorliving.service.QrPaymentService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
////package com.mora.backendangkorliving.controller;
//
////import com.mora.backendangkorliving.model.Payment;
////import com.mora.backendangkorliving.service.PaymentService;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/payments")
//public class QrPaymentController {
//
//    private final QrPaymentService paymentService;
//
//    public QrPaymentController(QrPaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
//
//    // Generate QR Payment
//    @PostMapping("/generate")
//    public ResponseEntity<Map<String, Object>> generatePayment(@RequestParam Long rentalId) {
//        Payment payment = paymentService.generatePayment(rentalId);
//        return ResponseEntity.ok(Map.of(
//                "paymentId", payment.getId(),
//                "rentalId", rentalId,
//                "amount", payment.getAmount(),
//                "qr_code", payment.getQrCode(),
//                "qr_md5", payment.getQrMd5(),
//                "status", payment.getStatus()
//        ));
//    }
//
//    // Confirm Payment by md5
//    @PostMapping("/confirm")
//    public ResponseEntity<Map<String, Object>> confirmPayment(@RequestParam String md5) {
//        Payment payment = paymentService.confirmPayment(md5);
//        return ResponseEntity.ok(Map.of(
//                "paymentId", payment.getId(),
//                "status", payment.getStatus(),
//                "paymentDate", payment.getPaymentDate()
//        ));
//    }
//}
