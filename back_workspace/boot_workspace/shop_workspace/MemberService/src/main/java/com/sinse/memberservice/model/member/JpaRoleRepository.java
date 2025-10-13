package com.sinse.memberservice.model.member;

import com.sinse.memberservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<Role, String> {
    public Role findByRoleName(String roleName);
}
