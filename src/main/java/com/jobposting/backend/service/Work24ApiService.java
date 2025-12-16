package com.jobposting.backend.service;

import com.jobposting.backend.dto.JobPostingCreateRequest;
import com.jobposting.backend.dto.api.Work24ApiDto;
import com.jobposting.backend.dto.api.Work24ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class Work24ApiService {

    @Value("${work24.api.key}")
    private String authKey;

    @Value("${work24.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<JobPostingCreateRequest> fetchJobPostings(int startPage, int display) {
        // IT 관련 직종 코드 목록 (사용자 제공 표 기준)
        // [소프트웨어]
        // 133100: 시스템SW, 133200: 응용SW, 133300: 웹개발자, 133900: QA/테스터
        // [네트워크 및 보안]
        // 134100: 네트워크 시스템 개발자, 134200: 정보 보안 전문가, 134201: 정보보호 전문가, 134202: 침해사고 대응 전문가, 134900: 기타 데이터/네트워크
        // [데이터]
        // 135100: 데이터 시스템 전문가, 135101: 데이터 설계 및 프로그래머, 135102: DB 운영/관리, 135200: 데이터 분석가
        // [시스템 운영 및 관리]
        // 136101: 정보시스템 운영자, 136102: 네트워크/클라우드 관리자, 136103: IT 기술지원, 136200: 웹 운영자
        List<String> jobCodes = List.of(
                "133100", "133200", "133300", "133900",
                "134100", "134200", "134201", "134202", "134900",
                "135100", "135101", "135102", "135200",
                "136101", "136102", "136103", "136200"
        );
        List<JobPostingCreateRequest> allRequests = new java.util.ArrayList<>();

        for (String jobCode : jobCodes) {
            try {
                URI uri = UriComponentsBuilder.fromHttpUrl(apiUrl)
                        .queryParam("authKey", authKey)
                        .queryParam("callTp", "L") // L: 목록
                        .queryParam("returnType", "XML")
                        .queryParam("startPage", startPage)
                        .queryParam("display", display) // 각 코드별로 display 개수만큼 요청
                        .queryParam("jobsCd", jobCode)
                        .build()
                        .toUri();

                log.info("Fetching Job Postings from Work24 for jobCode {}: {}", jobCode, uri);

                ResponseEntity<Work24ApiDto> response = restTemplate.getForEntity(uri, Work24ApiDto.class);
                Work24ApiDto body = response.getBody();

                if (body != null && body.getItems() != null) {
                    log.info("Fetched {} items for jobCode {}", body.getItems().size(), jobCode);
                    List<JobPostingCreateRequest> requests = body.getItems().stream()
                            .map(Work24ItemDto::toCreateRequest)
                            .collect(Collectors.toList());
                    allRequests.addAll(requests);
                } else {
                    log.warn("No items found for jobCode {}", jobCode);
                }

            } catch (Exception e) {
                log.error("Failed to fetch job postings for jobCode " + jobCode, e);
                // 하나의 코드가 실패해도 나머지는 계속 진행
            }
        }

        return allRequests;
    }
}
