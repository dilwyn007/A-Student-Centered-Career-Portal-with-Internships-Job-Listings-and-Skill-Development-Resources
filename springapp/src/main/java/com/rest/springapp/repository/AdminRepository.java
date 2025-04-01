package com.rest.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rest.springapp.entities.Admin;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    // JPQL Query to Find Admin by Email
    @Query("SELECT a FROM Admin a WHERE a.email = ?1")
    Optional<Admin> findByEmail(String email);

    // JPQL Query for Pagination
    @Query("SELECT a FROM Admin a")
    Page<Admin> findAllAdmins(Pageable pageable);
}
