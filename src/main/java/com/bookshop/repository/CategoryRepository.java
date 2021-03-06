package com.bookshop.repository;

import com.bookshop.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("select e from Category  e where concat(e.name,e.keyWord) like %?1%")
    List<Category> findByKeyWord(String keyWord, Pageable pageable);

    @Query("select e from Category  e where concat(e.name,e.keyWord) like %?1%")
    List<Category> countByKeyWord(String keyWord);

}
