package com.sinse.xmlapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
public class ARestBusController {
    @GetMapping("/buses")
    public String getList() throws Exception {
        String serviceKey = "h4iQorPnhgjXhZMRZBUz4808vDIVrJjIDEmVPuci4wjjyVcU2EAVTeXk7%2FhZOVJ%2BhQGylBRKhvudL9wLeulVZQ%3D%3D";
        StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/6260000/BusanBIMS/busStopList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("bstopnm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*정류소 명*/
        urlBuilder.append("&" + URLEncoder.encode("arsno","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*정류소 번호*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        return sb.toString();
    }
}
