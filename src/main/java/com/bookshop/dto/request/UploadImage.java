package com.bookshop.dto.request;

import lombok.Setter;

@Setter
public class UploadImage {

    private String base64;

    private String name;

    public String getBase64() {
        return base64.split(",")[1];
    }

    public String getName() {
        return name;
    }
}
