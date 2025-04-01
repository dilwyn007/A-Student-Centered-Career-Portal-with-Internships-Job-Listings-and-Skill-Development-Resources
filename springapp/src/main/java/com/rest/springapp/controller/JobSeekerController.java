package com.rest.springapp.controller;

import com.rest.springapp.entities.JobSeeker;
import com.rest.springapp.service.JobSeekerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekerController {

    private final JobSeekerService jobSeekerService;

    public JobSeekerController(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }    
 
    // Get paginated list of job seekers
    @GetMapping
    public ResponseEntity<Page<JobSeeker>> getAllJobSeekers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(jobSeekerService.getAllJobSeekers(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSeeker> getJobSeekerById(@PathVariable Long id) {
        return jobSeekerService.getJobSeekerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<JobSeeker> createJobSeeker(@RequestBody JobSeeker jobSeeker) {
        JobSeeker createdJobSeeker = jobSeekerService.createJobSeeker(jobSeeker);
        return ResponseEntity.status(201).body(createdJobSeeker);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSeeker> updateJobSeeker(@PathVariable Long id, @RequestBody JobSeeker jobSeekerDetails) {
        return jobSeekerService.updateJobSeeker(id, jobSeekerDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobSeeker(@PathVariable Long id) {
        if (jobSeekerService.deleteJobSeeker(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Get job seekers by name
    @GetMapping("/search/name")
    public ResponseEntity<List<JobSeeker>> getJobSeekersByName(@RequestParam String name) {
        return ResponseEntity.ok(jobSeekerService.getJobSeekersByName(name));
    }

    // Get job seekers by email
    @GetMapping("/search/email")
    public ResponseEntity<List<JobSeeker>> getJobSeekersByEmail(@RequestParam String email) {
        return ResponseEntity.ok(jobSeekerService.getJobSeekersByEmail(email));
    }

    // Get job seekers by skill
    @GetMapping("/search/skill")
    public ResponseEntity<List<JobSeeker>> getJobSeekersBySkill(@RequestParam String skill) {
        return ResponseEntity.ok(jobSeekerService.getJobSeekersBySkill(skill));
    }
}
