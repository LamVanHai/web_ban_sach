package com.bookshop.service.impl;

import com.bookshop.dto.response.WriterResponse;
import com.bookshop.entity.Writer;
import com.bookshop.mapper.WriterMapper;
import com.bookshop.repository.WriterRepository;
import com.bookshop.service.WriterService;
import com.bookshop.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class WriterServiceImpl implements WriterService {

    @Autowired
    private WriterRepository writerRepository;

    @Override
    public List<Writer> findAll() {
        List<Writer> writers=writerRepository.findAll();
        return writers;
    }

    @Override
    public List<WriterResponse> findAllPageable(Pageable pageable) {
        List<Writer> writers=writerRepository.findAll(pageable).getContent();
        List<WriterResponse> writerResponses=writers.stream().map(WriterMapper::mapToResponse).collect(Collectors.toList());
        return writerResponses;
    }

    @Override
    public int getTotalItem() {
        return (int) writerRepository.count();
    }

    @Override
    public List<WriterResponse> findByKeyWord(String keyWord, Pageable pageable) {
        List<Writer> writers=writerRepository.findByKeyWord(keyWord,pageable);
        List<WriterResponse> writerResponses=writers.stream().map(WriterMapper::mapToResponse).collect(Collectors.toList());
        return writerResponses;
    }

    @Override
    public int getItemKeyWord(String keyWord) {
        List<Writer> writers=writerRepository.countByKeyWord(keyWord);
        return writers.size();
    }

    @Override
    public WriterResponse findOneById(Long id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Writer writers=writerRepository.findOne(id);
        WriterResponse writerResponse=new WriterResponse();
        ReflectionUtil.mapper(writers,writerResponse);
        return writerResponse;
    }

    @Override
    public Writer save(WriterResponse writerResponse) {
        Writer writer=new Writer();
        if(writerResponse.getId()!=null){
            writer=writerRepository.findOne(writerResponse.getId());
            try {
                ReflectionUtil.mapper(writerResponse,writer);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
                e.printStackTrace();
            }
        }
        else {
            try {
                ReflectionUtil.mapper(writerResponse,writer);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return writerRepository.save(writer);
    }

    @Override
    public void delete(Long[] ids) {
        writerRepository.deleteAllByIdIn(ids);
    }

    @Override
    public Map<Long, String> findAllByName() {
        Map<Long,String> longStringMap=new ConcurrentHashMap<>();
        List<Writer> writers=writerRepository.findAll();
        for (Writer writer:writers){
            longStringMap.put(writer.getId(),writer.getName());
        }
        return longStringMap;
    }


}
