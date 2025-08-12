package com.sinse.studyapp0812.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.studyapp0812.model.ApiResponse;
import com.sinse.studyapp0812.model.ApiResponseBusan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class Study2Controller {
    private String serviceKey = "h4iQorPnhgjXhZMRZBUz4808vDIVrJjIDEmVPuci4wjjyVcU2EAVTeXk7%2FhZOVJ%2BhQGylBRKhvudL9wLeulVZQ%3D%3D";

    @GetMapping("/busan")
    public ApiResponseBusan list(String store_name) throws IOException, InterruptedException {
        String baseUrl = "https://apis.data.go.kr/6260000/FoodService/getFoodKr";

        String url = baseUrl + "?" +
                "serviceKey="+serviceKey +
                "&pageNo="+ URLEncoder.encode("1", StandardCharsets.UTF_8)+
                "&numOfRows="+URLEncoder.encode("20", StandardCharsets.UTF_8)+
                "&resultType="+URLEncoder.encode("json", StandardCharsets.UTF_8);
//                "&UC_SEQ="+URLEncoder.encode(store_name, StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        ApiResponseBusan apiResponseBusan = mapper.readValue(response.body(), ApiResponseBusan.class);

        return apiResponseBusan;
    }
}
