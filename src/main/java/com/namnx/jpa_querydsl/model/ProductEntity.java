package com.namnx.jpa_querydsl.model;

import com.querydsl.core.annotations.QueryEntity;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@QueryEntity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity extends BaseEntity {

    private Long categoryId;
    @Column(unique = true)
    private String productCode;
    private String productName;
    private Double price;
    private int quantity;
    private Date expiredDate;

    @Transient
    private String categoryName;

    public ProductEntity(Long categoryId, String productCode,
                         String productName, Double price,
                         int quantity, Date expiredDate) {
        this.categoryId = categoryId;
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.expiredDate = expiredDate;
    }

    @QueryProjection
    public ProductEntity(ProductEntity productEntity, String categoryName) {
        super(productEntity);
        this.categoryId = productEntity.categoryId;
        this.productCode = productEntity.productCode;
        this.productName = productEntity.productName;
        this.price = productEntity.price;
        this.quantity = productEntity.quantity;
        this.expiredDate = productEntity.expiredDate;
        this.categoryName = categoryName;
    }
}
