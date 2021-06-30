package com.namnx.jpa_querydsl.util;

import com.namnx.jpa_querydsl.dto.PageParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

public class PageableUtils {
    public static final String ASC_DIR = "ASC";

    public static Pageable from(String sortDir, String sortField, int page, int size) {
        if (ASC_DIR.equalsIgnoreCase(sortDir)) {
            return PageRequest.of(page, size, Sort.Direction.ASC, sortField);
        }
        return PageRequest.of(page, size, Sort.Direction.DESC, sortField);
    }

    public static <T extends PageParam> Pageable from(T pageParams) {
        return from(pageParams, false);
    }

    public static <T extends PageParam> Pageable from(T pageParams, boolean isSortUnsafe) {
        int page = pageParams.getPage() == null ? 0 : pageParams.getPage();
        int size = pageParams.getSize() == null ? 50 : pageParams.getSize();
        String sortField = pageParams.getSortField();
        String sortDir = pageParams.getSortDir();
        if (sortField == null || sortDir == null) {
            return PageRequest.of(page, size);
        }
        if (isSortUnsafe) {
            return fromWithJpaSortUnsafe(sortDir, sortField, page, size);
        } else {
            return from(sortDir, sortField, page, size);
        }

    }

    public static Pageable fromWithJpaSortUnsafe(String sortDir, String sortField, int page, int size) {
        Sort sort;
        if (ASC_DIR.equalsIgnoreCase(sortDir)) {
            sort = JpaSort.unsafe(Sort.Direction.ASC, "(" + sortField + ")");
        } else {
            sort = JpaSort.unsafe(Sort.Direction.DESC, "(" + sortField + ")");
        }
        return PageRequest.of(page, size, sort);
    }

    public static Pageable fromForSortStringAsNumber(String sortDir, String sortField, int page, int size) {
        Sort sort;
        if (ASC_DIR.equalsIgnoreCase(sortDir)) {
            sort = JpaSort.unsafe(Sort.Direction.ASC, "length(" + sortField + ") asc, " + sortField);
        } else {
            sort = JpaSort.unsafe(Sort.Direction.DESC, "length(" + sortField + ") desc, " + sortField);
        }
        return PageRequest.of(page, size, sort);
    }

}
