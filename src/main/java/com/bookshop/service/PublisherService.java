package com.bookshop.service;

import com.bookshop.dto.response.PublisherResponse;
import com.bookshop.entity.Publisher;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PublisherService {

    Map<Long,String> findAll();

    List<Publisher> findAllList();

    List<PublisherResponse> findAll(Pageable pageable);

    int getTotalItem();

    List<PublisherResponse> findAllKeyWord(String keyWord, Pageable pageable);

    int getTotalItem(String keyWord);

    PublisherResponse findOneById(Long id);

    Publisher save(PublisherResponse publisherResponse);

    void delete(Long[] ids);

}
