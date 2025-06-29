package com.project.secure.notes.repositories;

import com.project.secure.notes.entities.Role;
import com.project.secure.notes.enums.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}