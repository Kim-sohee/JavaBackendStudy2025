package com.sinse.orderservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    private final RestClient restClient;

    public OrderController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("/orders")
    public ResponseEntity<?> orders() {
        Map<String, Object> response = restClient.get()
                .uri("http://localhost:7777/products")
                .retrieve()
                .body(Map.class);
        List<String> products = (List<String>)response.get("data");

        return ResponseEntity.ok(Map.of("result","상품정보 댕겨온 주문 목록", "products", products));
    }
}
