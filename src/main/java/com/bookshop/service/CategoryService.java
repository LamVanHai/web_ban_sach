package com.bookshop.service;

import com.bookshop.dto.response.CategoryResponse;
import com.bookshop.entity.Category;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface CategoryService {

    Map<Long, String> findAll();

    List<Category> findAllBy();

    List<CategoryResponse> findAllAndAPaging(Pageable pageable);

    int getTotalItem();

    CategoryResponse findOneById(Long id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    CategoryResponse save(CategoryResponse categoryResponse) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    void delete(Long[] ids);

    List<CategoryResponse> findByKeyWord(String keyWord, Pageable pageable);

    int getItemKeyWord(String keyWord);
}
