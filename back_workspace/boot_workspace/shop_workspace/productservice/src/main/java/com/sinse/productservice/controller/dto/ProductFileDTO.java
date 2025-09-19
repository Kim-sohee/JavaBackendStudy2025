package com.sinse.productservice.controller.dto;

import lombok.Data;

//JPA에 의한 Entity가 아니므로, proxy 객체가 붙어있지 않기 때문에,
//순수한 객체이므로 json으로 변환될 때 문제가 없다.
@Data
public class ProductFileDTO {
    private int productFileId;
    private String fileName;
    private String originalName;
    private String contentType;
    private long fileSize;
}
