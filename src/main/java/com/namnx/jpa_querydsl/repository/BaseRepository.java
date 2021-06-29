package com.namnx.jpa_querydsl.repository;

import com.namnx.jpa_querydsl.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity>
        extends JpaRepository<T, Long>, QuerydslPredicateExecutor<T> {

    Optional<T> findByIdAndIsDeletedFalse(Long id);
}
