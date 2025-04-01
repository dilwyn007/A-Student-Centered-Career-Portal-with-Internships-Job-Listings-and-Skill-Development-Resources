package com.rest.springapp.controller;

import com.rest.springapp.entities.JobApplication;
import com.rest.springapp.service.JobApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    // Get paginated list of job applications
    @GetMapping
    public ResponseEntity<Page<JobApplication>> getAllJobApplications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(jobApplicationService.getAllJobApplications(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getJobApplicationById(@PathVariable Long id) {
        return jobApplicationService.getJobApplicationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<JobApplication> createJobApplication(@RequestBody JobApplication jobApplication) {
        JobApplication createdJobApplication = jobApplicationService.createJobApplication(jobApplication);
        return ResponseEntity.status(201).body(createdJobApplication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateJobApplication(@PathVariable Long id, @RequestBody JobApplication jobApplicationDetails) {
        return jobApplicationService.updateJobApplication(id, jobApplicationDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Long id) {
        if (jobApplicationService.deleteJobApplication(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
