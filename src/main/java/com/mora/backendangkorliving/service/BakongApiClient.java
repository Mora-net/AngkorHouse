//package com.mora.backendangkorliving.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class BakongApiClient {
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @Value("${NBC_API_URL}")
//    private String apiUrl;
//
//    @Value("${BAKONG_API_TOKEN}")
//    private String apiToken;
//
//    private HttpHeaders defaultHeaders(boolean withAuth) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        if (withAuth) {
//            headers.setBearerAuth(apiToken);
//        }
//        return headers;
//    }
//
//    // 1. Request Token
//    public Map<String, Object> requestToken(String email, String organization, String project) {
//        String url = apiUrl + "/v1/request_token";
//        Map<String, Object> payload = Map.of(
//                "email", email,
//                "organization", organization,
//                "project", project
//        );
//        return post(url, payload, false);
//    }
//
//    // 2. Verify Token
//    public Map<String, Object> verifyToken(String code) {
//        String url = apiUrl + "/v1/verify";
//        Map<String, Object> payload = Map.of("code", code);
//        return post(url, payload, false);
//    }
//
//    // 3. Renew Token
//    public Map<String, Object> renewToken(String email) {
//        String url = apiUrl + "/v1/renew_token";
//        Map<String, Object> payload = Map.of("email", email);
//        return post(url, payload, false);
//    }
//
//    // 4. Generate Deeplink
//    public Map<String, Object> generateDeeplink(String qr, String appIconUrl, String appName, String callbackUrl) {
//        String url = apiUrl + "/v1/generate_deeplink_by_qr";
//        Map<String, Object> sourceInfo = Map.of(
//                "appIconUrl", appIconUrl,
//                "appName", appName,
//                "appDeepLinkCallback", callbackUrl
//        );
//        Map<String, Object> payload = new HashMap<>();
//        payload.put("qr", qr);
//        payload.put("sourceInfo", sourceInfo);
//        return post(url, payload, true);
//    }
//
//    // 5. Check Transaction by MD5
//    public Map<String, Object> checkTransactionByMd5(String md5) {
//        String url = apiUrl + "/v1/check_transaction_by_md5";
//        Map<String, Object> payload = Map.of("md5", md5);
//        return post(url, payload, true);
//    }
//
//    // 6. Check Transaction by Full Hash
//    public Map<String, Object> checkTransactionByHash(String hash) {
//        String url = apiUrl + "/v1/check_transaction_by_hash";
//        Map<String, Object> payload = Map.of("hash", hash);
//        return post(url, payload, true);
//    }
//
//    // 7. Check Transaction by Short Hash
//    public Map<String, Object> checkTransactionByShortHash(String hash, Double amount, String currency) {
//        String url = apiUrl + "/v1/check_transaction_by_short_hash";
//        Map<String, Object> payload = Map.of(
//                "hash", hash,
//                "amount", amount,
//                "currency", currency
//        );
//        return post(url, payload, true);
//    }
//
//    // 8. Check Bakong Account
//    public Map<String, Object> checkBakongAccount(String accountId) {
//        String url = apiUrl + "/v1/check_bakong_account";
//        Map<String, Object> payload = Map.of("accountId", accountId);
//        return post(url, payload, true);
//    }
//
//    // Helper method
//    private Map<String, Object> post(String url, Map<String, Object> payload, boolean withAuth) {
//        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, defaultHeaders(withAuth));
//        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
//        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
//            return response.getBody();
//        }
//        throw new RuntimeException("API call failed: " + url);
//    }
//}
