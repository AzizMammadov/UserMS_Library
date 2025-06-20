package com.users.library.repository;

import com.users.library.entity.RoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface RoleGroupRepository extends JpaRepository<RoleGroup, Long> {

   Optional<RoleGroup> findByName(String name);
    boolean existsByName(String name);
}
