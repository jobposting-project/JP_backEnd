package com.jobposting.backend.controller;

import com.jobposting.backend.dto.JobPostingCreateRequest;
import com.jobposting.backend.dto.JobPostingResponse;
import com.jobposting.backend.service.JobPostingService;
import com.jobposting.backend.service.Work24ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-postings")
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;
    private final Work24ApiService work24ApiService;

    // 1. 공고 목록 조회 (DB에서)
    @GetMapping
    public ResponseEntity<Page<JobPostingResponse>> getJobPostings(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(jobPostingService.getJobPostings(pageable));
    }

    // 2. 공고 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<JobPostingResponse> getJobPosting(@PathVariable Long id) {
        return ResponseEntity.ok(jobPostingService.getJobPosting(id));
    }

    // 3. 워크넷 데이터 수동 동기화 (테스트용)
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
