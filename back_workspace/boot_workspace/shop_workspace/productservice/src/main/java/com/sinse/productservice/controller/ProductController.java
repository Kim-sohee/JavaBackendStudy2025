package com.sinse.productservice.controller;

import com.sinse.productservice.controller.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

//Roy폴딩
@Slf4j
@RestController
public class ProductController {

    @GetMapping("/products")
    public ResponseEntity<?> products() {
        return ResponseEntity.ok(Map.of("data", List.of("노트북", "스마트폰", "태블릿")));
    }

    @PostMapping(value="/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> regist(@ModelAttribute ProductDTO productDTO, @RequestPart List<MultipartFile> files) {
        log.debug("넘겨받은 이미지수 "+files.size());
        log.debug("subcategory "+productDTO.getSubCategoryDTO().getSubName());
        log.debug("상품명 "+productDTO.getProductName());
        log.debug("브랜드 "+productDTO.getBrand());

        return ResponseEntity.ok(Map.of("result", "업로드 성공"));
    }
}















