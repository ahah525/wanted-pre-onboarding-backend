package com.example.wantedpreonboardingbackend.domain.member.service;

import com.example.wantedpreonboardingbackend.domain.member.domain.Member;
import com.example.wantedpreonboardingbackend.domain.member.repository.MemberRepository;
import com.example.wantedpreonboardingbackend.global.exception.BusinessException;
import com.example.wantedpreonboardingbackend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
