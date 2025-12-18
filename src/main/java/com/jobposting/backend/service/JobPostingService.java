package com.jobposting.backend.service;

import com.jobposting.backend.dto.JobPostingListResponse;
import com.jobposting.backend.entity.JobPosting;
import com.jobposting.backend.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final JobBookmarkService jobBookmarkService;

    /**
     * 채용공고 목록 조회 + 찜 여부 포함
     */
    public List<JobPostingListResponse> getJobPostingList(Long userId) {

        List<JobPosting> postings = jobPostingRepository.findAll();

        return postings.stream()
                .map(posting ->
                        JobPostingListResponse.from(
                                posting,
                                jobBookmarkService.isBookmarked(
                                        userId,
                                        posting.getJobPostingId()
                                )
                        )
                )
                .toList();
    }
}