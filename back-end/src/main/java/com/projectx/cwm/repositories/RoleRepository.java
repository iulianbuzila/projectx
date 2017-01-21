package com.projectx.cwm.repositories;

import com.projectx.cwm.domain.Role;
import com.projectx.cwm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sl0 on 1/21/17.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByRole(String role);
}
