package com.sinse.productservice.model.category;

import com.sinse.productservice.domain.SubCategory;

import java.util.List;

public interface SubCategoryService {

    //특정 상위에 소속된 목록만 가져오기
    //매개변수 있을 경우
    public List<SubCategory> selectAll(int topCategoryId);

    // 모두 가져오기
    public List<SubCategory> selectAll();
}
