package com.bookshop.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class AddCart {
    private Long id;

    private int quanty=0;

    private String value;

    private String name;


}
