package com.rest.springapp.service;

import com.rest.springapp.entities.Admin;
import com.rest.springapp.repository.AdminRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // Get all admins with pagination
    public Page<Admin> getAllAdmins(Pageable pageable) {
        return adminRepository.findAllAdmins(pageable);
    }

    // Get admin by ID
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    // Get admin by email (using JPQL)
    public Optional<Admin> getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    // Create new admin
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Update admin
    @Transactional
    public Optional<Admin> updateAdmin(Long id, Admin adminDetails) {
        return adminRepository.findById(id).map(admin -> {
            admin.setName(adminDetails.getName());
            admin.setEmail(adminDetails.getEmail());
            return adminRepository.save(admin);
        });
    }

    // Delete admin
    @Transactional
    public boolean deleteAdmin(Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
