package com.namnx.jpa_querydsl.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FilterProductDto extends PageParam {
    private String nameOrCodeKeyword;
    private Integer quantity;
    private Double price;
    private Long categoryId;

}
