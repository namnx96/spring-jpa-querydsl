package com.namnx.jpa_querydsl;

import com.namnx.jpa_querydsl.model.CategoryEntity;
import com.namnx.jpa_querydsl.model.ProductEntity;
import com.namnx.jpa_querydsl.service.CategoryService;
import com.namnx.jpa_querydsl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringJpaQuerydslApplication {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaQuerydslApplication.class, args);
    }


    @PostConstruct
    void mockData() {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        CategoryEntity category1 = new CategoryEntity();
        category1.setCategoryName("Category 1");

        CategoryEntity category2 = new CategoryEntity();
        category2.setCategoryName("Category 2");
        categoryEntities.add(category1);
        categoryEntities.add(category2);
        categoryService.saveAll(categoryEntities);

        List<ProductEntity> productEntities = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Long categoryId = i > 5 ? 2L : 1L;
            String productCode = (i >= 10 ? "P0" : "P00") + i;
            String expiredDate = "2021/12/" + i;
            ProductEntity product = new ProductEntity(categoryId, productCode, "Product " + i, 10.0 + i, 20 + i, new Date(expiredDate));
            productEntities.add(product);
        }
        productService.saveAll(productEntities);
    }

}
