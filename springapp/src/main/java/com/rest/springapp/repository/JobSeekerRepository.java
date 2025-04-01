package com.rest.springapp.repository;

import com.rest.springapp.entities.JobSeeker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
    
    // Pagination support
    Page<JobSeeker> findAll(Pageable pageable);

    // JPQL to find job seekers by name (case-insensitive)
    @Query("SELECT j FROM JobSeeker j WHERE LOWER(j.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<JobSeeker> findByName(@Param("name") String name);

    // JPQL to find job seekers by email
    @Query("SELECT j FROM JobSeeker j WHERE j.email = :email")
    List<JobSeeker> findByEmail(@Param("email") String email);

    // JPQL to find job seekers by skill
    @Query("SELECT j FROM JobSeeker j JOIN j.skills s WHERE LOWER(s) LIKE LOWER(CONCAT('%', :skill, '%'))")
    List<JobSeeker> findBySkill(@Param("skill") String skill);
}
