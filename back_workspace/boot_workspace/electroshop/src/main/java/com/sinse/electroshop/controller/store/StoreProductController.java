package com.sinse.electroshop.controller.store;

import com.sinse.electroshop.controller.dto.ProductDTO;
import com.sinse.electroshop.domain.Product;
import com.sinse.electroshop.domain.Store;
import com.sinse.electroshop.model.product.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    public ResponseEntity<String> regist(ProductDTO productDTO){
        log.debug("productDTO: "+productDTO);

        Product product = new Product();
        Store store = new Store();
        store.setStoreId(productDTO.getStore().getStoreId());
        product.setStore(store);
        product.setProductName(productDTO.getProduct_name());
        product.setPrice(productDTO.getPrice());
        product.setBrand(productDTO.getBrand());

        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("등록성공");
    }

    @GetMapping("/store/product/list")
    public String listForm(){
        return "store/product/list";
    }

    @GetMapping("/store/product/data")
    @ResponseBody
    public List<Product> getProductList(@RequestParam(name="storeId", required = false) Integer storeId, HttpSession session){
        if(storeId == null || storeId==0) {
            Store store = (Store) session.getAttribute("store");
            storeId = store.getStoreId();
        }
        return productService.getListByStoreId(storeId);
    }

    @GetMapping("/store/product/detail")
    public  String getDetail(int productId, Model model){
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        return "store/product/detail";
    }
}
