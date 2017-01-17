package com.projectx.cwm.repositories;

import com.projectx.cwm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by sl0 on 11/2/16.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "select role from cwm.role inner join cwm.user_group on cwm.role.id = cwm.user_group.role_id i" +
            "nner join cwm.user on cwm.user.id = cwm.user_group.user_id where cwm.user.username = :username",
            nativeQuery = true)
    Set<String> findRolesByUser(@Param("username")String username);

    User findByUsernameAndPassword(String username, String password);

    User findByEmail(String email);
}
