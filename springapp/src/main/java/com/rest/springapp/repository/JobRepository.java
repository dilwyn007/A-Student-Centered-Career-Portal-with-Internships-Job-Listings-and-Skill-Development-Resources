package com.rest.springapp.repository;

import com.rest.springapp.entities.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    
    // Pagination support
    Page<Job> findAll(Pageable pageable);

    // Find jobs by title (case-insensitive)
    @Query("SELECT j FROM Job j WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Job> findByTitleContaining(@Param("title") String title);

    // Find jobs by location
    @Query("SELECT j FROM Job j WHERE j.location = :location")
    List<Job> findByLocation(@Param("location") String location);

    // Find jobs within a salary range
    @Query("SELECT j FROM Job j WHERE j.salary BETWEEN :minSalary AND :maxSalary")
    List<Job> findBySalaryRange(@Param("minSalary") double minSalary, @Param("maxSalary") double maxSalary);
}
