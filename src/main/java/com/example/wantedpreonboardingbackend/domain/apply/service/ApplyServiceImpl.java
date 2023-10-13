package com.example.wantedpreonboardingbackend.domain.apply.service;

import com.example.wantedpreonboardingbackend.domain.apply.domain.Apply;
import com.example.wantedpreonboardingbackend.domain.apply.dto.request.ApplyCreateReq;
import com.example.wantedpreonboardingbackend.domain.apply.repository.ApplyRepository;
import com.example.wantedpreonboardingbackend.domain.member.domain.Member;
import com.example.wantedpreonboardingbackend.domain.member.service.MemberService;
import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.domain.recruitment.service.RecruitmentService;
import com.example.wantedpreonboardingbackend.global.exception.BusinessException;
import com.example.wantedpreonboardingbackend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {
    private final MemberService memberService;
    private final RecruitmentService recruitmentService;
    private final ApplyRepository applyRepository;

    @Override
    @Transactional
    public Long apply(ApplyCreateReq dto) {
        Member member = memberService.findById(dto.getMemberId());
        Recruitment recruitment = recruitmentService.findById(dto.getRecruitmentId());
        // 이미 지원한 채용 공고에 다시 지원하는 경우 예외처리
        if(applyRepository.existsByMemberAndRecruitment(member, recruitment)) {
            throw new BusinessException(ErrorCode.APPLY_DUPLICATE);
        }
        Apply apply = Apply.builder()
                .member(member)
                .recruitment(recruitment)
                .build();
        return applyRepository.save(apply).getId();
    }
}
