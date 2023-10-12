package com.example.wantedpreonboardingbackend.domain.recruitment.service;

import com.example.wantedpreonboardingbackend.domain.company.domain.Company;
import com.example.wantedpreonboardingbackend.domain.company.service.CompanyService;
import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentCreateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentUpdateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.repository.RecruitmentRepository;
import com.example.wantedpreonboardingbackend.global.exception.BusinessException;
import com.example.wantedpreonboardingbackend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitmentServiceImpl implements RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyService companyService;

    @Override
    @Transactional
    public void registerRecruitment(RecruitmentCreateReq dto) {
        Company company = companyService.findById(dto.getCompanyId());
        recruitmentRepository.save(RecruitmentCreateReq.toEntity(company, dto));
    }

    @Override
    @Transactional
    public void updateRecruitment(Long id, RecruitmentUpdateReq dto) {
        Recruitment recruitment = findById(id);
        recruitment.updateInfo(dto);
    }

    @Override
    @Transactional
    public void deleteRecruitment(Long id) {
        Recruitment recruitment = findById(id);
        recruitmentRepository.delete(recruitment);
    }

    public Recruitment findById(Long id) {
        return recruitmentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.RECRUITMENT_NOT_FOUND));
    }
}
