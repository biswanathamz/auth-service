package com.affnine.auth.Repository;

import com.affnine.auth.Model.UserMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMetaDataRepository extends JpaRepository<UserMetaData,Long> {
}
