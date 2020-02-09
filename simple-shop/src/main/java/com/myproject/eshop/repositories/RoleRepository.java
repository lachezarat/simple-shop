package com.myproject.eshop.repositories;

import com.myproject.eshop.data.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findRoleByAuthority(String authority);
}
