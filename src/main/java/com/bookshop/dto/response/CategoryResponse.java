package com.bookshop.dto.response;

import com.bookshop.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponse extends BaseResponse<CategoryResponse> {

    protected Long id;

    private String name;

    private String keyWord;

    private String description;

    private List<Book> books;
}
