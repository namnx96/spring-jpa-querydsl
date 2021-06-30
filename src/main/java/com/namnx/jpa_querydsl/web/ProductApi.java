package com.namnx.jpa_querydsl.web;

import com.namnx.jpa_querydsl.dto.FilterProductDto;
import com.namnx.jpa_querydsl.model.ProductEntity;
import com.namnx.jpa_querydsl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductApi {

    @Autowired
    private ProductService service;

    @GetMapping("/withNoJoin")
    public ResponseEntity<Page<ProductEntity>> withNoJoin(FilterProductDto filterProductDto) {
        return ResponseEntity.ok(service.findAllWithNoJoin(filterProductDto));
    }

    @GetMapping("/withJoin")
    public ResponseEntity<Page<ProductEntity>> withJoin(FilterProductDto filterProductDto) {
        return ResponseEntity.ok(service.findAllWithJoin(filterProductDto));
    }
}
