package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;

import java.util.List;

public interface ProductDAO {
    //모든 상품
    public List<Product> findAll();

    //하나의 상품
    public Product findById(int id);

    //상품정보 등록
    public Product save(Product product);

    //상품정보 수정
    public Product update(Product product);
    
    //상품 정보 삭제
    public void deleteById(int id);
}
