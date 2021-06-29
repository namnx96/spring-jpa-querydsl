package com.namnx.jpa_querydsl.service;

import com.namnx.jpa_querydsl.dto.FilterProductDto;
import com.namnx.jpa_querydsl.model.ProductEntity;
import com.namnx.jpa_querydsl.model.QCategoryEntity;
import com.namnx.jpa_querydsl.model.QProductEntity;
import com.namnx.jpa_querydsl.repository.product.ProductRepository;
import com.namnx.jpa_querydsl.util.PageableUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductService extends BaseService<ProductEntity, ProductRepository, ProductEntity> {

    public Page<ProductEntity> findAllWithNoJoin(FilterProductDto filter) {
        Pageable pageable = PageableUtils.from(filter);
        return repository.findAll(builderQuery(filter, false), pageable);
    }

    //join 2 unrelated table
    public Page<ProductEntity> findAllWithJoin(FilterProductDto filter) {
        Pageable pageable = PageableUtils.from(filter);

        JPQLQuery<ProductEntity> q = new JPAQuery<ProductEntity>()
                .select(QProductEntity.create(QProductEntity.productEntity, QCategoryEntity.categoryEntity.categoryName))
                .from(QProductEntity.productEntity)
                .leftJoin(QCategoryEntity.categoryEntity)
                .on(QProductEntity.productEntity.categoryId.eq(QCategoryEntity.categoryEntity.id))
                .where(builderQuery(filter, true).getValue());
        return findAll(q, pageable);
    }

    public BooleanBuilder builderQuery(FilterProductDto filter, boolean isJoin) {
        QProductEntity qProduct = QProductEntity.productEntity;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        String nameOrCodeKeyword = filter.getNameOrCodeKeyword();

        if (!StringUtils.isEmpty(nameOrCodeKeyword)) {
            booleanBuilder.and((qProduct.productCode.contains(nameOrCodeKeyword)).or(qProduct.productName.contains(nameOrCodeKeyword)));
        }
        if (filter.getQuantity() != null) {
            booleanBuilder.and(qProduct.quantity.goe(filter.getQuantity()));
        }

        if (filter.getQuantity() != null) {
            booleanBuilder.and(qProduct.quantity.goe(filter.getQuantity()));
        }

        if (filter.getPrice() != null) {
            booleanBuilder.and(qProduct.price.goe(filter.getPrice()));
        }

        if (isJoin) {
            if (filter.getCategoryId() != null) {
                QCategoryEntity qCategory = QCategoryEntity.categoryEntity;
                booleanBuilder.and(qCategory.id.eq(filter.getCategoryId()));
            }
        }
        return booleanBuilder;
    }

}
