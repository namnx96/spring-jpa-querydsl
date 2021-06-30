package com.namnx.jpa_querydsl.web;

import com.namnx.jpa_querydsl.dto.CategoryDto;
import com.namnx.jpa_querydsl.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDto>> list() {
        return ResponseEntity.ok(categoryService.getListCategoryDto());
    }
}
