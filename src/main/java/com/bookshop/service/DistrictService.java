package com.bookshop.service;

import java.util.Map;

public interface DistrictService {

    Map<String, String> findById(String province_id);

    Map<String, String> findAll();
}
