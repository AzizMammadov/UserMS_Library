package com.users.library.repository;

import com.users.library.entity.RoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleGroupRepository extends JpaRepository<RoleGroup, Long> {

    boolean existsByName(String name);
}
