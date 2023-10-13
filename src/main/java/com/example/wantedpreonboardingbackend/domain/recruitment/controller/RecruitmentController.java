package com.example.wantedpreonboardingbackend.domain.recruitment.controller;

import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentCreateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentUpdateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.response.RecruitmentDetailResp;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.response.RecruitmentResp;
import com.example.wantedpreonboardingbackend.domain.recruitment.service.RecruitmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    // 채용공고 등록
    @PostMapping("")
    public ResponseEntity<?> registerRecruitment(@RequestBody @Valid RecruitmentCreateReq dto) {
        recruitmentService.registerRecruitment(dto);
        return ResponseEntity.ok(null);
    }

    // 채용공고 수정
    @PatchMapping("/{recruitment-id}")
    public ResponseEntity<?> updateRecruitment(@PathVariable(name = "recruitment-id") Long id,
                                               @RequestBody @Valid RecruitmentUpdateReq dto) {
        recruitmentService.updateRecruitment(id, dto);
        return ResponseEntity.ok(null);
    }

    // 채용공고 삭제
    @DeleteMapping("/{recruitment-id}")
    public ResponseEntity<?> deleteRecruitment(@PathVariable(name = "recruitment-id") Long id) {
        recruitmentService.deleteRecruitment(id);
        return ResponseEntity.ok(null);
    }

    // 채용공고 목록 조회
    @GetMapping("")
    public ResponseEntity<?> getAllRecruitment(@RequestParam(required = false, defaultValue = "") String search) {
        List<RecruitmentResp> recruitments = recruitmentService.getAllRecruitment(search);
        return ResponseEntity.ok(recruitments);
    }

    // 채용공고 상세조회
    @GetMapping("/{recruitment-id}")
    public ResponseEntity<?> getRecruitmentDetail(@PathVariable(name = "recruitment-id") Long id) {
        RecruitmentDetailResp recruitment = recruitmentService.getRecruitmentDetail(id);
        return ResponseEntity.ok(recruitment);
    }
}
