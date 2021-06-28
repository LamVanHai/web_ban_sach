package com.bookshop.controller.admin;

import com.bookshop.dto.response.WriterResponse;
import com.bookshop.service.WriterService;
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

@Controller
public class WriterController {

    @Autowired
    private WriterService writerService;

    @Autowired
    private Message message;


    @RequestMapping(value = "/quan-tri/tac-gia/danh-sach",method = RequestMethod.GET)
    public ModelAndView listChuDe(@RequestParam("page") int page,
                                  HttpServletRequest request,
                                  @RequestParam(value = "search",required = false) String search){
        ModelAndView mav=new ModelAndView("admin/tacgia/list");
        WriterResponse writerResponse=new WriterResponse();
        writerResponse.setLimit(5);
        writerResponse.setPage(page);
        Pageable pageable=new PageRequest(page-1,5);
        if(search.equals("all")){
            writerResponse.setTotalItem(writerService.getTotalItem());
            writerResponse.setTotalPage((int) Math.ceil((double)writerResponse.getTotalItem()/writerResponse.getLimit()));
            writerResponse.setData(writerService.findAllPageable(pageable));
        }else {
            writerResponse.setTotalItem(writerService.getItemKeyWord(search));
            writerResponse.setTotalPage((int) Math.ceil((double)writerResponse.getTotalItem()/writerResponse.getLimit()));
            writerResponse.setData(writerService.findByKeyWord(search,pageable));
        }
        message.listMessage(request,mav);
        mav.addObject("model", writerResponse);
        mav.addObject("search", search);
        return mav;
    }
    @RequestMapping(value = "/quan-tri/tac-gia/chinh-sua", method = RequestMethod.GET)
    public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ModelAndView mav = new ModelAndView("admin/tacgia/edit");
        WriterResponse writerResponse=new WriterResponse();
        if (id != null) {
            writerResponse = writerService.findOneById(id);
        }
        message.listMessage(request,mav);
        mav.addObject("model", writerResponse);
        return mav;

    }
}
