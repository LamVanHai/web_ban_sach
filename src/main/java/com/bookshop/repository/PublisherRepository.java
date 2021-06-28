package com.bookshop.repository;

import com.bookshop.entity.Publisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher,Long> {


    @Query("select e from Publisher  e where concat(e.name,e.keyWord,e.address,e.phone_number) like %?1%")
    List<Publisher> findByKeyWord(String keyWord, Pageable pageable);

    @Query("select e from Publisher  e where concat(e.name,e.keyWord,e.address,e.phone_number) like %?1%")
    List<Publisher> countByKeyWord(String keyWord);

    void deleteAllByIdIn(Long[] ids);
}
