package com.common.server.istudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping(value="/upload.do",method= RequestMethod.POST)
    public String upload(@RequestParam("dir") MultipartFile[] dir) {
        System.out.println("上传文件夹...");
        File file;
        String fileName="";
        String filePath="";
        for (MultipartFile f : dir) {
            fileName=f.getOriginalFilename();
            String type=f.getContentType();
            System.out.println("\n"+fileName+" ,"+type);
            filePath="/Users/macbook/workspace/tmp"+fileName.substring(0,fileName.lastIndexOf("/"));
            File tFile = new File(filePath);
            if(!tFile.exists()){
                tFile.mkdirs();
            }
            file = new File("/Users/macbook/workspace/tmp" + fileName);
            try {
                file.createNewFile();
                //将上传文件保存到一个目标文件中
                f.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "One";
    }

}
