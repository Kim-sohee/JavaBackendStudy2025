package com.sinse.jwtlogin.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int member_id;

    private String id;
    private String name;
    private String password;
}
