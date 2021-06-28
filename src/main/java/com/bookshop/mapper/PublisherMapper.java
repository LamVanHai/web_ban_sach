package com.bookshop.mapper;

import com.bookshop.dto.response.PublisherResponse;
import com.bookshop.entity.Publisher;
import com.bookshop.util.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;

public class PublisherMapper {
    public static PublisherResponse mapToResponse(Publisher publisher) {
        PublisherResponse publisherResponse=new PublisherResponse();
        try {
            ReflectionUtil.mapper(publisher, publisherResponse);
            return publisherResponse;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

}
