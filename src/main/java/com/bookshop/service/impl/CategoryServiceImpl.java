package com.bookshop.service.impl;

import com.bookshop.dto.response.CategoryResponse;
import com.bookshop.entity.Category;
import com.bookshop.mapper.CategoryMapper;
import com.bookshop.repository.CategoryRepository;
import com.bookshop.service.CategoryService;
import com.bookshop.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Map<Long, String> findAll() {
        Map<Long,String> stringMap=new ConcurrentHashMap<>();
        List<Category> categories= categoryRepository.findAll();
        for (Category category: categories) {
            stringMap.put(category.getId(),category.getName());
        }
        return stringMap;
    }

    @Override
    public List<Category> findAllBy() {
        List<Category> categories=categoryRepository.findAll();
        return categories;
    }

    @Override
    public List<CategoryResponse> findAllAndAPaging(Pageable pageable) {
        List<Category> categories=categoryRepository.findAll(pageable).getContent();
        List<CategoryResponse> categoryResponses=categories.stream().map(CategoryMapper::mapToResponse).collect(Collectors.toList());
        return categoryResponses;
    }

    @Override
    public int getTotalItem() {
        int totalItem= (int) categoryRepository.count();
        return totalItem;
    }

    @Override
    public CategoryResponse findOneById(Long id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        CategoryResponse categoryResponse=new CategoryResponse();
        Category category=categoryRepository.findOne(id);
        ReflectionUtil.mapper(category,categoryResponse);
        return categoryResponse;
    }

    @Override
    public CategoryResponse save(CategoryResponse categoryResponse) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Category category=new Category();
        if(categoryResponse.getId()!=null){
            Category categoryOld=categoryRepository.findOne(categoryResponse.getId());
            ReflectionUtil.mapper(categoryResponse,categoryOld);
            category=categoryOld;
        }
        else {
                ReflectionUtil.mapper(categoryResponse,category);
        }
        categoryRepository.save(category);
        ReflectionUtil.mapper(category,categoryResponse);
        return categoryResponse;
    }

    @Override
    public void delete(Long[] ids) {
        for(long i:ids){
            categoryRepository.delete(i);
        }
    }

    @Override
    public List<CategoryResponse> findByKeyWord(String keyWord, Pageable pageable) {
        List<Category> categories=categoryRepository.findByKeyWord(keyWord,pageable);
        List<CategoryResponse> categoryResponses=categories.stream().map(CategoryMapper::mapToResponse).collect(Collectors.toList());
        return categoryResponses;
    }

    @Override
    public int getItemKeyWord(String keyWord) {
        List<Category> categories=categoryRepository.countByKeyWord(keyWord);
        return categories.size();
    }
}
