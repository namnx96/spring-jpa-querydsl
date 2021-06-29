package com.namnx.jpa_querydsl.repository;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryDslRepository<T> {

    Page<T> findAll(JPQLQuery<T> jpqlQuery, Pageable pageable);
}
