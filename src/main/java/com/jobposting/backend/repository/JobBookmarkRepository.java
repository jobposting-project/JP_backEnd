package com.jobposting.backend.repository;

import com.jobposting.backend.entity.JobBookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobBookmarkRepository extends JpaRepository<JobBookmark, Long> {
}
