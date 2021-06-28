package com.bookshop.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WriterResponse extends BaseResponse<WriterResponse> {
    protected Long id;

    private String name;

    private String keyWord;

    private String address;

    private String biography;

    private String phone;
}
