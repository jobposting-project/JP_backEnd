package com.jobposting.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_posting")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobPosting extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_posting_id")
    private Long id;

    // 기본 정보
    @Column(name = "external_id", nullable = false, length = 50)
    private String externalId;

    @Column(name = "source_type", nullable = false, length = 20)
    private String sourceType = "WORK24";

    @Column(nullable = false)
    private String title;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    // 상세 정보
    @Column(name = "location_main")
    private String locationMain;

    @Column(name = "location_detail")
    private String locationDetail;

    @Column(name = "career_level")
    private String careerLevel;

    @Column(name = "job_category", length = 100)
    private String jobCategory;

    @Column(name = "job_category_code")
    private String jobCategoryCode;

    @Column(name = "employment_type", length = 100)
    private String employmentType;

    @Column(name = "education_level")
    private String educationLevel;

    @Column(name = "salary_text")
    private String salaryText;

    // 날짜 및 활성화
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "dday")
    private Integer dday;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "headcount")
    private Integer headcount;

    // 상세 설명
    @Column(name = "requirement_summary", columnDefinition = "TEXT")
    private String requirementSummary;

    @Column(name = "preference_summary", columnDefinition = "TEXT")
    private String preferenceSummary;

    @Column(name = "apply_method", columnDefinition = "TEXT")
    private String applyMethod;

    // URL
    @Column(name = "apply_url", length = 500)
    private String applyUrl;

    @Column(name = "source_url")
    private String sourceUrl;

    // 색인 필드 (nullable 가능)
    @Column(name = "idx_job_end_date")
    private LocalDate idxJobEndDate;

    @Column(name = "idx_job_is_active")
    private Boolean idxJobIsActive;

    @Column(name = "idx_job_title")
    private String idxJobTitle;

    @Column(name = "idx_job_company")
    private String idxJobCompany;

    @Column(name = "idx_job_category")
    private String idxJobCategory;

    @Builder
    public JobPosting(String externalId, String sourceType, String title, String companyName,
                      String locationMain, String locationDetail, String careerLevel,
                      String jobCategory, String jobCategoryCode, String employmentType,
                      String educationLevel, String salaryText, LocalDate startDate,
                      LocalDate endDate, Integer dday, Boolean isActive, Integer headcount,
                      String requirementSummary, String preferenceSummary, String applyMethod,
                      String applyUrl, String sourceUrl, LocalDate idxJobEndDate,
                      Boolean idxJobIsActive, String idxJobTitle, String idxJobCompany,
                      String idxJobCategory) {
        this.externalId = externalId;
        this.sourceType = sourceType != null ? sourceType : "WORK24";
        this.title = title;
        this.companyName = companyName;
        this.locationMain = locationMain;
        this.locationDetail = locationDetail;
        this.careerLevel = careerLevel;
        this.jobCategory = jobCategory;
        this.jobCategoryCode = jobCategoryCode;
        this.employmentType = employmentType;
        this.educationLevel = educationLevel;
        this.salaryText = salaryText;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dday = dday;
        this.isActive = isActive != null ? isActive : true;
        this.headcount = headcount;
        this.requirementSummary = requirementSummary;
        this.preferenceSummary = preferenceSummary;
        this.applyMethod = applyMethod;
        this.applyUrl = applyUrl;
        this.sourceUrl = sourceUrl;
        this.idxJobEndDate = idxJobEndDate;
        this.idxJobIsActive = idxJobIsActive;
        this.idxJobTitle = idxJobTitle;
        this.idxJobCompany = idxJobCompany;
        this.idxJobCategory = idxJobCategory;
    }
}