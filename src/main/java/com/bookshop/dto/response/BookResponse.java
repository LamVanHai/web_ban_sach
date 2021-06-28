package com.bookshop.dto.response;

import com.bookshop.entity.Category;
import com.bookshop.entity.Comment;
import com.bookshop.entity.Publisher;
import com.bookshop.entity.Writer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class BookResponse extends BaseResponse<BookResponse> {

    protected Long id;

    private String name;

    private String keyWord;

    private String image;

    private String description;

    private Long price;

    private Long input_price;

    private Long price1;

    private Long sale_price;

    private String sale_price1;

    private Integer amount;

    private Integer input_amount;

    private Category category;

    private Publisher publisher_book;

    private Set<Writer> writers;

    private List<Comment> comments;



}
