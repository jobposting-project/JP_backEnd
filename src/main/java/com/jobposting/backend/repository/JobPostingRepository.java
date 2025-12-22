package com.jobposting.backend.repository;

import com.jobposting.backend.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    
    boolean existsByExternalIdAndSourceType(String externalId, String sourceType);
}