package com.example.wantedpreonboardingbackend.domain.company.repository;

import com.example.wantedpreonboardingbackend.domain.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
