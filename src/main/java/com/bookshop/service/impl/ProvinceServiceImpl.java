package com.bookshop.service.impl;

import com.bookshop.entity.Province;
import com.bookshop.repository.ProvinceRepository;
import com.bookshop.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public Map<String, String> findAll() {
        Map<String, String> stringMap = new HashMap<>();
        List<Province> provinces = provinceRepository.findAll();
        for (Province province : provinces
        ) {
            stringMap.put(province.getId(),province.getName());
        }
        return stringMap;
    }
}
