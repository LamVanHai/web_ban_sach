package com.bookshop.service;

import com.bookshop.entity.Ward;

import java.util.Map;

public interface WardService {

    Map<String, String> findAllByDistrict_id(String district_id);

    Map<String, String> findAll();

    Ward findByTransport_fee(String id);
}
