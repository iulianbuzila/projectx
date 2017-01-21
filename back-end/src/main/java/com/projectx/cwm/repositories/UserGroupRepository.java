package com.projectx.cwm.repositories;

import com.projectx.cwm.domain.Group;
import com.projectx.cwm.domain.User;
import com.projectx.cwm.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sl0 on 1/18/17.
 */
@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    UserGroup findByUserAndGroup(User user, Group group);
}
