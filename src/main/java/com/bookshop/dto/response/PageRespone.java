package com.bookshop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRespone {
    protected Long id;
    private int page;
    private int limit;
    private int totalPage;
    private int totalItem;
}
