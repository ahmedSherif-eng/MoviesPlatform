package com.fawry.moviesplatform.repository;

import com.fawry.moviesplatform.security.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
}
