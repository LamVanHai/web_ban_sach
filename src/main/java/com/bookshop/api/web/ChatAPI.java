package com.bookshop.api.web;

import com.bookshop.dto.MessageModel;
import com.bookshop.dto.MessageResponse;
import com.bookshop.dto.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class ChatAPI {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel messageModel) throws InterruptedException {
        boolean isExist = UserStorage.getInstance().getUsers().contains(to);
        boolean isExistFromLogin = UserStorage.getInstance().getUsers().contains(messageModel.getFromLogin());
        if (!isExistFromLogin) {
            MessageModel model = new MessageModel();
            model.setMessage("Đã ngắt kết nối! Vui lòng kết nối lại");
            model.setFromLogin("admin");
            simpMessagingTemplate.convertAndSend("/topic/messages/" + messageModel.getFromLogin(), model);
        } else {
            if (isExist) {
                Thread.sleep(1000);
                if (messageModel.getFromLogin().equals("admin")) {
                    UserStorage.setListMessage(to, new MessageResponse(messageModel.getFromLogin(), messageModel.getMessage()));
                } else {
                    UserStorage.setListMessage(messageModel.getFromLogin(), new MessageResponse(messageModel.getFromLogin(), messageModel.getMessage()));
                }

                simpMessagingTemplate.convertAndSend("/topic/messages/" + to, messageModel);
            }
        }

    }

    @PostMapping("/listMessage")
    public List<MessageResponse> listMessage(HttpServletResponse response, @RequestBody MessageModel messageModel) throws IOException {
        List<MessageResponse> list = UserStorage.getMessage(messageModel.getFromLogin());
        return list;
    }
}
