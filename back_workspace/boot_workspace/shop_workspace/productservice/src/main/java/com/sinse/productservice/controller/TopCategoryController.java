package com.sinse.productservice.controller;

import com.sinse.productservice.domain.TopCategory;
import com.sinse.productservice.model.category.TopCategoryService;
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
public class TopCategoryController {
    private final TopCategoryService topCategoryService;

    @GetMapping("/topcategories")
    public ResponseEntity<?> topCategories() {

        List<TopCategory> topCategoryList = topCategoryService.selectAll();

        //join이 없어서 dto를 안만들고 바로 엔티티로 보내도 됨
        return ResponseEntity.ok(Map.of("result", topCategoryList));

    }
}
