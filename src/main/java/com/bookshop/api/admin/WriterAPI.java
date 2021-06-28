package com.bookshop.api.admin;

import com.bookshop.dto.response.WriterResponse;
import com.bookshop.entity.Writer;
import com.bookshop.service.Order_detailService;
import com.bookshop.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WriterAPI {
    @Autowired
    private Order_detailService order_detailService;

    @Autowired
    private WriterService writerService;

    @PutMapping("/api/writer")
    public Writer addWriter(@RequestBody WriterResponse writerResponse)  {
        return writerService.save(writerResponse);
    }

    @PostMapping("/api/writer")
    public Writer editWriter(@RequestBody WriterResponse writerResponse){
        return writerService.save(writerResponse);
    }

    @DeleteMapping("/api/writer")
    public ResponseEntity<Void> deleteWriter(@RequestBody Long[] ids){
        writerService.delete(ids);
        return ResponseEntity.noContent().build();
    }
}
