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

    @Column(name = "job_category", length = 100)
    private String jobCategory; // 직군명 (New)

    @Column(name = "employment_type", length = 100)
    private String employmentType; // 고용형태

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "apply_url", length = 500)
    private String applyUrl; // 지원하기 URL

    @Builder
    public JobPosting(String externalId, String sourceType, String title, String companyName,
            String jobCategory, String employmentType, LocalDate startDate, LocalDate endDate,
            String applyUrl, Boolean isActive) {
        this.externalId = externalId;
        this.sourceType = sourceType != null ? sourceType : "WORK24";
        this.title = title;
        this.companyName = companyName;
        this.jobCategory = jobCategory;
        this.employmentType = employmentType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applyUrl = applyUrl;
        this.isActive = isActive != null ? isActive : true;
    }
}
