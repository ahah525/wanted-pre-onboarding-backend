package com.example.wantedpreonboardingbackend.domain.member.service;

import com.example.wantedpreonboardingbackend.domain.member.domain.Member;

public interface MemberService {
    Member findById(Long id);
}
