package com.affnine.auth.Repository;

import com.affnine.auth.Model.UserRoleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleServiceRepository extends JpaRepository<UserRoleService, Long> {
    Optional<UserRoleService> findByUserIdAndServiceId(Long userId, Long serviceId);
}
