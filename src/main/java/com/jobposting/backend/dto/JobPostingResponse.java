package com.jobposting.backend.dto;

import com.jobposting.backend.entity.JobPosting;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class JobPostingResponse {
    private Long id;
    private String externalId;
    private String sourceType;
    private String title;
    private String companyName;
    private String jobCategory; // 직군명 (New)
    private String employmentType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private String applyUrl;

    public static JobPostingResponse from(JobPosting entity) {
        return JobPostingResponse.builder()
                .id(entity.getId())
                .externalId(entity.getExternalId())
                .sourceType(entity.getSourceType())
                .title(entity.getTitle())
                .companyName(entity.getCompanyName())
                .jobCategory(entity.getJobCategory())
                .employmentType(entity.getEmploymentType())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .isActive(entity.getIsActive())
                .applyUrl(entity.getApplyUrl())
                .build();
    }
}
