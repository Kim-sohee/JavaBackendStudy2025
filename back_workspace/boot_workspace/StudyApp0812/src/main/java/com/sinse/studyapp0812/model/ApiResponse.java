package com.sinse.studyapp0812.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {
    private List<Body> body;
    private Header header;

    @Data
    public static class Body{
        private String SIGUN;
        @JsonProperty("SIGUN")
        private String sigun;

        private String MRKT_ND_SOPSTR;
        @JsonProperty("MRKT_ND_SOPSTR")
        private String mrkt_nd_sopstr;

        private String MRKT_ADRES;
        @JsonProperty("MRKT_ADRES")
        private String mrkt_adres;

        private int ESTBL_YEAR;
        @JsonProperty("ESTBL_YEAR")
        private int estbl_year;

        private String ESTBL_CYCLE;
        @JsonProperty("ESTBL_CYCLE")
        private String estbl_cycle;

        private String MRKT_DE;
        @JsonProperty("MRKT_DE")
        private String mrkt_de;

        private String RM;
        @JsonProperty("RM")
        private String rm;
    }

    @Data
    public static class Header{
        private int perPage;
        private int resultCode;
        private int totalRows;
        private int currentPage;
        private String resultMsg;
    }
}
