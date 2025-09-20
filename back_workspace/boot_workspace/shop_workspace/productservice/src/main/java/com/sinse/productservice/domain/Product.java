package com.sinse.productservice.domain;

import com.sinse.productservice.controller.dto.SubCategoryDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;
    private String brand;
    private int price;
    private int discount;
    private String detail;

    //mybatis의 경우 association 대상임
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;

    //mybatis의 경우 collection으로 수집함
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductFile> productFileList;
}
