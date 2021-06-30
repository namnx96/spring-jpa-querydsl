package com.namnx.jpa_querydsl.service;

import com.namnx.jpa_querydsl.dto.CategoryDto;
import com.namnx.jpa_querydsl.model.CategoryEntity;
import com.namnx.jpa_querydsl.model.ProductEntity;
import com.namnx.jpa_querydsl.model.QCategoryEntity;
import com.namnx.jpa_querydsl.model.QProductEntity;
import com.namnx.jpa_querydsl.repository.CategoryRepository;
import com.querydsl.core.group.GroupBy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.querydsl.core.group.GroupBy.list;

@Service
public class CategoryService extends BaseService<CategoryEntity, CategoryRepository> {

    //get one to many in just one query
    //return a list of categoryDto
    public List<CategoryDto> getListCategoryDto() {
        QProductEntity p = QProductEntity.productEntity;
        QCategoryEntity c = QCategoryEntity.categoryEntity;

        Map<CategoryEntity, List<ProductEntity>> mapResult = getQuery()
                .from(c)
                .leftJoin(p)
                .on(p.categoryId.eq(c.id))
                .transform(GroupBy.groupBy(c).as(list(p)));

        return mapResult
                .keySet()
                .stream()
                .map(category -> new CategoryDto(category.getCategoryName(), category.getId(), mapResult.get(category)))
                .collect(Collectors.toList());
    }
}
