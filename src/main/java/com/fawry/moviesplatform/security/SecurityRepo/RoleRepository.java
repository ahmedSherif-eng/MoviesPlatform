package com.fawry.moviesplatform.security.SecurityRepo;

import com.fawry.moviesplatform.security.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String name);
}
