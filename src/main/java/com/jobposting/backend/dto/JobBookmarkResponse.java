package com.jobposting.backend.dto;

import com.jobposting.backend.entity.JobPosting;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class JobBookmarkResponse {

    private Long jobPostingId;
    private String title;
    private String companyName;
    private LocalDate endDate;
    private Integer dday;
    private String sourceUrl;

    /**
     * JobPosting 엔티티 → JobBookmarkResponse 변환
     */
    public static JobBookmarkResponse from(JobPosting jobPosting) {
        return JobBookmarkResponse.builder()
                .jobPostingId(jobPosting.getJobPostingId())
                .title(jobPosting.getTitle())
                .companyName(jobPosting.getCompanyName())
                .endDate(jobPosting.getEndDate())
                .dday(jobPosting.getDday())
                .sourceUrl(jobPosting.getSourceUrl())
                .build();
    }
}