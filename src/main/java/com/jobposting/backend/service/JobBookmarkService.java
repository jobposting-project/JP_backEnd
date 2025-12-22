package com.jobposting.backend.service;

import com.jobposting.backend.dto.JobBookmarkResponse;
import com.jobposting.backend.entity.JobBookmark;
import com.jobposting.backend.entity.User;
import com.jobposting.backend.repository.JobBookmarkRepository;
import com.jobposting.backend.repository.JobPostingRepository;
import com.jobposting.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobBookmarkService {

    private final JobBookmarkRepository jobBookmarkRepository;
    private final JobPostingRepository jobPostingRepository;
    private final UserRepository userRepository;

    /**
     * 찜 추가
     */
    public JobBookmark addBookmark(Long userId, Long jobPostingId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new RuntimeException("Job posting not found"));

        jobBookmarkRepository.findByUserAndJobPosting(user, jobPosting)
                .ifPresent(b -> {
                    throw new RuntimeException("Already bookmarked");
                });

        JobBookmark bookmark = JobBookmark.builder()
                .user(user)
                .jobPosting(jobPosting)
                .build();

        return jobBookmarkRepository.save(bookmark);
    }

    /**
     * 찜 삭제
     */
    public void removeBookmark(Long userId, Long jobPostingId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new RuntimeException("Job posting not found"));

        JobBookmark bookmark = jobBookmarkRepository.findByUserAndJobPosting(user, jobPosting)
                .orElseThrow(() -> new RuntimeException("Bookmark not found"));

        jobBookmarkRepository.delete(bookmark);
    }

    /**
     * 찜 목록 조회 (DTO 반환)
     */
    public List<JobBookmarkResponse> getMyBookmarks(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return jobBookmarkRepository.findAllByUser(user)
                .stream()
                .map(bookmark -> JobBookmarkResponse.from(bookmark.getJobPosting()))
                .collect(Collectors.toList());
    }

    /**
     * 특정 공고 찜 여부 확인
     */
    public boolean isBookmarked(Long userId, Long jobPostingId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new RuntimeException("Job posting not found"));

        return jobBookmarkRepository.existsByUserAndJobPosting(user, jobPosting);
    }
}