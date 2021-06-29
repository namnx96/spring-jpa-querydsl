package com.namnx.jpa_querydsl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageParam {
    private Integer page;
    private Integer size;
    private String sortDir;
    private String sortField;
}
