package com.bookshop.repository;

import com.bookshop.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District,String> {

    @Query("select e from District e where e.province.id=:province_id")
    List<District> findAllByProvince_id(@Param("province_id") String province_id);
}
