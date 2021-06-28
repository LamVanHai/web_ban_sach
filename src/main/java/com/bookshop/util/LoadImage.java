package com.bookshop.util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

@WebServlet(urlPatterns = "/image/*")
public class LoadImage extends HttpServlet {

    private String imagePath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        imagePath=System.getProperty("catalina.home")+ File.separator+"img";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get requested image by pathInfo
        String requestImage=req.getPathInfo();

        if(requestImage==null){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // Decode the file name
        File image=new File(imagePath, URLDecoder.decode(requestImage,"UTF-8"));

        //Check if file actually exists in file system
        if(!image.exists()){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //Get content type by file name
//        String contentType=getServletContext().getMimeType(image.getName());

        //Check if file is actually an image
//        if(contentType==null||!contentType.startsWith("image")){
//            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
        resp.reset();
        //resp.setContentType(contentType);
        resp.setHeader("Content-lenght",String.valueOf(image.length()));

        //Write image content to response
        Files.copy(image.toPath(),resp.getOutputStream());
    }
}
