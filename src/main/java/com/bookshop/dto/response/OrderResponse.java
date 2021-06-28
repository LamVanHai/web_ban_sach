package com.bookshop.dto.response;

import com.bookshop.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse extends BaseResponse<OrderResponse> {

    private Long id;

    private String note;

    private Integer status;

    private String payment_info;

    private Long price_total;

    private String info;

    private User user_order = new User();

    private int size=0;
}
