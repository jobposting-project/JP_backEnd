package com.jobposting.backend.service;

import com.jobposting.backend.dto.JobPostingCreateRequest;
import com.jobposting.backend.dto.JobPostingResponse;
import com.jobposting.backend.entity.JobPosting;
import com.jobposting.backend.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    // 공고 등록 (외부 API 데이터 저장용)
    @Transactional
    public Long createJobPosting(JobPostingCreateRequest request) {
        // 중복 체크
        if (jobPostingRepository.existsByExternalIdAndSourceType(request.getExternalId(), request.getSourceType())) {
            // 이미 존재하면 저장하지 않음 (또는 업데이트 로직 추가 가능)
            return null;
        }

        JobPosting jobPosting = request.toEntity();
        jobPostingRepository.save(jobPosting);
        return jobPosting.getId();
    }

    // 공고 목록 조회
    public Page<JobPostingResponse> getJobPostings(Pageable pageable) {
        return jobPostingRepository.findAll(pageable)
                .map(JobPostingResponse::from);
    }

    // 공고 상세 조회
    public JobPostingResponse getJobPosting(Long id) {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 채용공고가 없습니다. id=" + id));
        return JobPostingResponse.from(jobPosting);
    }
}
