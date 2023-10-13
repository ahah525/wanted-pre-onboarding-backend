package com.example.wantedpreonboardingbackend.domain.apply.service;

import com.example.wantedpreonboardingbackend.domain.apply.domain.Apply;
import com.example.wantedpreonboardingbackend.domain.apply.dto.request.ApplyCreateReq;
import com.example.wantedpreonboardingbackend.domain.apply.repository.ApplyRepository;
import com.example.wantedpreonboardingbackend.domain.company.domain.Company;
import com.example.wantedpreonboardingbackend.domain.company.repository.CompanyRepository;
import com.example.wantedpreonboardingbackend.domain.member.domain.Member;
import com.example.wantedpreonboardingbackend.domain.member.repository.MemberRepository;
import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.domain.recruitment.repository.RecruitmentRepository;
import com.example.wantedpreonboardingbackend.global.exception.BusinessException;
import com.example.wantedpreonboardingbackend.global.exception.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ApplyServiceImplTest {
    @Autowired
    private ApplyServiceImpl applyService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @DisplayName("채용공고 지원")
    @Test
    void applyTest() {
        // given
        Member member = new Member("승연", "email@naver.com");
        Long memberId = memberRepository.save(member).getId();
        Company company = Company.builder()
                .name("원티드랩")
                .nation("한국")
                .region("서울")
                .build();
        companyRepository.save(company);
        Recruitment recruitment = Recruitment.builder()
                .position("백엔드 주니어 개발자")
                .compensation(1000000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .stack("Python")
                .company(company)
                .build();
        Long recruitmentId = recruitmentRepository.save(recruitment).getId();
        ApplyCreateReq dto = ApplyCreateReq.builder()
                .recruitmentId(recruitmentId)
                .memberId(memberId)
                .build();
        // when
        Long applyId = applyService.apply(dto);
        // then
        Apply apply = applyRepository.findById(applyId).orElse(null);
        assertThat(apply).isNotNull();
        assertThat(apply.getMember().getId()).isEqualTo(memberId);
        assertThat(apply.getRecruitment().getId()).isEqualTo(recruitmentId);
    }

    @DisplayName("채용공고 지원 - 사용자는 같은 공고에는 1회만 지원할 수 있다.")
    @Test
    void applyTest2() {
        // given
        Member member = new Member("승연", "email@naver.com");
        Long memberId = memberRepository.save(member).getId();
        Company company = Company.builder()
                .name("원티드랩")
                .nation("한국")
                .region("서울")
                .build();
        companyRepository.save(company);
        Recruitment recruitment = Recruitment.builder()
                .position("백엔드 주니어 개발자")
                .compensation(1000000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .stack("Python")
                .company(company)
                .build();
        Long recruitmentId = recruitmentRepository.save(recruitment).getId();
        ApplyCreateReq dto = ApplyCreateReq.builder()
                .recruitmentId(recruitmentId)
                .memberId(memberId)
                .build();
        applyService.apply(dto);
        // when, then
        BusinessException exception = Assertions.assertThrows(BusinessException.class,
                () -> applyService.apply(dto)
        );
        assertThat(exception.getMessage()).isEqualTo(ErrorCode.APPLY_DUPLICATE.getMessage());
    }
}