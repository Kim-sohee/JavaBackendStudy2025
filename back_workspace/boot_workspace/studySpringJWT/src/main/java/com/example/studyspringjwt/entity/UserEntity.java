package com.example.studyspringjwt.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String role;
}
