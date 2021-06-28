package com.bookshop.repository;

import com.bookshop.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, String> {

    @Query("select e from Ward e where e.district.id=:district_id")
    List<Ward> findAllByDistrict_id(@Param("district_id") String district_id);

    Ward findOneById(String id);
}
