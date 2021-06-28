package com.bookshop.dto.request;

import com.bookshop.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
@Setter
public class OrderRequest {

    private Long id;

    private String payment_info;

    private String note;

    private Integer status;

    private String info;

    private Long price_total;

    private User user_order;

    private Date createdDate;

    private Date modifiedDate;

    private String createdBy;

    private String modifiedBy;

    private int size=0;

}
