package com.example.wantedpreonboardingbackend.domain.recruitment.service;

import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentCreateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentUpdateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.repository.RecruitmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RecruitmentServiceImplTest {
    @Autowired
    private RecruitmentServiceImpl recruitmentService;

    @Autowired
    private RecruitmentRepository recruitmentRepository;

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

    @DisplayName("채용공고는 회사 id 이외 모두 수정 가능하다")
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
}