package com.sinse.productservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subcategory")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private int subCategoryId;

    @Column(name = "subname")
    private String subName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="topcategory_id")
    private TopCategory topCategory;
}
