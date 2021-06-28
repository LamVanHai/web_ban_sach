package com.bookshop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThongKeResponse extends BaseResponse<ThongKeResponse> {

    protected Long id;

    private String name;

    private Long price;

    private Long input_price;

    private Long sale_price;

    private Integer amount;

    private String init;

    private Integer input_amount;

    private Integer output_amount;

    private Long total_input_price;

    private Long total_output_price;

    private Long revenue;

}
