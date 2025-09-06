package com.example.studyspringjwt.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tokens")
public class RefreshEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String refresh;
    private String expiration;
}
