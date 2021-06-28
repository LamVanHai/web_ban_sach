package com.bookshop.api.web;

import com.bookshop.dto.request.AddCart;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchAPI {

    @PostMapping("/tim-kiem")
    private AddCart searchValue1(@RequestBody AddCart addCart){
        return addCart;
    }

}
