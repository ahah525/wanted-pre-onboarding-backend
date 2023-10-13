package com.example.wantedpreonboardingbackend.domain.apply.domain;

import com.example.wantedpreonboardingbackend.domain.member.domain.Member;
import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        uniqueConstraints = {
                // 사용자는 채용공고에 1회만 지원할 수 있으므로 unique 조건 추가
                @UniqueConstraint(
                        name = "unique__member_id_recruitment_id",
                        columnNames = {"member_id", "recruitment_id"}
                )
        }
)
public class Apply extends BaseEntity {
    @ManyToOne
    private Member member;

    @ManyToOne
    private Recruitment recruitment;
}
