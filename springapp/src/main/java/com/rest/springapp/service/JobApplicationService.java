package com.rest.springapp.service;

import com.rest.springapp.entities.JobApplication;
import com.rest.springapp.repository.JobApplicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public Page<JobApplication> getAllJobApplications(Pageable pageable) {
        return jobApplicationRepository.findAll(pageable);
    }

    public Optional<JobApplication> getJobApplicationById(Long id) {
        return jobApplicationRepository.findById(id);
    }

    public JobApplication createJobApplication(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }

    public Optional<JobApplication> updateJobApplication(Long id, JobApplication jobApplicationDetails) {
        return jobApplicationRepository.findById(id).map(jobApplication -> {
            jobApplication.setStatus(jobApplicationDetails.getStatus());
            return jobApplicationRepository.save(jobApplication);
        });
    }

    public boolean deleteJobApplication(Long id) {
        if (jobApplicationRepository.existsById(id)) {
            jobApplicationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
