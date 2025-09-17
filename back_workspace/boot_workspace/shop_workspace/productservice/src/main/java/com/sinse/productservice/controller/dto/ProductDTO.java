package com.sinse.productservice.controller.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductDTO {
    private int prouctId;
    private String productName;
    private String brand;
    private int price;
    private int discount;
    private String detail;

    private SubCategoryDTO subCategoryDTO;
    private List<MultipartFile> files;
}
