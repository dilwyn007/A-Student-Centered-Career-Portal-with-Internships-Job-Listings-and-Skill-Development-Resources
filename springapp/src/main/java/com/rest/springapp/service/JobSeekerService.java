package com.rest.springapp.service;

import com.rest.springapp.entities.JobSeeker;
import com.rest.springapp.repository.JobSeekerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerService {

    private final JobSeekerRepository jobSeekerRepository;

    public JobSeekerService(JobSeekerRepository jobSeekerRepository) {
        this.jobSeekerRepository = jobSeekerRepository;
    }

    public Page<JobSeeker> getAllJobSeekers(Pageable pageable) {
        return jobSeekerRepository.findAll(pageable);
    }

    public Optional<JobSeeker> getJobSeekerById(Long id) {
        return jobSeekerRepository.findById(id);
    }

    public JobSeeker createJobSeeker(JobSeeker jobSeeker) {
        return jobSeekerRepository.save(jobSeeker);
    }

    public Optional<JobSeeker> updateJobSeeker(Long id, JobSeeker jobSeekerDetails) {
        return jobSeekerRepository.findById(id).map(jobSeeker -> {
            jobSeeker.setName(jobSeekerDetails.getName());
            jobSeeker.setEmail(jobSeekerDetails.getEmail());
            jobSeeker.setPhoneNumber(jobSeekerDetails.getPhoneNumber());
            jobSeeker.setSkills(jobSeekerDetails.getSkills());
            return jobSeekerRepository.save(jobSeeker);
        });
    }

    public boolean deleteJobSeeker(Long id) {
        if (jobSeekerRepository.existsById(id)) {
            jobSeekerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get job seekers by name
    public List<JobSeeker> getJobSeekersByName(String name) {
        return jobSeekerRepository.findByName(name);
    }

    // Get job seekers by email
    public List<JobSeeker> getJobSeekersByEmail(String email) {
        return jobSeekerRepository.findByEmail(email);
    }

    // Get job seekers by skill
    public List<JobSeeker> getJobSeekersBySkill(String skill) {
        return jobSeekerRepository.findBySkill(skill);
    }
}
