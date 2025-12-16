package com.jobposting.backend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "job_posting")
public class JobPosting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_posting_id")
    private Long id;

    @Column(name = "external_id", nullable = false, length = 50)
    private String externalId;

    @Column(name = "source_type", nullable = false, length = 20)
    private String sourceType = "WORK24";

    @Column(nullable = false)
    private String title;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "company_type", length = 100)
    private String companyType;

    @Column(name = "employment_type", length = 100)
    private String employmentType;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "submit_documents", columnDefinition = "TEXT")
    private String submitDocuments;

    @Column(name = "apply_method", columnDefinition = "TEXT")
    private String applyMethod;

    @Column(name = "pass_announce")
    private String passAnnounce;

    @Column(columnDefinition = "TEXT")
    private String inquiry;

    @Column(columnDefinition = "TEXT")
    private String etc;

    @Column(name = "homepage_url", length = 500)
    private String homepageUrl;

    @Column(name = "apply_url", length = 500)
    private String applyUrl;

    @Column(name = "mobile_apply_url", length = 500)
    private String mobileApplyUrl;

    @Column(name = "logo_file")
    private String logoFile;

    @Column(name = "work_region")
    private String workRegion;

    @Column(name = "career_requirement")
    private String careerRequirement;

    @Column(name = "education_requirement")
    private String educationRequirement;

    @Column(name = "recruit_summary", columnDefinition = "TEXT")
    private String recruitSummary;

    @Column(name = "common_description", columnDefinition = "TEXT")
    private String commonDescription;

    @Column(name = "raw_payload", columnDefinition = "MEDIUMTEXT")
    private String rawPayload;

    @Builder
    public JobPosting(String externalId, String sourceType, String title, String companyName, String companyType,
            String employmentType, LocalDate startDate, LocalDate endDate, Boolean isActive, String submitDocuments,
            String applyMethod, String passAnnounce, String inquiry, String etc, String homepageUrl, String applyUrl,
            String mobileApplyUrl, String logoFile, String workRegion, String careerRequirement,
            String educationRequirement, String recruitSummary, String commonDescription, String rawPayload) {
        this.externalId = externalId;
        this.sourceType = sourceType != null ? sourceType : "WORK24";
        this.title = title;
        this.companyName = companyName;
        this.companyType = companyType;
        this.employmentType = employmentType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive != null ? isActive : true;
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
}
