package com.affnine.auth.Repository;

import com.affnine.auth.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    boolean existsByName(String name);
}
