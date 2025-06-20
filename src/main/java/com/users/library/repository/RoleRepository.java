package com.users.library.repository;

import com.users.library.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByName(String name);

   List<Role> findByName(String name);

    List<Role> findAllByNameContainingIgnoreCase(String keyword);
// Adında "admin" keçən rolları tapır (case-insensitive)

    List<Role> findAllByIdIn(Set<Long> ids);
// Verilən id-lərin siyahısına uyğun rolları qaytarır
long countByName(String name);
// Müəyyən adla neçə roldan olduğunu qaytarır

}

