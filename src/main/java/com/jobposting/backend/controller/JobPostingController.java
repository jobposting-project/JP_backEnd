package com.jobposting.backend.controller;

import com.jobposting.backend.dto.JobPostingListResponse;
import com.jobposting.backend.security.CustomUserDetails;
import com.jobposting.backend.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-postings")
public class JobPostingController {

    private final JobPostingService jobPostingService;

    /**
     * 채용공고 목록 조회 (찜 여부 포함)
     */
    @GetMapping
    public ResponseEntity<List<JobPostingListResponse>> getJobPostingList(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUser().getUserId();
        List<JobPostingListResponse> response =
                jobPostingService.getJobPostingList(userId);
        return ResponseEntity.ok(response);
    }
}