package com.bookshop.api.admin;

import com.bookshop.dto.request.OrderRequest;
import com.bookshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@RestController
public class OrderAPI {

    @Autowired
    private OrderService orderService;

    @PostMapping("/api/Order")
    public ResponseEntity<Void> xacNhan(@RequestBody OrderRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(request.getStatus()==0){
            request.setStatus(1);
        }else {
            request.setStatus(2);
        }
        orderService.save(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/Order")
    public OrderRequest detele(@RequestBody OrderRequest request){
        orderService.delete(request.getId(),request.getStatus());
        return request;
    }
}
