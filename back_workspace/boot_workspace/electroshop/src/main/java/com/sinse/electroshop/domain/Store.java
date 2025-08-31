package com.sinse.electroshop.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="store")
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //MySQL의 auto_increment 매핑
    @Column(name="store_id")
    private int storeId;

    private String businessId;
    private String password;
    private String storeName;
}
