package com.bookshop.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageUtil {
	private String messages;

	public Map<String, String> getMessage(String message) {
		Map<String, String> result = new HashMap<>();
		if (message.equals("update_success")) {
			result.put("message", "Update success");
			result.put("alert", "success");
		} else if (message.equals("insert_success")) {
			result.put("message", "Insert success");
			result.put("alert", "success");
		} else if (message.equals("delete_success")) {
			result.put("message", "Delete success");
			result.put("alert", "success");
		} else if (message.equals("error_system")) {
			result.put("message", "Error system");
			result.put("alert", "danger");
		}else if(message.equals("format_incorrect")){
			result.put("message","Định dạng gmail không đúng!");
			result.put("alert", "danger");
		}else if(message.equals("success")){
			result.put("message","Thành công!");
			result.put("alert", "success");
		}else if(message.equals("incorrect_password")){
			result.put("message","Mật khẩu cũ không chính xác!");
			result.put("alert", "danger");
		}
		return result;
	}

}
