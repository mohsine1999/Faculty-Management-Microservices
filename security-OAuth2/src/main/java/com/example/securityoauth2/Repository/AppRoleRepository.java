package com.example.securityoauth2.Repository;

import com.example.securityoauth2.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
       AppRole findByRoleName(String name);
}
