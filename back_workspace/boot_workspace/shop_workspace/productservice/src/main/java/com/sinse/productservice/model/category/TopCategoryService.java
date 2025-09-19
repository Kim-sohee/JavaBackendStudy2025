package com.sinse.productservice.model.category;

import com.sinse.productservice.domain.TopCategory;

import java.util.List;

public interface TopCategoryService {
    public List<TopCategory> selectAll();
}
