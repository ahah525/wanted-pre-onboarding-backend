package com.example.wantedpreonboardingbackend.domain.apply.service;

import com.example.wantedpreonboardingbackend.domain.apply.dto.request.ApplyCreateReq;

public interface ApplyService {
    Long apply(ApplyCreateReq dto);
}
