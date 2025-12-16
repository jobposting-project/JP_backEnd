package com.jobposting.backend.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JacksonXmlRootElement(localName = "dhsOpenEmpInfoList")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Work24ApiDto {

    @JacksonXmlProperty(localName = "total")
    private Integer total;

    @JacksonXmlProperty(localName = "startPage")
    private Integer startPage;

    @JacksonXmlProperty(localName = "display")
    private Integer display;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "dhsOpenEmpInfo")
    private List<Work24ItemDto> items = new java.util.ArrayList<>();
}
