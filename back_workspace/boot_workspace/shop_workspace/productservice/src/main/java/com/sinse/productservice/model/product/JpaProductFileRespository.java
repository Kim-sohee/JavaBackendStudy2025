package com.sinse.productservice.model.product;

import com.sinse.productservice.domain.ProductFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductFileRespository extends JpaRepository<ProductFile, Integer> {
}
