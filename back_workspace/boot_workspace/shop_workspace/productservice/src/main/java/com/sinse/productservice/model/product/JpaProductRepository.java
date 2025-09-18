package com.sinse.productservice.model.product;

import com.sinse.productservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<Product, Integer> {

}
