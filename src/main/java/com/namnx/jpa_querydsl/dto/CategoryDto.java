package com.namnx.jpa_querydsl.dto;

import com.namnx.jpa_querydsl.model.ProductEntity;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private String categoryName;
    private Long categoryId;
    private List<ProductEntity> products;

    @QueryProjection
    public CategoryDto(String categoryName,
                       Long categoryId,
                       List<ProductEntity> products) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.products = products;
    }

    @QueryProjection
    public CategoryDto(String categoryName, Long categoryId) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }
}
