package com.bookshop.mapper;

import com.bookshop.dto.response.OrderResponse;
import com.bookshop.entity.Order;
import com.bookshop.util.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;

public class OrderMapper {
    public static OrderResponse mapToResponse(Order order){
        OrderResponse orderResponse=new OrderResponse();
        try {
            ReflectionUtil.mapper(order,orderResponse);
            orderResponse.setCreatedDate(order.getCreatedDate());
            orderResponse.setModifiedDate(order.getModifiedDate());
            if(order.getStatus()==0){
                orderResponse.setInfo("Đang chờ xử lý");
            }
            else if(order.getStatus()==1){
                orderResponse.setInfo("Đang giao hàng");
            }
            else if(order.getStatus()==2){
                orderResponse.setInfo("Đã giao");
            }
            else {
                orderResponse.setInfo("Đã hủy");
            }
            return orderResponse;
        } catch (NoSuchMethodException |IllegalAccessException |InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
