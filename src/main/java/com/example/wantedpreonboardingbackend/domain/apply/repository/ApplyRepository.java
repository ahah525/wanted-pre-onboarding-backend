package com.example.wantedpreonboardingbackend.domain.apply.repository;

import com.example.wantedpreonboardingbackend.domain.apply.domain.Apply;
import com.example.wantedpreonboardingbackend.domain.member.domain.Member;
import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    Boolean existsByMemberAndRecruitment(Member member, Recruitment recruitment);
}
