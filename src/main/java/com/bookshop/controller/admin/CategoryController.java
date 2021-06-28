package com.bookshop.controller.admin;

import com.bookshop.dto.response.CategoryResponse;
import com.bookshop.service.CategoryService;
import com.bookshop.service.other.Message;
import com.bookshop.util.MessageUtil;
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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private Message message;

    @RequestMapping(value = "/quan-tri/chu-de/danh-sach",method = RequestMethod.GET)
    public ModelAndView listChuDe(@RequestParam("page") int page,
                                  HttpServletRequest request,
                                  @RequestParam(value = "search",required = false) String search){
        ModelAndView mav=new ModelAndView("admin/chude/list");
        CategoryResponse categoryResponse=new CategoryResponse();
        categoryResponse.setLimit(5);
        categoryResponse.setPage(page);
        Pageable pageable=new PageRequest(page-1,5);
        if(search.equals("all")){
            categoryResponse.setTotalItem(categoryService.getTotalItem());
            categoryResponse.setTotalPage((int) Math.ceil((double)categoryResponse.getTotalItem()/categoryResponse.getLimit()));
            categoryResponse.setData(categoryService.findAllAndAPaging(pageable));
        }else {
            categoryResponse.setTotalItem(categoryService.getItemKeyWord(search));
            categoryResponse.setTotalPage((int) Math.ceil((double)categoryResponse.getTotalItem()/categoryResponse.getLimit()));
            categoryResponse.setData(categoryService.findByKeyWord(search,pageable));
        }
        message.listMessage(request,mav);
        mav.addObject("model", categoryResponse);
        mav.addObject("search", search);
        return mav;
    }
    @RequestMapping(value = "/quan-tri/chu-de/chinh-sua", method = RequestMethod.GET)
    public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ModelAndView mav = new ModelAndView("admin/chude/edit");
        CategoryResponse categoryResponse=new CategoryResponse();
        if (id != null) {
            categoryResponse = categoryService.findOneById(id);
        }
        message.listMessage(request,mav);
        mav.addObject("model", categoryResponse);
        return mav;

    }

}
