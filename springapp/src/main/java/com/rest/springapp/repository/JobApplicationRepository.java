package com.rest.springapp.repository;

import com.rest.springapp.entities.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    Page<JobApplication> findAll(Pageable pageable); // Pagination support
}
