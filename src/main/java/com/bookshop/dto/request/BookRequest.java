package com.bookshop.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest{

    private Long id;

    private String name;

    private String keyWord;

    private String image;

    private String description;

    private Long price;

    private Long input_price;

    private Long sale_price;

    private Integer amount;

    private Integer input_amount;

    private Long category_id;

    private Long publisher_id;

    private Long writer_id;

//
//    private List<Order_detail> order_details;
//
//    private Set<Writer> writers;
//
//    private List<Comment> comments;
//
//    private Publisher publisher;

}
