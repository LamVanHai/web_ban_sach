package com.bookshop.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseRequest {

    protected Long id;

    protected Date createdDate;

    protected Date modifiedDate;

    protected String createdBy;

    protected String modifiedBy;
}
