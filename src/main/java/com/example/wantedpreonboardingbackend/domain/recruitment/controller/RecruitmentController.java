package com.example.wantedpreonboardingbackend.domain.recruitment.controller;

import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentCreateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentUpdateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.service.RecruitmentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {
    private final RecruitmentServiceImpl recruitmentServiceImpl;

    // 채용공고 등록
    @PostMapping("")
    public ResponseEntity<?> registerRecruitment(@RequestBody @Valid RecruitmentCreateReq dto) {
        recruitmentServiceImpl.registerRecruitment(dto);
        return ResponseEntity.ok(null);
    }

    // 채용공고 수정
    @PatchMapping("/{recruitment-id}")
    public ResponseEntity<?> updateRecruitment(@PathVariable(name = "recruitment-id") Long id,
                                               @RequestBody @Valid RecruitmentUpdateReq dto) {
        recruitmentServiceImpl.updateRecruitment(id, dto);
        return ResponseEntity.ok(null);
    }
}
