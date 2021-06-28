package com.bookshop.controller.admin;

import com.bookshop.dto.response.PageRespone;
import com.bookshop.dto.response.UserResponse;
import com.bookshop.service.UserService;
import com.bookshop.service.User_groupService;
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
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Message message;

    @Autowired
    private User_groupService user_groupService;

    @RequestMapping(value = "/quan-tri/nguoi-dung/danh-sach",method = RequestMethod.GET)
    public ModelAndView listUser(@RequestParam("page") int page,
                                 @RequestParam(value = "search",required = false) String search){
        PageRespone pageRespone=new PageRespone();
        pageRespone.setLimit(5);
        pageRespone.setPage(page);
        Pageable pageable=new PageRequest(page-1,5);
        List<UserResponse> userResponses;
        if(search.equals("all")){
            userResponses=userService.findAll(pageable);
            pageRespone.setTotalItem(userService.getTotalItem());
            pageRespone.setTotalPage((int) Math.ceil((double) pageRespone.getTotalItem() / pageRespone.getLimit()));
        }else {
            userResponses=userService.findByKeyWord(search,pageable);
            pageRespone.setTotalItem(userService.getItemKeyWord(search));
            pageRespone.setTotalPage((int) (Math.ceil((double)pageRespone.getTotalItem())/pageRespone.getLimit()));
        }
        ModelAndView modelAndView=new ModelAndView("admin/user/list");
        modelAndView.addObject("model",pageRespone);
        modelAndView.addObject("search",search);
        modelAndView.addObject("model1",userResponses);
        return modelAndView;
    }

    @RequestMapping(value = "/quan-tri/nguoi-dung/chinh-sua",method = RequestMethod.GET)
    public ModelAndView editUser(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("admin/user/edit");
        modelAndView.addObject("user_group",user_groupService.findAll());
        UserResponse userResponse=new UserResponse();
        if(id!=null){
            userResponse=userService.findById(id);
        }
        message.listMessage(request,modelAndView);
        modelAndView.addObject("model",userResponse);
        return modelAndView;
    }
}
