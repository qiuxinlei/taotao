package com.taotao.controller;

import com.taotao.pojo.PictureResult;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;
    //上传文件
    @RequestMapping(value = "/pic/upload")
    @ResponseBody
    public PictureResult uploadFile(@RequestParam(value = "uploadFile",required = false,defaultValue = "") MultipartFile uploadFile){
        PictureResult result = pictureService.uploadPic(uploadFile);

        System.out.println(result);
        return result;
    }

}
