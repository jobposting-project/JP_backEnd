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
    private String companyType;
    private String employmentType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String submitDocuments;
    private String applyMethod;
    private String passAnnounce;
    private String inquiry;
    private String etc;
    private String homepageUrl;
    private String applyUrl;
    private String mobileApplyUrl;
    private String logoFile;
    private String workRegion;
    private String careerRequirement;
    private String educationRequirement;
    private String recruitSummary;
    private String commonDescription;
    private String rawPayload;

    @Builder
    public JobPostingCreateRequest(String externalId, String sourceType, String title, String companyName,
            String companyType, String employmentType, LocalDate startDate, LocalDate endDate, String submitDocuments,
            String applyMethod, String passAnnounce, String inquiry, String etc, String homepageUrl, String applyUrl,
            String mobileApplyUrl, String logoFile, String workRegion, String careerRequirement,
            String educationRequirement, String recruitSummary, String commonDescription, String rawPayload) {
        this.externalId = externalId;
        this.sourceType = sourceType;
        this.title = title;
        this.companyName = companyName;
        this.companyType = companyType;
        this.employmentType = employmentType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.submitDocuments = submitDocuments;
        this.applyMethod = applyMethod;
        this.passAnnounce = passAnnounce;
        this.inquiry = inquiry;
        this.etc = etc;
        this.homepageUrl = homepageUrl;
        this.applyUrl = applyUrl;
        this.mobileApplyUrl = mobileApplyUrl;
        this.logoFile = logoFile;
        this.workRegion = workRegion;
        this.careerRequirement = careerRequirement;
        this.educationRequirement = educationRequirement;
        this.recruitSummary = recruitSummary;
        this.commonDescription = commonDescription;
        this.rawPayload = rawPayload;
    }

    public JobPosting toEntity() {
        return JobPosting.builder()
                .externalId(externalId)
                .sourceType(sourceType)
                .title(title)
                .companyName(companyName)
                .companyType(companyType)
                .employmentType(employmentType)
                .startDate(startDate)
                .endDate(endDate)
                .submitDocuments(submitDocuments)
                .applyMethod(applyMethod)
                .passAnnounce(passAnnounce)
                .inquiry(inquiry)
                .etc(etc)
                .homepageUrl(homepageUrl)
                .applyUrl(applyUrl)
                .mobileApplyUrl(mobileApplyUrl)
                .logoFile(logoFile)
                .workRegion(workRegion)
                .careerRequirement(careerRequirement)
                .educationRequirement(educationRequirement)
                .recruitSummary(recruitSummary)
                .commonDescription(commonDescription)
                .rawPayload(rawPayload)
                .isActive(true)
                .build();
    }
}
