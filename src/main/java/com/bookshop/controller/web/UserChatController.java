package com.bookshop.controller.web;

import com.bookshop.dto.MessageModel;
import com.bookshop.dto.UserStorage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@Controller
public class UserChatController {

    @RequestMapping(value = "/registration/{userName}",method = RequestMethod.GET)
    public ResponseEntity<Void> register(@PathVariable String userName) {
        System.out.println("handling register user request: " + userName);
        try {
            UserStorage.getInstance().setUser(userName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/fetchAllUsers",method = RequestMethod.GET)
    public void fetchAll(HttpServletResponse response) throws IOException {
        Set<String> list= UserStorage.getInstance().getUsers();
        PrintWriter printWriter = response.getWriter();
        for (String list1: list
             ) {
            if(list1.equals("admin")){
                continue;
            }
            printWriter.println(
            "<a href=\"#\">\n" +
                    "    <li class=\"clearfix\">\n" +
                    "        <img style=\"border-radius:50%;\" src=\"/template/web/public/img/avatarUser.jpg\" width=\"55px\"  height=\"55px\" alt=\"avatar\"/>\n" +
                    "        <div class=\"about\">\n" +
                    "            <div id=\"userNameAppender_"+list1+"\" class=\"name\">"+list1+"</div>\n" +
                    "            <div class=\"status\">\n" +
                    "                <i class=\"fa fa-circle offline\"></i>\n" +
                    "            <span style=\"color: red\" id=\"newMessage1_"+list1+"\"></span>" +
                    "            </div>\n" +

                    "            <button onclick=\"selectUser('"+list1+"')\">Trả lời</button> <button onclick=\"deleteUser('"+list1+"')\">Đóng</button>\n" +
                    "        </div>\n" +
                    "    </li>\n" +
                    "</a>"
        );

        }

    }
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public void delete(@RequestBody MessageModel messageModel, HttpServletResponse response) throws IOException {
        UserStorage.deleteByUserName(messageModel.getFromLogin());
        UserStorage.deleteMessageByUserName(messageModel.getFromLogin());
        Set<String> list = UserStorage.getInstance().getUsers();
        PrintWriter printWriter = response.getWriter();
        for (String list1 : list) {
            if(list1.equals("admin")){
                continue;
            }
            printWriter.println(
                    "<a href=\"#\">\n" +
                            "    <li class=\"clearfix\">\n" +
                            "        <img style=\"border-radius:50%;\" src=\"/template/web/public/img/avatarUser.jpg\" width=\"55px\"  height=\"55px\" alt=\"avatar\"/>\n" +
                            "        <div class=\"about\">\n" +
                            "            <div id=\"userNameAppender_"+list1+"\" class=\"name\">" + list1 + "</div>\n" +
                            "            <div class=\"status\">\n" +
                            "                <i class=\"fa fa-circle offline\"></i>\n" +
                            "            <span style=\"color: red\" id=\"newMessage1_"+list1+"\"></span>" +
                            "            </div>\n" +


                            "            <button onclick=\"selectUser('" + list1 + "')\">Trả lời</button> <button onclick=\"deleteUser('" + list1 + "')\">Đóng</button>\n" +
                            "        </div>\n" +
                            "    </li>\n" +
                            "</a>"
            );
        }
    }


}
