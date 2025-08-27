package com.sinse.electroshop.controller.store;

import com.sinse.electroshop.domain.Product;
import com.sinse.electroshop.model.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class StoreProductController {

    private final ProductService productService;

    @GetMapping("/store/product/registform")
    public String registerForm(){
        return "store/product/regist";
    }

    @PostMapping("/store/product/regist")
    @ResponseBody
    public ResponseEntity<String> regist(Product product){
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("등록성공");
    }
}
