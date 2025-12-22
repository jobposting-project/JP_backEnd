package com.jobposting.backend.dto;

import com.jobposting.backend.entity.JobPosting;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class JobPostingCreateRequest {
    private String externalId;
    private String sourceType;
    private String title;
    private String companyName;
    private String jobCategory;
    private String employmentType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String applyUrl;

    @Builder
    public JobPostingCreateRequest(String externalId, String sourceType, String title, String companyName,
            String jobCategory, String employmentType, LocalDate startDate, LocalDate endDate,
            String applyUrl) {
        this.externalId = externalId;
        this.sourceType = sourceType;
        this.title = title;
        this.companyName = companyName;
        this.jobCategory = jobCategory;
        this.employmentType = employmentType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applyUrl = applyUrl;
    }

    public JobPosting toEntity() {
        return JobPosting.builder()
                .externalId(externalId)
                .sourceType(sourceType)
                .title(title)
                .companyName(companyName)
                .jobCategory(jobCategory)
                .employmentType(employmentType)
                .startDate(startDate)
                .endDate(endDate)
                .applyUrl(applyUrl)
                .isActive(true)
                .build();
    }
}
