package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
    public List<Product> findAll();

    //특정 상점의 상품 리스트
    public List<Product> findByStore_storeId(int storeId);

    public Product findById(int productId);
}
