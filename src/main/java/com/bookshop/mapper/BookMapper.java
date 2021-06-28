package com.bookshop.mapper;

import com.bookshop.dto.response.BookResponse;
import com.bookshop.dto.response.ThongKeResponse;
import com.bookshop.entity.Book;
import com.bookshop.entity.Order_detail;
import com.bookshop.service.Order_detailService;
import com.bookshop.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component
public class BookMapper {

    @Autowired
    private Order_detailService order_detailService;

    public static BookResponse mapToResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        try {
            ReflectionUtil.mapper(book, bookResponse);
            if(book.getSale_price()!=null){
                bookResponse.setPrice1(book.getPrice()/100*(100-book.getSale_price()));
            }else {
                bookResponse.setPrice1(book.getPrice());
            }
            return bookResponse;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();

            return null;
        }
    }

    public ThongKeResponse mapToThongKeResponse(Book book) {
        ThongKeResponse thongKeResponse=new ThongKeResponse();
        try {
            ReflectionUtil.mapper(book, thongKeResponse);
            List<Order_detail> order_details=order_detailService.findByBookId(book.getId());
            int quanty=0;
            long total_price=0;
            if(order_details!=null){
                for (Order_detail detail:order_details
                ) {
                    if(detail.getOrder3().getStatus()!=3){
                        quanty+=detail.getAmount();
                        total_price+=detail.getUnit_price();
                    }
                }
            }
            thongKeResponse.setOutput_amount(quanty);
            thongKeResponse.setInit("Quyển");
            thongKeResponse.setTotal_input_price(book.getInput_price()*book.getInput_amount());
            thongKeResponse.setTotal_output_price(total_price);
            thongKeResponse.setRevenue(thongKeResponse.getTotal_output_price()-thongKeResponse.getTotal_input_price());
            return thongKeResponse;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}

