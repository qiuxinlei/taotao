package com.taotao.controller;

import com.taotao.pojo.PictureResult;
import com.taotao.service.PictureService;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;
    //上传文件
    @RequestMapping(value = "/pic/upload")
    @ResponseBody
    public String uploadFile(@RequestParam(value = "uploadFile",required = false,defaultValue = "") MultipartFile uploadFile){
        PictureResult result = pictureService.uploadPic(uploadFile);
        String json = JsonUtils.objectToJson(result);
        return json;
    }

}
