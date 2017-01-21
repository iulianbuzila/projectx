package com.projectx.cwm.repositories;

import com.projectx.cwm.domain.Log;
import com.projectx.cwm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sl0 on 1/17/17.
 */
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
