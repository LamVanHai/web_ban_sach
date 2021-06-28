package com.bookshop.api.admin;


import com.bookshop.dto.request.BookRequest;
import com.bookshop.dto.request.UploadImage;
import com.bookshop.service.BookService;
import com.bookshop.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

@RestController
public class BookAPI {

    @Autowired
    private BookService bookService;

    @Autowired
    private UploadFileUtil uploadFileUtil;

    @PostMapping("/api/book")
    public BookRequest createNew(@RequestBody BookRequest bookRequest) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return bookService.save(bookRequest);
    }

    @PostMapping("/api/bookimage")
    public ResponseEntity<Void> updatefile(@RequestBody UploadImage uploadImage, HttpServletRequest request){//upload file ảnh sản phẩm
        byte[] decodeBase64= Base64.getDecoder().decode(uploadImage.getBase64().getBytes());
        uploadFileUtil.saveFile(decodeBase64,uploadImage.getName(),request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/book")
    public ResponseEntity<BookRequest> updateNew(@RequestBody BookRequest bookRequest) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return new ResponseEntity<>(bookService.save(bookRequest), HttpStatus.OK);
    }

    @DeleteMapping("/api/book")
    public void deleteNew(@RequestBody long[] ids) {
        bookService.delete(ids);
    }
}
