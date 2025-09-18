package com.sinse.productservice.controller;

import com.sinse.productservice.controller.dto.ProductDTO;
import com.sinse.productservice.domain.Product;
import com.sinse.productservice.domain.SubCategory;
import com.sinse.productservice.model.product.ProductService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

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

        //서비스에게!
        Product product = new Product();
        product.setProductId(productDTO.getProuctId());
        product.setProductName(productDTO.getProductName());
        product.setBrand(productDTO.getBrand());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setDetail(productDTO.getDetail());

        SubCategory subCategory = new SubCategory();
        subCategory.setSubName(productDTO.getSubCategoryDTO().getSubName());
        subCategory.setSubCategoryId(productDTO.getSubCategoryDTO().getSubCategoryId());
        product.setSubCategory(subCategory);

        productService.save(product, files);

        return ResponseEntity.ok(Map.of("result", "업로드 성공"));
    }
}















