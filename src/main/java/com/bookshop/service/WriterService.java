package com.bookshop.service;

import com.bookshop.dto.response.WriterResponse;
import com.bookshop.entity.Writer;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface WriterService {
    List<Writer> findAll();

    List<WriterResponse> findAllPageable(Pageable pageable);

    int getTotalItem();

    List<WriterResponse> findByKeyWord(String keyWord, Pageable pageable);

    int getItemKeyWord(String keyWord);

    WriterResponse findOneById(Long id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    Writer save(WriterResponse writerResponse);

    void delete(Long[] ids);

    Map<Long,String> findAllByName();

}
