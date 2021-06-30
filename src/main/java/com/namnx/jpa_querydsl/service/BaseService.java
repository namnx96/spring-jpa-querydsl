package com.namnx.jpa_querydsl.service;

import com.namnx.jpa_querydsl.model.BaseEntity;
import com.namnx.jpa_querydsl.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public abstract class BaseService<E extends BaseEntity, R extends BaseRepository<E>> extends QueryDslRepositoryImpl {

    @Autowired
    protected R repository;

    public Optional<E> findById(Long id) {
        return repository.findByIdAndIsDeletedFalse(id);
    }

    public E save(E e) {
        preSave(e);
        return repository.save(e);
    }

    public boolean delete(Long id) {
        Optional<E> optional = findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        E e = optional.get();
        e.setDeleted(true);
        save(e);
        return true;
    }


    public void preSave(E e) {
        if (e.getId() == null) {
            e.setCreatedTime(new Date());
        }
        e.setUpdatedTime(new Date());
    }

    public void saveAll(Collection<E> collectionOfE) {
        collectionOfE.forEach(this::preSave);
        repository.saveAll(collectionOfE);
    }
}
