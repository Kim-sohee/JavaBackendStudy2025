package com.sinse.productservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "topcategory")
public class TopCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topcategory_id")
    private int topCategoryId;

    @Column(name = "topname")
    private String topName;
}
