package com.instant.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * @author sroshchupkin
 */

@Component
public class PaginationBean {
    @Autowired
    private EnvironmentBean environmentBean;

    public static Pageable createPageable(int pageNum, int pageSize) {
        return new PageRequest(pageNum, pageSize, createSort());
    }

    public static Sort createSort() {
        return new Sort(Sort.Direction.DESC, "name");
    }

    public Pageable defaultPageable(Integer pageNum) {
        return createPageable(pageNum, environmentBean.getInt("pagination.page.size"));
    }
}
