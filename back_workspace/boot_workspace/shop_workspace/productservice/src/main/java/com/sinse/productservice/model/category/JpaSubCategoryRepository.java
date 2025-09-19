package com.sinse.productservice.model.category;

import com.sinse.productservice.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaSubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    //외래키를 이용하여 목록 가져오기
    public List<SubCategory> findByTopCategory_topCategoryId(int topCategoryId);
}
