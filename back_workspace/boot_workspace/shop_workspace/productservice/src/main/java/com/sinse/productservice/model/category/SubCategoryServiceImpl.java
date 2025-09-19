package com.sinse.productservice.model.category;

import com.sinse.productservice.domain.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final JpaSubCategoryRepository jpaSubCategoryRepository;

    @Override
    public List<SubCategory> selectAll(int topCategoryId) {
        return jpaSubCategoryRepository.findByTopCategory_topCategoryId(topCategoryId);
    }

    @Override
    public List<SubCategory> selectAll() {
        return List.of();
    }
}
