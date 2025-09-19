package com.sinse.productservice.controller;

import com.sinse.productservice.controller.dto.ProductDTO;
import com.sinse.productservice.controller.dto.ProductFileDTO;
import com.sinse.productservice.controller.dto.SubCategoryDTO;
import com.sinse.productservice.domain.Product;
import com.sinse.productservice.domain.SubCategory;
import com.sinse.productservice.model.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Roy폴딩
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/productapp")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> products() {
        List<Product> productList = productService.selectAll();

        //Product으로 구성된 List가 아닌, ProductDTO로 구성된 List를 새로 만들자
        List<ProductDTO> productDTOList = productList.stream()
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setProductId(product.getProductId());
                    productDTO.setProductName(product.getProductName());
                    productDTO.setBrand(product.getBrand());
                    productDTO.setPrice(product.getPrice());
                    productDTO.setDiscount(product.getDiscount());
                    productDTO.setDetail(product.getDetail());

                    SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
                    subCategoryDTO.setSubCategoryId(product.getSubCategory().getSubCategoryId());
                    subCategoryDTO.setSubName(product.getSubCategory().getSubName());
                    productDTO.setSubCategoryDTO(subCategoryDTO);

                    List<ProductFileDTO> productFileDTOList = product.getProductFileList().stream()
                            .map(file -> {
                                ProductFileDTO productFileDTO = new ProductFileDTO();
                                productFileDTO.setProductFileId(file.getProductFileId());
                                productFileDTO.setFileName(file.getFileName());
                                productFileDTO.setOriginalName(file.getOriginalName());
                                productFileDTO.setContentType(file.getContentType());
                                productFileDTO.setFileSize(file.getFileSize());
                                return productFileDTO;
                            }).toList();

                    productDTO.setProductFileList(productFileDTOList);

                    return productDTO;
                }).toList();

        return ResponseEntity.ok(Map.of("data", productDTOList));
    }

    @PostMapping(value="/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> regist(@ModelAttribute ProductDTO productDTO, @RequestPart List<MultipartFile> files) {
        log.debug("넘겨받은 이미지수 "+files.size());
        log.debug("subcategory "+productDTO.getSubCategoryDTO().getSubName());
        log.debug("상품명 "+productDTO.getProductName());
        log.debug("브랜드 "+productDTO.getBrand());

        //서비스에게!
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
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

    //1건 가져오기
    @GetMapping("/products/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable("productId") int productId) {
        Product product = productService.select(productId);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setBrand(product.getBrand());
        productDTO.setPrice(product.getPrice());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setDetail(product.getDetail());

        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.setSubCategoryId(product.getSubCategory().getSubCategoryId());
        subCategoryDTO.setSubName(product.getSubCategory().getSubName());
        productDTO.setSubCategoryDTO(subCategoryDTO);

        List<ProductFileDTO> productFileDTOList = product.getProductFileList().stream()
                .map(file -> {
                    ProductFileDTO productFileDTO = new ProductFileDTO();
                    productFileDTO.setProductFileId(file.getProductFileId());
                    productFileDTO.setFileName(file.getFileName());
                    productFileDTO.setOriginalName(file.getOriginalName());
                    productFileDTO.setContentType(file.getContentType());
                    productFileDTO.setFileSize(file.getFileSize());
                    return productFileDTO;
                }).toList();

        productDTO.setProductFileList(productFileDTOList);

        return ResponseEntity.ok(Map.of("result", product));
    }
}















