package com.bookshop.controller.admin;

import com.bookshop.dto.response.PublisherResponse;
import com.bookshop.dto.response.WriterResponse;
import com.bookshop.service.PublisherService;
import com.bookshop.service.other.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Controller
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @Autowired
    private Message message;


    @RequestMapping(value = "/quan-tri/nha-xuat-ban/danh-sach",method = RequestMethod.GET)
    public ModelAndView listChuDe(@RequestParam("page") int page,
                                  HttpServletRequest request,
                                  @RequestParam(value = "search",required = false) String search){
        ModelAndView mav=new ModelAndView("admin/nhaxuatban/list");
        PublisherResponse publisherResponse=new PublisherResponse();
        publisherResponse.setLimit(5);
        publisherResponse.setPage(page);
        Pageable pageable=new PageRequest(page-1,5);
        List<WriterResponse> writerResponses;
        if(search.equals("all")){
            publisherResponse.setTotalItem(publisherService.getTotalItem());
            publisherResponse.setTotalPage((int) Math.ceil((double)publisherResponse.getTotalItem()/publisherResponse.getLimit()));
            publisherResponse.setData(publisherService.findAll(pageable));
        }else {
            publisherResponse.setTotalItem(publisherService.getTotalItem(search));
            publisherResponse.setTotalPage((int) Math.ceil((double)publisherResponse.getTotalItem()/publisherResponse.getLimit()));
            publisherResponse.setData(publisherService.findAllKeyWord(search,pageable));
        }
        message.listMessage(request,mav);
        mav.addObject("model", publisherResponse);
        mav.addObject("search", search);
        return mav;
    }
    @RequestMapping(value = "/quan-tri/nha-xuat-ban/chinh-sua", method = RequestMethod.GET)
    public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ModelAndView mav = new ModelAndView("admin/nhaxuatban/edit");
        PublisherResponse publisherResponse=new PublisherResponse();
        if (id != null) {
            publisherResponse = publisherService.findOneById(id);
        }
        message.listMessage(request,mav);
        mav.addObject("model", publisherResponse);
        return mav;

    }
}
