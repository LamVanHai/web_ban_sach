package com.bookshop.dto.request;

import com.bookshop.entity.Order_detail;
import com.bookshop.entity.User;

import java.util.List;

public class OrderAndUserRequest {

    protected Long id;

    private String name;

    private String address;

    private String phone_number;

    private Integer status;

    private String payment_info;

    private Long price_total;

    private User user_order;

    private List<Order_detail> order_details;

}
