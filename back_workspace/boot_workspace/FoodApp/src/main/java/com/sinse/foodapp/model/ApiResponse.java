package com.sinse.foodapp.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

//Open API로 부터 받은 응답 정보를 담기 위한 객체
@Data
public class ApiResponse {
    private List<Body> body;
    private Header header;

    //Body 정의(내부 클래스로 정의한다. -> 왜? 분산시키면 유지관리 하기가 복잡할 수 있음)
   @Data
    public static class Body{
        private String SIGUN;   //관할 행정구역
        @JsonProperty("SIGUN")
        private String sigun;

        private double LO;  //경도
        @JsonProperty("LO")
        private double lo;

        private String MNMNU;   //메인 메뉴
        @JsonProperty("MNMNU")
        private String mnmnu;

        private String SE;      //업소 구분
        @JsonProperty("SE")
        private String se;

        private String CMPNM;   //상호명
        @JsonProperty("CMPNM")
        private String cmpnm;

        private String MENU;    //메뉴
        @JsonProperty("MENU")
        private String menu;

        private String TELNO;   //전화번호
        @JsonProperty("TELNO")
        private String telno;

        //private String _URL;    //소개 홈페이지
        @JsonProperty("_URL")
        @JsonAlias({"url" , "URL"})
        private String url;

        private String ADRES;   //주소
        @JsonProperty("ADRES")
        private String adres;

        private double LA;      //위도
        @JsonProperty("LA")
        private String la;

        private String TIME;    //운영시간
        @JsonProperty("TIME")
        private String time;

        private String DC;      //설명
        @JsonProperty("DC")
        private String dc;
    }

    //Header 정의
    @Data
    public static class Header{
        private int perPage;    //한 페이지 결과 수
        private int resultCode; //결과 코드
        private int totalRows;  //데이터 총 개수
        private int currentPage;    //페이지 번호
        private String resultMsg;       //결과 메시지
    }
}
