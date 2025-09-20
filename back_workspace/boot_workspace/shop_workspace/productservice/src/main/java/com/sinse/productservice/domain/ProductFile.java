package com.sinse.productservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_file")
public class ProductFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_file_id")
    private int productFileId;

    @Column(name = "filename")
    private String fileName;

    @Column(name="original_name")
    private String originalName;

    @Column(name="content_type")
    private String contentType;

    @Column(name="filepath")
    private String filePath;

    @Column(name = "filesize")
    private long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
