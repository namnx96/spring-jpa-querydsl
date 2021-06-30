package com.namnx.jpa_querydsl.service;

import com.namnx.jpa_querydsl.repository.QueryDslRepository;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.AbstractJPAQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class QueryDslRepositoryImpl implements QueryDslRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public <T> Page<T> findAll(JPQLQuery<T> jpqlQuery, Pageable pageable) {
        Querydsl querydsl = new Querydsl(entityManager, new PathBuilderFactory()
                .create(jpqlQuery.getType()));

        JPQLQuery<T> countQuery = ((AbstractJPAQuery) jpqlQuery).clone(entityManager);
        AbstractJPAQuery query = (AbstractJPAQuery) querydsl.applyPagination(pageable, jpqlQuery);
        return PageableExecutionUtils.getPage(
                query.clone(entityManager).fetch(),
                pageable,
                countQuery::fetchCount);
    }

    @Override
    public <T> JPAQuery<T> getQuery() {
        return new JPAQuery<>(entityManager);
    }
}
