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
    private String companyType;
    private String employmentType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private String workRegion;
    private String careerRequirement;
    private String educationRequirement;
    private String recruitSummary;
    private String applyUrl;
    private String mobileApplyUrl;
    private String logoFile;

    private String submitDocuments;
    private String applyMethod;
    private String passAnnounce;
    private String inquiry;
    private String etc;
    private String homepageUrl;
    private String commonDescription;
    private String rawPayload;

    public static JobPostingResponse from(JobPosting entity) {
        return JobPostingResponse.builder()
                .id(entity.getId())
                .externalId(entity.getExternalId())
                .sourceType(entity.getSourceType())
                .title(entity.getTitle())
                .companyName(entity.getCompanyName())
                .companyType(entity.getCompanyType())
                .employmentType(entity.getEmploymentType())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .isActive(entity.getIsActive())
                .workRegion(entity.getWorkRegion())
                .careerRequirement(entity.getCareerRequirement())
                .educationRequirement(entity.getEducationRequirement())
                .recruitSummary(entity.getRecruitSummary())
                .applyUrl(entity.getApplyUrl())
                .mobileApplyUrl(entity.getMobileApplyUrl())
                .logoFile(entity.getLogoFile())
                .submitDocuments(entity.getSubmitDocuments())
                .applyMethod(entity.getApplyMethod())
                .passAnnounce(entity.getPassAnnounce())
                .inquiry(entity.getInquiry())
                .etc(entity.getEtc())
                .homepageUrl(entity.getHomepageUrl())
                .commonDescription(entity.getCommonDescription())
                .rawPayload(entity.getRawPayload())
                .build();
    }
}
