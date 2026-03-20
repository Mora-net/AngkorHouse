//package com.mora.backendangkorliving.service;
//
////package com.mora.backendangkorliving.service.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mora.backendangkorliving.DTOs.request.BakongRequest;
//import com.mora.backendangkorliving.DTOs.response.BakongResponse;
//import com.mora.backendangkorliving.DTOs.request.CheckTransactionRequest;
//import com.mora.backendangkorliving.model.Payment;
//import com.mora.backendangkorliving.model.Rental;
//import com.mora.backendangkorliving.Repository.RentalRepository;
//import com.mora.backendangkorliving.service.BakongService;
//import com.mora.backendangkorliving.service.BakongTokenService;
//import jakarta.persistence.EntityNotFoundException;
//import kh.gov.nbc.bakong_khqr.BakongKHQR;
//import kh.gov.nbc.bakong_khqr.model.KHQRCurrency;
//import kh.gov.nbc.bakong_khqr.model.KHQRData;
//import kh.gov.nbc.bakong_khqr.model.KHQRResponse;
//import kh.gov.nbc.bakong_khqr.model.IndividualInfo;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClient;
//
//import java.time.LocalDate;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class BakongSeviceImpl implements BakongService {
//
//    @Value("${bakong.account-id}")
//    private String bakongAccountId;
//
//    @Value("${bakong.base-url}")
//    private String baseUrl;
//
//    private final RentalRepository rentalRepository;
//    private final BakongTokenService bakongTokenService;
//    private final ObjectMapper mapper;
//    private final RestClient restClient = RestClient.create();
//
//    @Override
//    public KHQRResponse<KHQRData> generateQR(BakongRequest request) {
//        Rental rental = rentalRepository.findById(request.getRentalId())
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
////        Payment payment = Payment.builder()
////                .rental(rental)
////                .amount(rental.getMonthlyRent())
////                .method("QR")
////                .status("PENDING")
////                .dueDate(LocalDate.now())
////                .qrCode(response.getData().getQr())
////                .qrMd5(response.getData().getMd5())
////                .build();
////
////        return paymentRepository.save(payment);
//        return BakongKHQR.generateIndividual(info);
//    }
//
//    @Override
//    public byte[] getQRImage(KHQRData qr) {
//        try {
//            if (qr == null || qr.getQr() == null || qr.getQr().isBlank()) {
//                return "Invalid QR data".getBytes();
//            }
//            return qr.getQr().getBytes(); // simplified: return raw QR string as bytes
//        } catch (Exception e) {
//            return ("Error: " + e.getMessage()).getBytes();
//        }
//    }
//
//    @Override
//    public BakongResponse checkTransactionByMD5(CheckTransactionRequest request) {
//        String bearerToken = bakongTokenService.getToken();
//        String url = baseUrl.replaceAll("/+$", "") + "/v1/check_transaction_by_md5";
//
//        String responseBody = restClient.post()
//                .uri(url)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
//                .body(Map.of("md5", request.getMd5()))
//                .retrieve()
//                .body(String.class);
//
//        log.info("Response from Bakong API: {}", responseBody);
//
//        try {
//            return mapper.readValue(responseBody, BakongResponse.class);
//        } catch (Exception e) {
//            throw new RuntimeException("Invalid upstream response", e);
//        }
//    }
//}
//
