package com.example.studyspringjwt.model.user;

import com.example.studyspringjwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    //유저가 존재하는지 확인
    public Boolean existsByUsername(String username);
}