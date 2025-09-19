package com.sinse.productservice.controller;

import com.sinse.productservice.controller.dto.SubCategoryDTO;
import com.sinse.productservice.domain.SubCategory;
import com.sinse.productservice.model.category.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productapp")
@RequiredArgsConstructor
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    //특정 상위카테고리에 소속된 모든 하위 목록 가져오기
    @GetMapping("/subcategories")
    public ResponseEntity<?> getListByTopCategoryId(int topCategoryId) {
        List<SubCategory> subCategoryList = subCategoryService.selectAll(topCategoryId);

        List<SubCategoryDTO> subCategoryDTOList = subCategoryList.stream()
                .map(subCategory -> {
                    SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
                    subCategoryDTO.setSubCategoryId(subCategory.getSubCategoryId());
                    subCategoryDTO.setSubName(subCategory.getSubName());

                    return subCategoryDTO;
                }).toList();

        return ResponseEntity.ok(Map.of("result", subCategoryDTOList));
    }
}
