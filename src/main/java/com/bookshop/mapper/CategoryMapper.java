package com.bookshop.mapper;

import com.bookshop.dto.response.CategoryResponse;
import com.bookshop.entity.Category;
import com.bookshop.util.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;

public class CategoryMapper {

    public static CategoryResponse mapToResponse(Category category){
        CategoryResponse categoryResponse=new CategoryResponse();
        try {
            ReflectionUtil.mapper(category,categoryResponse);
            categoryResponse.setCreatedBy(category.getCreatedBy());
            categoryResponse.setCreatedDate(category.getCreatedDate());
            categoryResponse.setModifiedBy(category.getModifiedBy());
            categoryResponse.setModifiedDate(category.getModifiedDate());
            return categoryResponse;
        } catch (NoSuchMethodException |IllegalAccessException |InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
