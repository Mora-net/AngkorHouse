//package com.mora.backendangkorliving.controller;
//
//import com.mora.backendangkorliving.service.BakongApiClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/payments")
//public class KhqrContoller {
//    private final BakongApiClient bakongApiClient;
//
//    public KhqrContoller(BakongApiClient bakongApiClient) {
//        this.bakongApiClient = bakongApiClient;
//    }
//
//    // Generate KHQR Deeplink
//    @PostMapping("/khqr")
//    public ResponseEntity<Map<String, Object>> generateKhqr(@RequestParam Double amount) {
//        Map<String, Object> qrData = bakongApiClient.generateDeeplink(
//                "SAMPLE_QR_STRING", // TODO: replace with actual QR string
//                "https://yourapp.com/icon.png",
//                "AngkorLiving",
//                "https://yourapp.com/callback"
//        );
//        return ResponseEntity.ok(qrData);
//    }
//
//    // Check Transaction by MD5
//    @GetMapping("/check/md5")
//    public ResponseEntity<Map<String, Object>> checkTransactionByMd5(@RequestParam String md5) {
//        Map<String, Object> result = bakongApiClient.checkTransactionByMd5(md5);
//        return ResponseEntity.ok(result);
//    }
//
//    // Check Transaction by Full Hash
//    @GetMapping("/check/hash")
//    public ResponseEntity<Map<String, Object>> checkTransactionByHash(@RequestParam String hash) {
//        Map<String, Object> result = bakongApiClient.checkTransactionByHash(hash);
//        return ResponseEntity.ok(result);
//    }
//
//    // Check Transaction by Short Hash
//    @GetMapping("/check/short")
//    public ResponseEntity<Map<String, Object>> checkTransactionByShortHash(
//            @RequestParam String hash,
//            @RequestParam Double amount,
//            @RequestParam String currency) {
//        Map<String, Object> result = bakongApiClient.checkTransactionByShortHash(hash, amount, currency);
//        return ResponseEntity.ok(result);
//    }
//
//    // Check Bakong Account
//    @GetMapping("/account")
//    public ResponseEntity<Map<String, Object>> checkBakongAccount(@RequestParam String accountId) {
//        Map<String, Object> result = bakongApiClient.checkBakongAccount(accountId);
//        return ResponseEntity.ok(result);
//    }
//}
