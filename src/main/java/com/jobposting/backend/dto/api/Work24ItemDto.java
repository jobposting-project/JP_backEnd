package com.jobposting.backend.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.jobposting.backend.dto.JobPostingCreateRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Work24ItemDto {

    @JacksonXmlProperty(localName = "empSeqno")
    private String empSeqno; // externalId

    @JacksonXmlProperty(localName = "empBusiNm")
    private String empBusiNm; // companyName

    @JacksonXmlProperty(localName = "empWantedTitle")
    private String empWantedTitle; // title

    @JacksonXmlProperty(localName = "coClcdNm")
    private String coClcdNm; // companyType

    @JacksonXmlProperty(localName = "empWantedTypeNm")
    private String empWantedTypeNm; // employmentType

    @JacksonXmlProperty(localName = "empWantedStdt")
    private String empWantedStdt; // startDate (yyyyMMdd)

    @JacksonXmlProperty(localName = "empWantedEndt")
    private String empWantedEndt; // endDate (yyyyMMdd)

    @JacksonXmlProperty(localName = "empWantedHomepgDetail")
    private String empWantedHomepgDetail; // applyUrl

    @JacksonXmlProperty(localName = "empWantedMobileUrl")
    private String empWantedMobileUrl; // mobileApplyUrl

    @JacksonXmlProperty(localName = "regLogImgNm")
    private String regLogImgNm; // logoFile

    @JacksonXmlProperty(localName = "empRcptMthdCont")
    private String empRcptMthdCont; // applyMethod

    // DTO 변환 메서드
    public JobPostingCreateRequest toCreateRequest(String jobCategory) {
        LocalDate start = parseDate(empWantedStdt);
        LocalDate end = parseDate(empWantedEndt);

        // 지원 링크 우선순위: 상세홈페이지 > 모바일URL
        String finalApplyUrl = (empWantedHomepgDetail != null && !empWantedHomepgDetail.isEmpty())
                ? empWantedHomepgDetail
                : empWantedMobileUrl;

        return JobPostingCreateRequest.builder()
                .externalId(empSeqno)
                .sourceType("WORK24")
                .title(empWantedTitle)
                .companyName(empBusiNm)
                .jobCategory(jobCategory) // 직군명 주입
                .employmentType(empWantedTypeNm)
                .startDate(start)
                .endDate(end)
                .applyUrl(finalApplyUrl)
                .build();
    }

    private LocalDate parseDate(String dateStr) {
        try {
            if (dateStr == null || dateStr.isEmpty())
                return null;
            // yyyyMMdd 형식
            return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            return null;
        }
    }
}
