package com.bookshop.service.impl;

import com.bookshop.entity.Ward;
import com.bookshop.repository.WardRepository;
import com.bookshop.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WardServiceImpl implements WardService {

    @Autowired
    private WardRepository wardRepository;

    @Override
    public Map<String, String> findAllByDistrict_id(String district_id) {
        List<Ward> wards = wardRepository.findAllByDistrict_id(district_id);
        Map<String, String> stringMap = new HashMap<>();
        for (Ward ward : wards
        ) {
            stringMap.put(ward.getId(),ward.getName());
        }
        return stringMap;
    }

    @Override
    public Map<String, String> findAll() {
        List<Ward> wards = wardRepository.findAll();
        Map<String, String> stringMap = new HashMap<>();
        for (Ward ward : wards
        ) {
            stringMap.put(ward.getId(),ward.getName());
        }
        return stringMap;
    }

    @Override
    public Ward findByTransport_fee(String id) {

        return wardRepository.findOneById(id);
    }


}
