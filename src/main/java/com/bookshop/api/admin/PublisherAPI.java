package com.bookshop.api.admin;

import com.bookshop.dto.response.PublisherResponse;
import com.bookshop.entity.Publisher;
import com.bookshop.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublisherAPI {

    @Autowired
    private PublisherService publisherService;

    @PutMapping("/api/publisher")
    public Publisher addPublisher(@RequestBody PublisherResponse publisherResponse)  {
        return publisherService.save(publisherResponse);
    }

    @PostMapping("/api/publisher")
    public Publisher editPublisher(@RequestBody PublisherResponse publisherResponse){
        return publisherService.save(publisherResponse);
    }

    @DeleteMapping("/api/publisher")
    public ResponseEntity<Void> deleteWriter(@RequestBody Long[] ids){
        publisherService.delete(ids);
        return ResponseEntity.noContent().build();
    }
}
