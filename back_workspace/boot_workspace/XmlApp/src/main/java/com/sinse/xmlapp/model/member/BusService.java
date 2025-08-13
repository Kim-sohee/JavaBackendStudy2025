package com.sinse.xmlapp.model.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Service
public class BusService {
    private BusHandler busHandler;
    private String serviceKey = "h4iQorPnhgjXhZMRZBUz4808vDIVrJjIDEmVPuci4wjjyVcU2EAVTeXk7%2FhZOVJ%2BhQGylBRKhvudL9wLeulVZQ%3D%3D";

    public BusService(BusHandler busHandler) {
        this.busHandler = busHandler;
    }

    public List<Bus> parse() throws Exception{
        String baseUrl = "http://apis.data.go.kr/6260000/BusanBIMS/busStopList";

        String url = baseUrl + "?"+
                "ServiceKey=" + serviceKey +
                "&pageNo="+ URLEncoder.encode("1", StandardCharsets.UTF_8)+
                "&numOfRows="+URLEncoder.encode("10", StandardCharsets.UTF_8)+
                "&bstopnm="+URLEncoder.encode("", StandardCharsets.UTF_8)+
                "&arsno="+URLEncoder.encode("", StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String xmlContent = response.body();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        InputSource inputSource = new InputSource(new StringReader(xmlContent));
        saxParser.parse(inputSource, busHandler);

        return busHandler.getBusList();
    }
}
