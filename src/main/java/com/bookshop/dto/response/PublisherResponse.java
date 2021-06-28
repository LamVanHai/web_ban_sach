package com.bookshop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublisherResponse extends BaseResponse<PublisherResponse> {

    protected Long id;

    private String name;

    private String address;

    private String keyWord;

    private String phone_number;
}
