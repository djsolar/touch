package com.twinflag.touch.utils;

import org.springframework.data.domain.PageRequest;

public class PageRequestBuilder {

    public static PageRequest buildPageRequest(int page, int pageSize) {
        return new PageRequest(page, pageSize);
    }
}
