package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaProductDAO implements ProductDAO {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll();
    }

    @Override
    public Product findById(int product_id) {
        return productJpaRepository.findById(product_id);
    }

    @Override
    public Product save(Product product) {
        return productJpaRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productJpaRepository.save(product);
    }

    @Override
    public void deleteById(int id) {
        productJpaRepository.deleteById(id);
    }
}
