package com.users.library.repository;

import com.users.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Soft-delete olunmayan bütün istifadəçilər (Hibernate @Where ilə filtr edilir)
    @Override
    List<User> findAll();

    // Soft-deleted istifadəçiləri gətir (native query ilə, çünki @Where bunu gizlədir)
    @Query(value = "SELECT * FROM users WHERE deleted = true", nativeQuery = true)
    List<User> findAllDeleted();

    // Bütün istifadəçilər (soft-deleted olanlar da daxil), Hibernate filterini atlayır
    @Query("SELECT u FROM User u WHERE 1=1")
    List<User> findAllIncludingDeleted();

    // Soft-delete olmuş istifadəçini geri qaytar (deleted = false)
    @Modifying
    @Query("UPDATE User u SET u.deleted = false WHERE u.id = :id")
    void restoreById(@Param("id") Long id);

    // Soft-deleted istifadəçiləri həqiqi olaraq sil (DB-dən fiziki sil)
    @Modifying
    @Query(value = "DELETE FROM users WHERE deleted = true", nativeQuery = true)
    void hardDeleteAllSoftDeleted();

    // İstifadəçini username ilə tap
    Optional<User> findByUsername(String username);

    // İstifadəçini email ilə tap
    Optional<User> findByEmail(String email);

    // Username var olub olmadığını yoxla
    boolean existsByUsername(String username);

    // Email var olub olmadığını yoxla
    boolean existsByEmail(String email);
}
