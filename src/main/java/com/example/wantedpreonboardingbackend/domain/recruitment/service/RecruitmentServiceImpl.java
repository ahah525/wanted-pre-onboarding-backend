package com.example.wantedpreonboardingbackend.domain.recruitment.service;

import com.example.wantedpreonboardingbackend.domain.company.domain.Company;
import com.example.wantedpreonboardingbackend.domain.company.service.CompanyService;
import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentCreateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentUpdateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.response.RecruitmentDetailResp;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.response.RecruitmentResp;
import com.example.wantedpreonboardingbackend.domain.recruitment.repository.RecruitmentRepository;
import com.example.wantedpreonboardingbackend.global.BaseEntity;
import com.example.wantedpreonboardingbackend.global.exception.BusinessException;
import com.example.wantedpreonboardingbackend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitmentServiceImpl implements RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyService companyService;

    @Override
    @Transactional
    public Long registerRecruitment(RecruitmentCreateReq dto) {
        Company company = companyService.findById(dto.getCompanyId());
        return recruitmentRepository.save(RecruitmentCreateReq.toEntity(company, dto)).getId();
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

    @Override
    public List<RecruitmentResp> getAllRecruitment(String search) {
        // 1. 앞뒤 공백 제거
        search = search.strip();
        // 2. ""이면 전체 목록 조회
        if (search.length() == 0) return findAll();
        // 3. 검색 조회
        return findBySearch(search);
    }

    // 회사명, 포지션, 사용기술에 검색어가 포함된 채용공고 목록 조회
    private List<RecruitmentResp> findBySearch(String search) {
        return recruitmentRepository.findByCompanyNameOrPositionOrStackContains(search).stream()
                .map(RecruitmentResp::of)
                .collect(Collectors.toList());
    }

    // 전체 채용공고 목록 조회
    private List<RecruitmentResp> findAll() {
        return recruitmentRepository.findAll().stream()
                .map(RecruitmentResp::of)
                .collect(Collectors.toList());
    }

    @Override
    public RecruitmentDetailResp getRecruitmentDetail(Long id) {
        Recruitment recruitment = findById(id);
        List<Long> ids = findIdsByCompanyAndIdNot(recruitment.getCompany(), id);
        return RecruitmentDetailResp.of(recruitment, ids);
    }

    @Override
    public Recruitment findById(Long id) {
        return recruitmentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.RECRUITMENT_NOT_FOUND));
    }

    // 회사의 채용공고 중 해당 채용공고를 제외한 id 목록 조회
    private List<Long> findIdsByCompanyAndIdNot(Company company, Long id) {
        List<Recruitment> recruitments = recruitmentRepository.findByCompanyAndIdNot(company, id);
        return recruitments.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
    }
}
