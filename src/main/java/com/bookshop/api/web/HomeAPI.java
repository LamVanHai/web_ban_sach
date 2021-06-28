package com.bookshop.api.web;

import com.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeAPI {

    @Autowired
    private BookService bookService;

}
