package com.namnx.jpa_querydsl.service;

import com.namnx.jpa_querydsl.model.CategoryEntity;
import com.namnx.jpa_querydsl.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<CategoryEntity, CategoryRepository, CategoryEntity> {
}
