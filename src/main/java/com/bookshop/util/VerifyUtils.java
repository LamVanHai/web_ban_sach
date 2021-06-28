package com.bookshop.util;

import com.bookshop.constant.Recaptcha;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class VerifyUtils {

    public static final String SITE_VERIFY_URL="https://www.google.com/recaptcha/api/siteverify";

    public static boolean verify(String gRecaptchaResponse){
        if(gRecaptchaResponse==null || gRecaptchaResponse.length()==0){
            return false;
        }
        try {
            URL verifyUrl=new URL(SITE_VERIFY_URL);
            //Mở một kết nối tới url
            HttpURLConnection conn= (HttpURLConnection) verifyUrl.openConnection();
            //Thêm các thông tin header cà request chuẩn bị gửi tới server
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent","Mozilla/5.0");
            conn.setRequestProperty("Accept-language","en-US,en,q=0.5");

            //Dữ liệu gửi tới server
            String postPareams="secret=" + Recaptcha.SECRET_KEY +"&response"+gRecaptchaResponse;
            //send request

            conn.setDoOutput(true);

            //Lấy out Stream (Luồng đầu ra) cảu kết nối tới server.
            //Ghi dữ liệu vào Output Stream, có nghĩa là gửi thông tin tới server.
            OutputStream outputStream=conn.getOutputStream();
            outputStream.write(postPareams.getBytes());

            outputStream.flush();
            outputStream.close();

            //Mã trả lời được trả về từ server
            int responseCode=conn.getResponseCode();
            System.out.println("reponseCode="+ responseCode);


            //Lấy input stream(luồng đầu vào) của Connection
            //Để đọc  dữ liệu gửi đến từ server
            InputStream inputStream=conn.getInputStream();
            JsonReader jsonReader= Json.createReader(inputStream);
            JsonObject  jsonObject=jsonReader.readObject();
            jsonReader.close();

            //==> success: true
            System.out.println("Response:" +jsonObject);

            boolean success=jsonObject.getBoolean("success");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    return true;
    }
}
