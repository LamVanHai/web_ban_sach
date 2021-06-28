package com.bookshop.service.impl;

import com.bookshop.dto.response.PublisherResponse;
import com.bookshop.entity.Publisher;
import com.bookshop.mapper.PublisherMapper;
import com.bookshop.repository.PublisherRepository;
import com.bookshop.service.PublisherService;
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
public class PublisherServiceIplm implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public Map<Long, String> findAll() {
        Map<Long,String> longStringMap=new ConcurrentHashMap<>();
        List<Publisher> publishers=publisherRepository.findAll();
        for (Publisher publisher:publishers){
            longStringMap.put(publisher.getId(),publisher.getName());
        }
        return longStringMap;
    }

    @Override
    public List<Publisher> findAllList() {
        List<Publisher> publishers=publisherRepository.findAll();
        return publishers;
    }

    @Override
    public List<PublisherResponse> findAll(Pageable pageable) {
        List<Publisher> publishers=publisherRepository.findAll(pageable).getContent();
        List<PublisherResponse> publisherResponses=publishers.stream().map(PublisherMapper::mapToResponse).collect(Collectors.toList());
        return publisherResponses;
    }

    @Override
    public int getTotalItem() {
        List<Publisher> publishers=publisherRepository.findAll();
        return publishers.size();
    }

    @Override
    public List<PublisherResponse> findAllKeyWord(String keyWord, Pageable pageable) {
        List<Publisher> publishers=publisherRepository.findByKeyWord(keyWord,pageable);
        List<PublisherResponse> publisherResponses=publishers.stream().map(PublisherMapper::mapToResponse).collect(Collectors.toList());
        return publisherResponses;
    }

    @Override
    public int getTotalItem(String keyWord) {
        List<Publisher> publishers=publisherRepository.countByKeyWord(keyWord);
        return publishers.size();
    }

    @Override
    public PublisherResponse findOneById(Long id) {
        Publisher publisher=publisherRepository.findOne(id);
        PublisherResponse publisherResponse=new PublisherResponse();
        try {
            ReflectionUtil.mapper(publisher,publisherResponse);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
        return publisherResponse;
    }

    @Override
    public Publisher save(PublisherResponse publisherResponse) {
        Publisher publisher=null;
        if(publisherResponse.getId()!=null){
            try {
                publisher=publisherRepository.findOne(publisherResponse.getId());
                ReflectionUtil.mapper(publisherResponse,publisher);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
                e.printStackTrace();
            }
        }else {
            try {
                ReflectionUtil.mapper(publisherResponse,publisher);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
                e.printStackTrace();
            }
        }
        return publisherRepository.save(publisher);
    }

    @Override
    public void delete(Long[] ids) {
        publisherRepository.deleteAllByIdIn(ids);
    }
}
