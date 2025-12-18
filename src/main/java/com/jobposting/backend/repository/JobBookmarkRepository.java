package com.jobposting.backend.repository;

import com.jobposting.backend.entity.JobBookmark;
import com.jobposting.backend.entity.JobPosting;
import com.jobposting.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface JobBookmarkRepository extends JpaRepository<JobBookmark, Long> {

    // 특정 유저가 특정 공고를 이미 찜했는지 확인
    Optional<JobBookmark> findByUserAndJobPosting(User user, JobPosting jobPosting);

    // 특정 유저가 특정 공고를 찜했는지 여부 확인 (엔티티 조회 없이, UI 상태 판단용으로)
    boolean existsByUserAndJobPosting(User user, JobPosting jobPosting);

    // 특정 유저의 찜 목록 전체 조회
    List<JobBookmark> findAllByUser(User user);
}
