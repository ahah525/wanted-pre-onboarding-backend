package com.example.wantedpreonboardingbackend.domain.recruitment.service;

import com.example.wantedpreonboardingbackend.domain.company.domain.Company;
import com.example.wantedpreonboardingbackend.domain.company.repository.CompanyRepository;
import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentCreateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentUpdateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.response.RecruitmentResp;
import com.example.wantedpreonboardingbackend.domain.recruitment.repository.RecruitmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RecruitmentServiceImplTest {
    @Autowired
    private RecruitmentServiceImpl recruitmentService;

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void init() {
        Company company1 = Company.builder()
                .name("원티드랩")
                .nation("한국")
                .region("서울")
                .build();
        Company company2 = Company.builder()
                .name("네이버")
                .nation("한국")
                .region("판교")
                .build();
        companyRepository.save(company1);
        companyRepository.save(company2);
    }

    @DisplayName("채용공고 등록")
    @Test
    void registerRecruitmentTest() {
        // given
        RecruitmentCreateReq dto = RecruitmentCreateReq.builder()
                .companyId(1L)
                .position("백엔드 주니어 개발자")
                .compensation(1000000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .stack("Python")
                .build();
        // when
        Long recruitmentId = recruitmentService.registerRecruitment(dto);
        // then
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElse(null);
        assertThat(recruitment.getCompany().getId()).isEqualTo(1l);
        assertThat(recruitment.getPosition()).isEqualTo("백엔드 주니어 개발자");
        assertThat(recruitment.getCompensation()).isEqualTo(1000000);
        assertThat(recruitment.getContent()).isEqualTo("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");
        assertThat(recruitment.getStack()).isEqualTo("Python");
    }

    @DisplayName("채용공고 수정 - 채용공고는 회사 id 이외 모두 수정 가능하다")
    @Test
    void updateRecruitmentTest() {
        // given
        RecruitmentCreateReq dto = RecruitmentCreateReq.builder()
                .companyId(1L)
                .position("백엔드 주니어 개발자")
                .compensation(1000000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .stack("Python")
                .build();
        Long recruitmentId = recruitmentService.registerRecruitment(dto);
        RecruitmentUpdateReq updateDto = RecruitmentUpdateReq.builder()
                .position("백엔드 경력 개발자")
                .compensation(1500000)
                .content("원티드랩에서 백엔드 경력 개발자를 채용합니다. 자격요건은..")
                .stack("Java")
                .build();
        // when
        recruitmentService.updateRecruitment(recruitmentId, updateDto);
        // then
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElse(null);
        assertThat(recruitment.getCompany().getId()).isEqualTo(1l);
        assertThat(recruitment.getPosition()).isEqualTo("백엔드 경력 개발자");
        assertThat(recruitment.getCompensation()).isEqualTo(1500000);
        assertThat(recruitment.getContent()).isEqualTo("원티드랩에서 백엔드 경력 개발자를 채용합니다. 자격요건은..");
        assertThat(recruitment.getStack()).isEqualTo("Java");
    }

    @DisplayName("채용공고 삭제")
    @Test
    void deleteRecruitmentTest() {
        // given
        RecruitmentCreateReq dto = RecruitmentCreateReq.builder()
                .companyId(1L)
                .position("백엔드 주니어 개발자")
                .compensation(1000000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .stack("Python")
                .build();
        Long recruitmentId = recruitmentService.registerRecruitment(dto);
        // when
        recruitmentService.deleteRecruitment(recruitmentId);
        // then
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElse(null);
        assertThat(recruitment).isNull();
    }

    @DisplayName("채용공고 목록 조회 - 검색어가 비었거나 공백이면 전체 목록 조회한다.")
    @Test
    void getAllRecruitmentTest1() {
        // given
        RecruitmentCreateReq dto1 = RecruitmentCreateReq.builder()
                .companyId(1L)
                .position("백엔드 주니어 개발자")
                .compensation(1000000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .stack("Python")
                .build();
        Long id1 = recruitmentService.registerRecruitment(dto1);
        RecruitmentCreateReq dto2 = RecruitmentCreateReq.builder()
                .companyId(2L)
                .position("백엔드 경력 개발자")
                .compensation(1500000)
                .content("네이버에서 백엔드 경력 개발자를 채용합니다. 자격요건은..")
                .stack("Java")
                .build();
        Long id2 = recruitmentService.registerRecruitment(dto2);
        // when
        List<RecruitmentResp> recruitments1 = recruitmentService.getAllRecruitment("");
        List<RecruitmentResp> recruitments2 = recruitmentService.getAllRecruitment(" ");
        // then
        assertThat(recruitments1.size()).isEqualTo(2);
        assertThat(recruitments2.size()).isEqualTo(2);
    }
}