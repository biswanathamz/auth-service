package com.affnine.auth.Repository;

import com.affnine.auth.Model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services, Long> {
}
