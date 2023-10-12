package com.example.wantedpreonboardingbackend.domain.apply.controller;

import com.example.wantedpreonboardingbackend.domain.apply.dto.request.ApplyCreateReq;
import com.example.wantedpreonboardingbackend.domain.apply.service.ApplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apply")
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping("")
    public ResponseEntity<?> apply(@RequestBody @Valid ApplyCreateReq dto) {
        applyService.apply(dto);
        return ResponseEntity.ok(null);
    }
}
