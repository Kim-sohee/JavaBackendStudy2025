package com.sinse.studyapp0812.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponseBusan {
    private GetFoodKr getFoodKr;


    @Data
    public static class GetFoodKr{
        private Header header;
        private List<Item> item;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }

    @Data
    public static class Header {
        private int code;
        private String message;
    }

    @Data
    public static class Item {
        @JsonProperty("UC_SEQ")
        private String ucSeq;

        @JsonProperty("MAIN_TITLE")
        private String mainTitle;

        @JsonProperty("GUGUN_NM")
        private String gugunNm;

        @JsonProperty("LAT")
        private double lat;

        @JsonProperty("LNG")
        private double lng;

        @JsonProperty("PLACE")
        private String place;

        @JsonProperty("TITLE")
        private String title;

        @JsonProperty("SUBTITLE")
        private String subtitle;

        @JsonProperty("ADDR1")
        private String addr1;

        @JsonProperty("ADDR2")
        private String addr2;

        @JsonProperty("CNTCT_TEL")
        private String cntctTel;

        @JsonProperty("HOMEPAGE_URL")
        private String homepageUrl;

        @JsonProperty("USAGE_DAY_WEEK_AND_TIME")
        private String usageDayWeekAndTime;

        @JsonProperty("RPRSNTV_MENU")
        private String rprsntvMenu;

        @JsonProperty("MAIN_IMG_NORMAL")
        private String mainImgNormal;

        @JsonProperty("MAIN_IMG_THUMB")
        private String mainImgThumb;

        @JsonProperty("ITEMCNTNTS")
        private String itemcntnts;
    }
}
