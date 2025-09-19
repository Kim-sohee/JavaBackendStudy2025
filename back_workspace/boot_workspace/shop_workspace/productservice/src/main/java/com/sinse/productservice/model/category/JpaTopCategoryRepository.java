package com.sinse.productservice.model.category;

import com.sinse.productservice.domain.TopCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTopCategoryRepository extends JpaRepository<TopCategory, Integer> {

}
