package com.example.wantedpreonboardingbackend.domain.recruitment.repository;

import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
}
