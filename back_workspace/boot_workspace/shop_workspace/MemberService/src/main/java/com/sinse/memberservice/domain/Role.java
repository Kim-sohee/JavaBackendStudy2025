package com.sinse.memberservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name="role_name")
    private String roleName;
}
