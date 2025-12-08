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

    @Column(length = 20)
    private String source;

    @Column(name = "source_job_id", length = 100)
    private String sourceJobId;

    @Column(length = 300)
    private String title;

    @Column(name = "company_name", length = 200)
    private String companyName;

    @Column(name = "location_main", length = 200)
    private String locationMain;

    @Column(name = "location_detail", length = 300)
    private String locationDetail;

    @Column(name = "career_level", length = 100)
    private String careerLevel;

    @Column(name = "employment_type", length = 100)
    private String employmentType;

    @Column(name = "education_level", length = 100)
    private String educationLevel;

    @Column(name = "job_category_code", length = 100)
    private String jobCategoryCode;

    @Column(name = "job_category_name", length = 100)
    private String jobCategoryName;

    @Column(name = "salary_text", length = 200)
    private String salaryText;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private Integer dday;

    @Column(name = "is_active")
    private Boolean isActive;

    private Integer headcount;

    @Column(name = "requirement_summary", columnDefinition = "TEXT")
    private String requirementSummary;

    @Column(name = "preference_summary", columnDefinition = "TEXT")
    private String preferenceSummary;

    @Column(name = "apply_method", columnDefinition = "TEXT")
    private String applyMethod;

    @Column(name = "source_url", length = 500)
    private String sourceUrl;

    @Builder
    public JobPosting(String source, String sourceJobId, String title, String companyName, String locationMain,
            String locationDetail, String careerLevel, String employmentType, String educationLevel,
            String jobCategoryCode, String jobCategoryName, String salaryText, LocalDate startDate, LocalDate endDate,
            Integer dday, Boolean isActive, Integer headcount, String requirementSummary, String preferenceSummary,
            String applyMethod, String sourceUrl) {
        this.source = source;
        this.sourceJobId = sourceJobId;
        this.title = title;
        this.companyName = companyName;
        this.locationMain = locationMain;
        this.locationDetail = locationDetail;
        this.careerLevel = careerLevel;
        this.employmentType = employmentType;
        this.educationLevel = educationLevel;
        this.jobCategoryCode = jobCategoryCode;
        this.jobCategoryName = jobCategoryName;
        this.salaryText = salaryText;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dday = dday;
        this.isActive = isActive;
        this.headcount = headcount;
        this.requirementSummary = requirementSummary;
        this.preferenceSummary = preferenceSummary;
        this.applyMethod = applyMethod;
        this.sourceUrl = sourceUrl;
    }
}
