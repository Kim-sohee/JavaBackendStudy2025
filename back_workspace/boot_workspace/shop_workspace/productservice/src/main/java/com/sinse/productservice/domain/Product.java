package com.sinse.productservice.domain;

import com.sinse.productservice.controller.dto.SubCategoryDTO;
import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;
}
