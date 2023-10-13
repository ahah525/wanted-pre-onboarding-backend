package com.example.wantedpreonboardingbackend.domain.recruitment.repository;

import com.example.wantedpreonboardingbackend.domain.company.domain.Company;
import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    List<Recruitment> findByCompanyAndIdNot(Company company, Long id);

    @Query(value = """
    SELECT r 
    FROM Recruitment r
    WHERE r.company.name LIKE %:search%
    OR r.position LIKE %:search%
    OR r.stack LIKE %:search%
    """)
    List<Recruitment> findByCompanyNameOrPositionOrStackContains(@Param("search") String search);
}
