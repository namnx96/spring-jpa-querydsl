package com.namnx.jpa_querydsl.repository;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryDslRepository {

    <T> Page<T> findAll(JPQLQuery<T> jpqlQuery, Pageable pageable);

    <T> JPAQuery<T> getQuery();

}
