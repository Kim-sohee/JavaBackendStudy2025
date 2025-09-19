package com.sinse.productservice.model.category;

import com.sinse.productservice.domain.TopCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopCategoryServiceImpl implements TopCategoryService {

    private final JpaTopCategoryRepository jpaTopCategoryRepository;

    @Override
    public List<TopCategory> selectAll() {
        return jpaTopCategoryRepository.findAll();
    }
}
