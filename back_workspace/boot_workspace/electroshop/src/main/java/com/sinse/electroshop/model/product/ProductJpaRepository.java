package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
    public List<Product> findAll();
    public Product findById(int product_id);
}
