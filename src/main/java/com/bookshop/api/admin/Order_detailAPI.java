package com.bookshop.api.admin;

import com.bookshop.dto.request.Order_detail_Request;
import com.bookshop.entity.Order_detail;
import com.bookshop.service.Order_detailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
public class Order_detailAPI {

    @Autowired
    private Order_detailService order_detailService;

    @PutMapping("/api/order_detail")
    public Order_detail addOrder_detail(@RequestBody Order_detail_Request order_detail_request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return order_detailService.save(order_detail_request);
    }

    @PostMapping("/api/order_detail")
    public Order_detail editOrder_detail(@RequestBody Order_detail_Request order_detail_request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return order_detailService.save(order_detail_request);
    }

    @DeleteMapping("/api/order_detail")
    public ResponseEntity<Void> deleteOrder_detail(@RequestBody Long[] ids) {
        order_detailService.delete(ids);
        return ResponseEntity.noContent().build();
    }
}
