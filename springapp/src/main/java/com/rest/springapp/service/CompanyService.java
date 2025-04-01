package com.rest.springapp.service;

import com.rest.springapp.entities.Company;
import com.rest.springapp.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Page<Company> getAllCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    public Optional<Company> updateCompany(Long id, Company companyDetails) {
        return companyRepository.findById(id).map(company -> {
            company.setName(companyDetails.getName());
            company.setLocation(companyDetails.getLocation());
            company.setIndustry(companyDetails.getIndustry());
            company.setDescription(companyDetails.getDescription());
            return companyRepository.save(company);
        });
    }

    @Transactional
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
