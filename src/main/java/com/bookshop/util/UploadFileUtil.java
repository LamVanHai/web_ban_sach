package com.bookshop.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UploadFileUtil {

    public final String root = "src\\main\\webapp\\template\\web\\public\\books\\";
    public static String fileURL;

    public String saveFile(byte[] bytes, String path, HttpServletRequest request) {
        String root2 = request.getServletContext().getRealPath("image4");
        String url = root2.substring(0, root2.indexOf("target")) + root;
        File filedir = new File(url);
        String nameBookNew = null;
        if (!filedir.exists()) {
            filedir.mkdir();
        }
        if (bytes != null) {
            String rootPath = System.getProperty("catalina.home");
            // Tạo nơi lưu trữ file
            File dir = new File(rootPath + File.separator + "/img");
            if (!dir.exists()) {
                dir.mkdir();
            }
            //Tạo file trên server
            SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
            String middle = format.format(new Date());
            String nameBook = path.substring(0, path.indexOf('.'));
            String last = path.substring(path.indexOf('.'));
            nameBookNew = nameBook + middle + last;
            File serverFile = new File(dir.getAbsoluteFile() + File.separator + nameBookNew);
            try {
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filedir + File.separator + nameBookNew));
                stream.write(bytes);
                try {
                    Thumbnails.of(new File(filedir + File.separator + nameBookNew)).size(215, 300).toFile(serverFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        fileURL=nameBookNew;
        return nameBookNew;
    }

}
