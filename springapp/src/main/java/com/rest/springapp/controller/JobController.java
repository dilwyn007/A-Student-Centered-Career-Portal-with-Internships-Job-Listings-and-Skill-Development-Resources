package com.rest.springapp.controller;

import com.rest.springapp.entities.Job;
import com.rest.springapp.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Get paginated list of jobs
    @GetMapping
    public ResponseEntity<Page<Job>> getAllJobs(
            @RequestParam(defaultValue = "0") int page,  // ✅ Un-commented pagination parameters
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(jobService.getAllJobs(pageable));
    }

    // Get job by ID
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new job
    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = jobService.createJob(job);
        return ResponseEntity.status(201).body(createdJob);
    }

    // Update an existing job
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job jobDetails) {
        return jobService.updateJob(id, jobDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a job
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        if (jobService.deleteJob(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ New Endpoints using JPQL Queries

    // Find jobs by title
    @GetMapping("/search/title")
    public ResponseEntity<List<Job>> getJobsByTitle(@RequestParam String title) {
        List<Job> jobs = jobService.getJobsByTitle(title);
        return jobs.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(jobs);
    }

    // Find jobs by location
    @GetMapping("/search/location")
    public ResponseEntity<List<Job>> getJobsByLocation(@RequestParam String location) {
        List<Job> jobs = jobService.getJobsByLocation(location);
        return jobs.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(jobs);
    }

    // Find jobs within a salary range
    @GetMapping("/search/salary")
    public ResponseEntity<List<Job>> getJobsBySalaryRange(
            @RequestParam double minSalary,
            @RequestParam double maxSalary) {
        List<Job> jobs = jobService.getJobsBySalaryRange(minSalary, maxSalary);
        return jobs.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(jobs);
    }
}
