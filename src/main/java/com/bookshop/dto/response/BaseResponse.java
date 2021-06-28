package com.bookshop.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BaseResponse<T> {

    protected Date createdDate;

    protected Date modifiedDate;

    protected String createdBy;

    protected String modifiedBy;

    protected List<T> data;

    protected Long id;

    protected int page;

    protected int limit;

    protected int totalPage;

    protected int totalItem;
}
