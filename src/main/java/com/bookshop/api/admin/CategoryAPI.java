package com.bookshop.api.admin;

import com.bookshop.dto.response.CategoryResponse;
import com.bookshop.service.CategoryService;
import com.bookshop.service.Order_detailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
public class CategoryAPI {

    @Autowired
    private Order_detailService order_detailService;

    @Autowired
    private CategoryService categoryService;

    @PutMapping("/api/category")
    public CategoryResponse addOr(@RequestBody CategoryResponse categoryResponse) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return categoryService.save(categoryResponse);
    }

    @PostMapping("/api/category")
    public CategoryResponse editCategory(@RequestBody CategoryResponse categoryResponse) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return categoryService.save(categoryResponse);
    }

    @DeleteMapping("/api/category")
    public ResponseEntity<Void> deleteCategory(@RequestBody Long[] ids){
        categoryService.delete(ids);
        return ResponseEntity.noContent().build();
    }


}
