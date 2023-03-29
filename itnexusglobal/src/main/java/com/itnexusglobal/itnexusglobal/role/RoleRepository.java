package com.itnexusglobal.itnexusglobal.role;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByRoleName(String name);
}
