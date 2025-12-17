package com.jobposting.backend.controller;

import com.jobposting.backend.entity.JobBookmark;

import com.jobposting.backend.dto.JobBookmarkResponse;
import com.jobposting.backend.security.CustomUserDetails;
import com.jobposting.backend.service.JobBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmarks")
public class JobBookmarkController {

    private final JobBookmarkService jobBookmarkService;

    /**
     * 찜 추가
     */
    @PostMapping("/{jobPostingId}")
    public ResponseEntity<JobBookmarkResponse> addBookmark(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long jobPostingId
    ) {
        Long userId = userDetails.getUser().getUserId();
        JobBookmark bookmark = jobBookmarkService.addBookmark(userId, jobPostingId);
        return ResponseEntity.ok(JobBookmarkResponse.from(bookmark.getJobPosting()));
    }

    /**
     * 찜 삭제
     */
    @DeleteMapping("/{jobPostingId}")
    public ResponseEntity<?> removeBookmark(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long jobPostingId
    ) {
        Long userId = userDetails.getUser().getUserId();
        jobBookmarkService.removeBookmark(userId, jobPostingId);
        return ResponseEntity.ok("Bookmark removed");
    }

    /**
     * 찜 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<JobBookmarkResponse>> getMyBookmarks(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUser().getUserId();
        List<JobBookmarkResponse> bookmarks = jobBookmarkService.getMyBookmarks(userId);
        return ResponseEntity.ok(bookmarks);
    }

    /**
     * 특정 채용공고 찜 여부 확인
     */
    @GetMapping("/{jobPostingId}/exists")
    public ResponseEntity<Boolean> isBookmarked(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long jobPostingId
    ) {
        Long userId = userDetails.getUser().getUserId();
        boolean isBookmarked = jobBookmarkService.isBookmarked(userId, jobPostingId);
        return ResponseEntity.ok(isBookmarked);
    }
}
