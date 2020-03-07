package com.vicyor.bysj.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 作者:姚克威
 * 时间:2020/3/7 20:59
 **/
@Service
public class FileService {
    private static final String CONTENT_PATH = "D:\\bysj-ui\\content\\";
    private static final String STYLE_PATH = "D:\\bysj-ui\\style\\";

    public String storeFile(MultipartFile file, String type) {
        String fileName = file.getOriginalFilename();
        String path = null;
        File storeFile = null;
        if (type.equals("content")) {
            path = CONTENT_PATH + fileName;
        } else {
            path = STYLE_PATH + fileName;
        }
        try {
            storeFile=new File(path);
            file.transferTo(storeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type+"/"+fileName;
    }
}
