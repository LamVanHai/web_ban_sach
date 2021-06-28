package com.bookshop.mapper;

import com.bookshop.dto.response.WriterResponse;
import com.bookshop.entity.Writer;
import com.bookshop.util.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;

public class WriterMapper {
    public static WriterResponse mapToResponse(Writer writer) {
       WriterResponse writerResponse=new WriterResponse();
        try {
            ReflectionUtil.mapper(writer, writerResponse);
            return writerResponse;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Writer mapToEntity(WriterResponse writerResponse){
        Writer writer=new Writer();
        try {
            ReflectionUtil.mapper(writerResponse,writer);
            return writer;
        } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
            return null;
        }
    }
}
