package com.vicyor.bysj.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者:姚克威
 * 时间:2020/3/7 20:59
 **/
@Service
public class FileService {
    private static final String CONTENT_PATH = "D:\\bysj-ui\\content\\";
    private static final String STYLE_PATH = "D:\\bysj-ui\\style\\";
    private static final String RESULT_PATH = "D:\\bysj-ui\\result\\";

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
            storeFile = new File(path);
            file.transferTo(storeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type + "/" + fileName;
    }

    private static final String pyPath = "test_all_layer.py";
    private static final String vggPath = "vgg19.npy";
    private ObjectMapper objectMapper = new ObjectMapper();

    public String transfer(String contentPath, String stylePath) {
        contentPath = contentPath.substring(contentPath.indexOf('/') + 1);
        stylePath = stylePath.substring(stylePath.indexOf('/') + 1);
        String ret=contentPath.substring(0, contentPath.indexOf('.')) + "-" + stylePath;
        String result = RESULT_PATH + ret;
        contentPath = CONTENT_PATH + contentPath.replace('/', '\\');
        stylePath = STYLE_PATH + stylePath.replace('/', '\\');

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("localhost", 8080));
            Map<String, Object> args = new HashMap<>();
            args.put("content_path", contentPath);
            args.put("style_path", stylePath);
            args.put("output_path", result);
            args.put("pretrained_vgg", vggPath);
            args.put("alpha", 0.8);
            String jsonStr = objectMapper.writeValueAsString(args);
            OutputStream sos = socket.getOutputStream();
            sos.write(jsonStr.getBytes("UTF-8"));
            sos.flush();
            InputStream in = socket.getInputStream();
            byte []buf=new byte[1024];
            int length = in.read(buf);
            System.out.println(new String(buf,"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/result/"+ret;
    }
}
