package com.jobposting.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "job_posting")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobPostingId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String source;
    private String sourceJobId;
    private String title;
    private String companyName;
    private String locationMain;
    private String locationDetail;
    private String careerLevel;
    private String employmentType;
    private String educationLevel;
    private String jobCategoryCode;
    private String jobCategoryName;
    private String salaryText;

    private LocalDate startDate;
    private LocalDate endDate;

    private Integer dday;
    private Boolean isActive;
    private Integer headcount;

    @Column(columnDefinition = "TEXT")
    private String requirementSummary;

    @Column(columnDefinition = "TEXT")
    private String preferenceSummary;

    @Column(columnDefinition = "TEXT")
    private String applyMethod;

    private String sourceUrl;

    // 색인 필드 (nullable 가능)
    private LocalDate idxJobEndDate;
    private Boolean idxJobIsActive;
    private String idxJobTitle;
    private String idxJobCompany;
    private String idxJobCategory;
}
