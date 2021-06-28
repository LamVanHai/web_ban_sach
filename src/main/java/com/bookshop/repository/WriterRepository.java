package com.bookshop.repository;

import com.bookshop.entity.Writer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WriterRepository extends JpaRepository<Writer,Long> {

    @Query("select e from Writer e where concat(e.address,e.address) like %?1%")
    List<Writer> findByKeyWord(String keyWord, Pageable pageable);

    @Query("select e from Writer e where concat(e.address,e.address) like %?1%")
    List<Writer> countByKeyWord(String keyWord);

    void deleteAllByIdIn(Long[] ids);

}
