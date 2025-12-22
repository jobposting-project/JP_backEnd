package com.jobposting.backend.dto;

import com.jobposting.backend.entity.JobPosting;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class JobPostingListResponse {

    private Long jobPostingId;
    private String title;
    private String companyName;
    private LocalDate endDate;
    private Integer dday;
    private boolean isBookmarked; // ⭐ 핵심

    public static JobPostingListResponse from(
            JobPosting jobPosting,
            boolean isBookmarked
    ) {
        return JobPostingListResponse.builder()
                .jobPostingId(jobPosting.getJobPostingId())
                .title(jobPosting.getTitle())
                .companyName(jobPosting.getCompanyName())
                .endDate(jobPosting.getEndDate())
                .dday(jobPosting.getDday())
                .isBookmarked(isBookmarked)
                .build();
    }
}