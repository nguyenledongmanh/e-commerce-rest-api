package com.ecommerceproject.ecommercerestapi.repository;

import com.ecommerceproject.ecommercerestapi.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository
        extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

}
