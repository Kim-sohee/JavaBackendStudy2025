package com.sinse.productservice.model.product;

import com.sinse.productservice.domain.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public void save(Product product, List<MultipartFile> files);
}
