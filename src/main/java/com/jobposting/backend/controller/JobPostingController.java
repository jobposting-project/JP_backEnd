package com.jobposting.backend.controller;

import com.jobposting.backend.dto.JobPostingCreateRequest;
import com.jobposting.backend.dto.JobPostingListResponse;
import com.jobposting.backend.dto.JobPostingResponse;
import com.jobposting.backend.security.CustomUserDetails;
import com.jobposting.backend.service.JobPostingService;
import com.jobposting.backend.service.Work24ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-postings")
public class JobPostingController {
    
    private final JobPostingService jobPostingService;
    private final Work24ApiService work24ApiService;

    /**
     * 채용공고 목록 조회 (찜 여부 포함)
     * - 로그인 O: 찜 여부 포함
     * - 로그인 X: 찜 여부 없이 목록만
     */
    @GetMapping
    public ResponseEntity<List<JobPostingListResponse>> getJobPostingList(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        // 로그인했으면 userId 사용, 아니면 null
        Long userId = userDetails != null ? userDetails.getUser().getUserId() : null;
        List<JobPostingListResponse> response =
                jobPostingService.getJobPostingList(userId);
        return ResponseEntity.ok(response);
    }

    // 공고 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<JobPostingResponse> getJobPosting(@PathVariable Long id) {
        return ResponseEntity.ok(jobPostingService.getJobPosting(id));
    }

    // 워크넷 데이터 수동 동기화 (테스트용)
    // 실제로는 스케줄러가 하겠지만, Postman 테스트를 위해 엔드포인트 생성
    @PostMapping("/sync")
    public ResponseEntity<String> syncJobPostings() {
        // 1페이지 100개만 가져오도록 설정 (IT 필터링 적용됨)
        List<JobPostingCreateRequest> requests = work24ApiService.fetchJobPostings(1, 100);
        int savedCount = 0;
        for (JobPostingCreateRequest request : requests) {
            jobPostingService.createJobPosting(request);
            savedCount++;
        }
        return ResponseEntity.ok("Synced " + savedCount + " job postings from Work24.");
    }
}