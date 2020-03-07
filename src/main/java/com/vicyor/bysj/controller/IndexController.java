package com.vicyor.bysj.controller;

import com.vicyor.bysj.service.FileService;
import com.vicyor.bysj.vo.GenerateReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者:姚克威
 * 时间:2020/3/7 20:56
 **/
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    FileService fileService;

    @RequestMapping("/upload")
    @ResponseBody
    public GenerateReturnValue uploadFile(@RequestParam(name = "file") MultipartFile file,
                                          @RequestParam(name = "type") String type
    ) {
        String fileName = fileService.storeFile(file, type);
        Map<String,Object> data=new HashMap<>();
        data.put("src",fileName);
        GenerateReturnValue returnValue=new GenerateReturnValue(0,"上传成功",data);
        return returnValue;
    }
}
