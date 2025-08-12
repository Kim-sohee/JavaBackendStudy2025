package com.sinse.studyapp0812.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.studyapp0812.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class StudyController {
    private String encoding_serviceKey = "h4iQorPnhgjXhZMRZBUz4808vDIVrJjIDEmVPuci4wjjyVcU2EAVTeXk7%2FhZOVJ%2BhQGylBRKhvudL9wLeulVZQ%3D%3D";

    @GetMapping("/stores")
    public ApiResponse stores(String store_name) throws IOException, InterruptedException {
        String baseUrl = "https://apis.data.go.kr/6430000/tradiMarketStatService/getTradiMarketStat";

        String url = baseUrl + "?" +
                        "serviceKey="+encoding_serviceKey +
                        "&currentPage="+URLEncoder.encode("1", StandardCharsets.UTF_8)+
                        "&perPage="+URLEncoder.encode("10", StandardCharsets.UTF_8)+
                        "&SIGUN="+URLEncoder.encode("청주", StandardCharsets.UTF_8)+
                        "&MRKT_ND_SOPSTR="+URLEncoder.encode(store_name, StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        ApiResponse apiResponse = mapper.readValue(response.body(), ApiResponse.class);

        return apiResponse;
    }
}