package com.sinse.memberservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private int providerId;

    @Column(name = "provider_name")
    private String providerName;
}
