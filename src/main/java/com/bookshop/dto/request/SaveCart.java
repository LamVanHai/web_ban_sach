package com.bookshop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCart {

    private Long transport_fee;

    private Long total;
}
