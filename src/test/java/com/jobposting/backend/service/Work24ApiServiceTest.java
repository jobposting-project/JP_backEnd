package com.jobposting.backend.service;

import com.jobposting.backend.dto.JobPostingCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Work24ApiServiceTest {

    @Autowired
    private Work24ApiService work24ApiService;

    @Test
    @DisplayName("워크넷 API 연동 테스트 - 실제 호출")
    void fetchJobPostings() {
        // given
        int startPage = 1;
        int display = 100;

        // when
        List<JobPostingCreateRequest> results = work24ApiService.fetchJobPostings(startPage, display);

        // then
        // API 키가 유효하지 않거나 데이터가 없으면 빈 리스트가 반환될 수 있음
        // 하지만 에러가 발생하지 않아야 함
        System.out.println("Fetched Items Count: " + results.size());
        if (!results.isEmpty()) {
            JobPostingCreateRequest firstItem = results.get(0);
            System.out.println("First Item Title: " + firstItem.getTitle());
            System.out.println("First Item Company: " + firstItem.getCompanyName());

            assertThat(firstItem.getTitle()).isNotNull();
            assertThat(firstItem.getCompanyName()).isNotNull();
        }
    }
}
