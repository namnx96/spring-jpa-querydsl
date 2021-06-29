package com.namnx.jpa_querydsl.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdTime;
    private Date updatedTime;
    private boolean isDeleted;

    public BaseEntity(BaseEntity baseEntity) {
        this.id = baseEntity.id;
        this.createdTime = baseEntity.createdTime;
        this.updatedTime = baseEntity.updatedTime;
        this.isDeleted = baseEntity.isDeleted;
    }
}
