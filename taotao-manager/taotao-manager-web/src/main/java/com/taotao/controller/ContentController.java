package com.taotao.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import com.taotao.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Package com.taotao.controller
 * @User 12428
 * @Date 2018/3/21 20:30
 */

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;
    @Value(value = "${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value(value = "${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;

    @RequestMapping("save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent content){
        TaotaoResult result = contentService.insertContent(content);
        HttpClientUtil.get(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
        return result;
    }
}
