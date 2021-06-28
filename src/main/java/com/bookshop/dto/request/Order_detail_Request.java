package com.bookshop.dto.request;

import com.bookshop.entity.Book;
import com.bookshop.entity.Order;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Order_detail_Request {

    private Long id;

    private Integer amount;

    private Long unit_price;

    private Book book3;

    private Order order3;

}
